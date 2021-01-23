/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Logica;

import static Metodos.Ayuda.*;
import Modelo.Datos.PersonaDAO;
import Modelo.Datos.PersonaDaoJDBC;
import Modelo.Datos.ReservaDaoJDBC;
import Modelo.Datos.TrabajadorDAO;
import Modelo.Datos.TrabajadorDaoJDBC;
import Modelo.domain.PersonaDTO;
import Modelo.domain.ReservaDTO;
import Modelo.domain.TrabajadorDTO;
import Vista.Formularios.PanelTabla;
import Vista.ReservasConsumos.PanelRegistroReserva;
import Vista.ReservasConsumos.VentanaListadoClientes;
import Vista.ReservasConsumos.VentanaListadoHabitaciones;
import Vista.ReservasConsumos.VentanaReserva;
import Vista.Sistema.VentanaSistema;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovan
 */
public class LogicaReserva {

    private final int MAX_CARACTERES_OBSERVACION = 255;
    private final int MAX_CARACTERES_COSTO = 10;

    private JInternalFrame ventanaLista;
    private int ventanaActiva;//1 - Habitaciones, 2 - Clientes
    private PanelTabla listaTabla;

    private Connection conexion = null;
    private ReservaDTO reserva = null;
    private ReservaDaoJDBC reservaDao = null;

    private final PanelRegistroReserva componente;
    private final PanelTabla componenteTabla;
    private final TrabajadorDTO usuario;

    public LogicaReserva(VentanaReserva componente, TrabajadorDTO usuario, Connection conexion) {
        this.componente = componente.getpRegistroReserva();
        this.componenteTabla = componente.getpListadoReserva();
        this.usuario = usuario;
        this.conexion = conexion;
        this.reservaDao = new ReservaDaoJDBC(conexion);
        datosPersona(usuario, 1);
    }

    private void datosPersona(PersonaDTO persona, int tipo) {
        int id = persona.getIdPersona();
        String nombre = persona.getNombre() + " " + persona.getaPaterno() + " " + persona.getaMaterno();
        if (tipo == 1) { //Trabajador
            componente.getTxtIdTrabajador().setText(String.valueOf(id));
            componente.getTxtTrabajador().setText(nombre);
        } else {//Cliente
            componente.getTxtIdCliente().setText(String.valueOf(id));
            componente.getTxtCliente().setText(nombre);
        }
    }

    public void nuevaReserva() {
        try {
            insertarReserva();
            conexion.commit();
            ventanaMensaje(componente,
                    reserva.getIdReserva() == 0 ? "Se a registrado con exito la Reserva"
                    : "La informacion de la Reserva con ID " + reserva.getIdReserva() + "\n ha sido actualizada con exito.",
                    reserva.getIdReserva() == 0 ? "¡Registro con exito!"
                    : "¡Actualizacion con exito!",
                    JOptionPane.INFORMATION_MESSAGE);
            activarBotones(false, 1);
        } catch (SQLException ex) {
            try {
                ventanaMensaje(componente, ex.getMessage() + "\nHaciendo rollback...", "Ocurrio un error", JOptionPane.INFORMATION_MESSAGE);
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        } catch (Exception ex) {
            ventanaMensaje(componente, ex.getMessage(), "¡ERROR!", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void insertarReserva() throws SQLException, Exception {
        String error = comprobarCampos();
        if (error.isEmpty()) {
            reserva = obtenerDatos();
            boolean inserta = reserva.getIdReserva() == 0;
            if (inserta) {
                reservaDao.insert(reserva);
            } else {
                reservaDao.update(reserva);
            }
            actualizarTabla();
            limpiarCampos();
        } else {
            throw new Exception(error);
        }
    }

    public void crearVentanaTabla(int ventanaActiva, boolean activo, JInternalFrame ventana, PanelTabla tabla, JButton boton, InternalFrameListener ifl, MouseListener ml) {
        this.ventanaActiva = ventanaActiva;
        this.listaTabla = tabla;
        this.ventanaLista = ventana;
        boton.setEnabled(!activo);
        if (activo) {
            ventanaLista.addInternalFrameListener(ifl);
            listaTabla.getTbl().addMouseListener(ml);
            VentanaSistema.escritorio.add(ventanaLista);
            ventanaLista.setVisible(true);
        } else {
            ventanaLista.dispose();
        }
    }

    public void seleccionarRegistro() {
        int fila = listaTabla.getTbl().getSelectedRow();
        DefaultTableModel modelo = listaTabla.getModelo();
        if (ventanaActiva == 1) {
            componente.getTxtIdHabitacion().setText(String.valueOf(modelo.getValueAt(fila, 0)));
            componente.getTxtNumero().setText(String.valueOf(modelo.getValueAt(fila, 1)));
        } else if (ventanaActiva == 2) {
            componente.getTxtIdCliente().setText(String.valueOf(modelo.getValueAt(fila, 0)));
            String nombre = (String) modelo.getValueAt(fila, 1);
            String apellidoP = (String) modelo.getValueAt(fila, 2);
            String apellidoM = (String) modelo.getValueAt(fila, 3);
            componente.getTxtCliente().setText(nombre + " " + apellidoP + " " + apellidoM);
        }
        ventanaLista.doDefaultCloseAction();
    }

    public void actualizarReserva() {
        PersonaDAO persona = new PersonaDaoJDBC();
        int fila = componenteTabla.getTbl().getSelectedRow();
        DefaultTableModel modelo = componenteTabla.getModelo();
        componente.getTxtId().setText(String.valueOf(modelo.getValueAt(fila, 0)));
        componente.getTxtIdHabitacion().setText(String.valueOf(modelo.getValueAt(fila, 1)));
        componente.getTxtIdCliente().setText(String.valueOf(modelo.getValueAt(fila, 2)));
        componente.getTxtIdTrabajador().setText(String.valueOf(modelo.getValueAt(fila, 3)));
        try {
            int idTrabajador = Integer.parseInt(componente.getTxtIdTrabajador().getText());
            int idCliente = Integer.parseInt(componente.getTxtIdCliente().getText());
            PersonaDTO t = persona.search(new PersonaDTO(idTrabajador));
            PersonaDTO c = persona.search(new PersonaDTO(idCliente));
            datosPersona(t, 1);
            datosPersona(c, 2);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        componente.getCbTipoReserva().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 4)));
        componente.getDcReserva().setDate((Date) modelo.getValueAt(fila, 5));
        componente.getDcIngreso().setDate((Date) modelo.getValueAt(fila, 6));
        componente.getDcSalida().setDate((Date) modelo.getValueAt(fila, 7));
        componente.getTxtCosto().setText(String.valueOf(modelo.getValueAt(fila, 8)));
        componente.getTxtObservacion().setText(String.valueOf(modelo.getValueAt(fila, 9)));
        componente.getCbEstadoReserva().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 10)));
        activarBotones(true, 3);
    }

    public void borrarRegistro() {
        if (Metodos.Ayuda.ventanaMensaje(componenteTabla, "¿Desea borrar este registro?", "Confirmar opción") == 0) {
            int fila = componenteTabla.getTbl().getSelectedRow();
            DefaultTableModel modelo = componenteTabla.getModelo();
            String idStr = String.valueOf(modelo.getValueAt(fila, 0));
            reserva = new ReservaDTO(Integer.parseInt(idStr));
            try {
                reservaDao.delete(reserva);
                conexion.commit();
                ventanaMensaje(componenteTabla, "La Reserva con id " + idStr + " a sido eliminado", "Se ha eliminado con exito el registro", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                actualizarTabla();
            } catch (SQLException ex) {
                ventanaMensaje(componenteTabla, ex.getMessage() + "\nHaciendo rollback...", "Ocurrio un error", JOptionPane.INFORMATION_MESSAGE);
                try {
                    conexion.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace(System.out);
                }
            }
        }
    }

    public void actualizarTabla() {
        componenteTabla.getModelo().setRowCount(0);
        List<ReservaDTO> Reservas;
        try {
            Reservas = reservaDao.select();
            Reservas.forEach(r -> {
                componenteTabla.getModelo().addRow(
                        new Object[]{
                            r.getIdReserva(),
                            r.getIdHabitacion(),
                            r.getIdCliente(),
                            r.getIdTrabajador(),
                            r.getTipoReserva(),
                            r.getFechaReserva(),
                            r.getFechaIngreso(),
                            r.getFechaSalida(),
                            r.getCostoAlojamiento(),
                            r.getObservacion(),
                            r.getEstado()});
            });
            componenteTabla.getEtiRegistros().setText("Registros: " + Reservas.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private ReservaDTO obtenerDatos() {
        String idStr = componente.getTxtId().getText();
        int id = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
        int idHabitacion = Integer.parseInt(componente.getTxtIdHabitacion().getText());
        int idCliente = Integer.parseInt(componente.getTxtIdCliente().getText());
        int idTrabajador = Integer.parseInt(componente.getTxtIdTrabajador().getText());
        String tipoReserva = (String) componente.getCbTipoReserva().getSelectedItem();
        Date fechaReserva = componente.getDcReserva().getDate();
        Date fechaIngreso = componente.getDcIngreso().getDate();
        Date fechaSalida = componente.getDcSalida().getDate();
        double costoAlojamiento = Double.parseDouble(componente.getTxtCosto().getText());
        String observacion = componente.getTxtObservacion().getText();
        String estado = (String) componente.getCbEstadoReserva().getSelectedItem();
        return new ReservaDTO(id, idHabitacion, idCliente, idTrabajador, tipoReserva, fechaReserva, fechaIngreso, fechaSalida, costoAlojamiento, observacion, estado);
    }

    private String comprobarCampos() {
        String error = "";
        error += comprobarCampo(componente.getTxtIdHabitacion().getText(), "ID Habitacion");
        error += comprobarCampo(componente.getTxtIdCliente().getText(), "ID Cliente");
        error += comprobarCampo(componente.getDcReserva().getDate() == null ? "" : "0", "Fecha reserva");
        error += comprobarCampo(componente.getDcIngreso().getDate() == null ? "" : "0", "Fecha ingreso");
        error += comprobarCampo(componente.getDcSalida().getDate() == null ? "" : "0", "Fecha salida");
        error += comprobarCampo(componente.getTxtCosto().getText(), "Costo", MAX_CARACTERES_COSTO);
        error += comprobarCampo(componente.getTxtObservacion().getText(), "Observacion", MAX_CARACTERES_OBSERVACION);
        return error;
    }

    public void limpiarCampos() {
        datosPersona(usuario, 1);
        componente.getTxtId().setText("");
        componente.getTxtIdHabitacion().setText("");
        componente.getTxtIdCliente().setText("");
        componente.getTxtCliente().setText("");
        componente.getCbTipoReserva().setSelectedIndex(0);
        componente.getDcReserva().setDate(null);
        componente.getDcIngreso().setDate(null);
        componente.getDcSalida().setDate(null);
        componente.getTxtCosto().setText("");
        componente.getTxtObservacion().setText("");
        componente.getCbEstadoReserva().setSelectedIndex(0);
    }

    public void activarBotones(boolean activar, int estado) {
        if (estado == 1) {
            componente.getBtnGuardar().setText("Guardar");
        } else {
            componente.getBtnGuardar().setText("Actualizar");
        }
        componente.getBtnNuevo().setEnabled(!activar);
        componente.getBtnGuardar().setEnabled(activar);
        componente.getBtnCancelar().setEnabled(activar);
        activarCampos(activar);
    }

    private void activarCampos(boolean activar) {
        componente.getTxtId().setEnabled(false);
        componente.getTxtIdHabitacion().setEnabled(false);
        componente.getTxtNumero().setEnabled(false);
        componente.getTxtIdCliente().setEnabled(false);
        componente.getTxtCliente().setEnabled(false);
        componente.getTxtIdTrabajador().setEnabled(false);
        componente.getTxtTrabajador().setEnabled(false);
        componente.getCbTipoReserva().setEnabled(activar);
        componente.getDcReserva().setEnabled(activar);
        componente.getDcIngreso().setEnabled(activar);
        componente.getDcSalida().setEnabled(activar);
        componente.getTxtCosto().setEnabled(activar);
        componente.getTxtObservacion().setEnabled(activar);
        componente.getCbEstadoReserva().setEnabled(activar);
        componente.getBtnBuscarHabitacion().setEnabled(activar);
        componente.getBtnBuscarCliente().setEnabled(activar);
    }
}
