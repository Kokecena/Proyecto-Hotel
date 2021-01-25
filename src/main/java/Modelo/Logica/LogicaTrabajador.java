/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Logica;

import Metodos.Ayuda;
import static Metodos.Ayuda.*;
import Modelo.Datos.TrabajadorDaoJDBC;
import Modelo.domain.TrabajadorDTO;
import Vista.ClientesTrabajadores.PanelRegistroTrabajador;
import Vista.ClientesTrabajadores.VentanaTrabajador;
import Vista.Formularios.PanelTabla;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovan
 */
public class LogicaTrabajador {

    private final int MAX_CARACTERES_NOMBRE = 45;
    private final int MAX_CARACTERES_APATERNO = 20;
    private final int MAX_CARACTERES_AMATERNO = 20;
    private final int MAX_CARACTERES_NUM_DOCUMENTO = 10;
    private final int MAX_CARACTERES_DIRECCION = 100;
    private final int MAX_CARACTERES_TELEFONO = 10;
    private final int MAX_CARACTERES_EMAIL = 25;
    private final int MAX_CARACTERES_SUELDO = 10;
    private final int MAX_CARACTERES_LOGIN = 15;
    private final int MAX_CARACTERES_PASSWORD = 15;

    private Connection conexion = null;
    private TrabajadorDTO trabajador = null;
    private TrabajadorDaoJDBC trabajadorDao = null;
    private final PanelRegistroTrabajador componente;
    private final PanelTabla componenteTabla;

    public LogicaTrabajador(VentanaTrabajador componente, Connection conexion) {
        this.componente = componente.getpRegistroTrabajador();
        this.componenteTabla = componente.getpListadoTrabajador();
        this.conexion = conexion;
        this.trabajadorDao = new TrabajadorDaoJDBC(conexion);
    }

    public void nuevoTrabajador() {
        try {
            insertarTrabajador();
            conexion.commit();
            ventanaMensaje(componente,
                    trabajador.getIdPersona() == 0 ? "Se a registrado con exito el nuevo trabajador"
                    : "La informacion del trabajador con ID " + trabajador.getIdPersona() + "\n ha sido actualizada con exito.",
                    trabajador.getIdPersona() == 0 ? "¡Registro con exito!"
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

    private void insertarTrabajador() throws SQLException, Exception {
        String error = comprobarCampos();
        if (error.isEmpty()) {
            trabajador = obtenerDatos();
            boolean inserta = trabajador.getIdPersona() == 0;
            if (inserta) {
                trabajadorDao.insert(trabajador);
            } else {
                trabajadorDao.update(trabajador);
            }
            actualizarTabla();
            limpiarCampos();
        } else {
            throw new Exception(error);
        }
    }

    public void buscarProducto() {
    }

    public void actualizarTrabajador() {
        int fila = componenteTabla.getTbl().getSelectedRow();
        DefaultTableModel modelo = componenteTabla.getModelo();
        componente.getTxtId().setText(String.valueOf(modelo.getValueAt(fila, 0)));
        componente.getTxtNombre().setText(String.valueOf(modelo.getValueAt(fila, 1)));
        componente.getTxtAPaterno().setText(String.valueOf(modelo.getValueAt(fila, 2)));
        componente.getTxtAMaterno().setText(String.valueOf(modelo.getValueAt(fila, 3)));
        componente.getJcDoc().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 4)));
        componente.getTxtNumDoc().setText(String.valueOf(modelo.getValueAt(fila, 5)));
        componente.getTxtDireccion().setText(String.valueOf(modelo.getValueAt(fila, 6)));
        componente.getTxtTelefono().setText(String.valueOf(modelo.getValueAt(fila, 7)));
        componente.getTxtEmail().setText(String.valueOf(modelo.getValueAt(fila, 8)));
        componente.getTxtSueldo().setText(String.valueOf(modelo.getValueAt(fila, 9)));
        componente.getJcAcceso().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 10)));
        componente.getTxtLogin().setText(String.valueOf(modelo.getValueAt(fila, 11)));
        componente.getTxtPassword().setText(String.valueOf(modelo.getValueAt(fila, 12)));
        componente.getJcEstado().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 13)));
        activarBotones(true, 3);
    }

    public void borrarRegistro() {
        if (Metodos.Ayuda.ventanaMensaje(componenteTabla, "¿Desea borrar este registro?", "Confirmar opción") == 0) {
            int fila = componenteTabla.getTbl().getSelectedRow();
            DefaultTableModel modelo = componenteTabla.getModelo();
            String idStr = String.valueOf(modelo.getValueAt(fila, 0));
            trabajador = new TrabajadorDTO(Integer.parseInt(idStr));
            try {
                trabajadorDao.delete(trabajador);
                conexion.commit();
                ventanaMensaje(componenteTabla, "El trabajador con id " + idStr + " a sido eliminado", "Se ha eliminado con exito el registro", JOptionPane.INFORMATION_MESSAGE);
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
        List<TrabajadorDTO> trabajadores = null;
        try {
            trabajadores = trabajadorDao.select();
            trabajadores.forEach(t -> {
                componenteTabla.getModelo().addRow(
                        new Object[]{
                            t.getIdPersona(),
                            t.getNombre(),
                            t.getaPaterno(),
                            t.getaMaterno(),
                            t.getTipoDocumento(),
                            t.getNumDocumento(),
                            t.getDireccion(),
                            t.getTelefono(),
                            t.getEmail(),
                            t.getSueldo(),
                            t.getAcceso(),
                            t.getLogin(),
                            t.getPassword(),
                            t.getEstado()});
            });
            componenteTabla.getEtiRegistros().setText("Registros: " + trabajadores.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private TrabajadorDTO obtenerDatos() {
        String idStr = componente.getTxtId().getText();
        int id = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
        String nombre = componente.getTxtNombre().getText();
        String aPaterno = componente.getTxtAPaterno().getText();
        String aMaterno = componente.getTxtAMaterno().getText();
        String tipoDocumento = (String) componente.getJcDoc().getSelectedItem();
        String numDocumento = componente.getTxtNumDoc().getText();
        String direccion = componente.getTxtDireccion().getText();
        String telefono = componente.getTxtTelefono().getText();
        String email = componente.getTxtEmail().getText();
        double sueldo = Double.parseDouble(componente.getTxtSueldo().getText());
        String acceso = (String) componente.getJcAcceso().getSelectedItem();
        String login = componente.getTxtLogin().getText();
        String password = Ayuda.verPassword(componente.getTxtPassword().getPassword());
        String estado = (String) componente.getJcEstado().getSelectedItem();
        return new TrabajadorDTO(id, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email, sueldo, acceso, login, password, estado);
    }

    private String comprobarCampos() {
        String error = "";
        error += comprobarCampo(componente.getTxtNombre().getText(), "Nombre", MAX_CARACTERES_NOMBRE);
        error += comprobarCampo(componente.getTxtAPaterno().getText(), "Apellido paterno", MAX_CARACTERES_APATERNO);
        error += comprobarCampo(componente.getTxtAMaterno().getText(), "Apellido materno", MAX_CARACTERES_AMATERNO);
        error += comprobarCampo(componente.getTxtNumDoc().getText(), "Num. documento", MAX_CARACTERES_NUM_DOCUMENTO);
        error += comprobarCampo(componente.getTxtDireccion().getText(), "Direccion", MAX_CARACTERES_DIRECCION);
        error += comprobarCampo(componente.getTxtTelefono().getText(), "Telefono", MAX_CARACTERES_TELEFONO, COMPROBAR_NUMEROS_ENTEROS);
        error += comprobarCampo(componente.getTxtEmail().getText(), "Email", MAX_CARACTERES_EMAIL, COMPROBAR_EMAIL);
        error += comprobarCampo(componente.getTxtSueldo().getText(), "Sueldo", MAX_CARACTERES_SUELDO, COMPROBAR_NUMEROS_REALES);
        error += comprobarCampo(componente.getTxtLogin().getText(), "Login", MAX_CARACTERES_LOGIN);
        error += comprobarCampo(verPassword(componente.getTxtPassword().getPassword()), "Contraseña", MAX_CARACTERES_PASSWORD);
        return error;
    }

    public void limpiarCampos() {
        componente.getTxtId().setText("");
        componente.getTxtNombre().setText("");
        componente.getTxtAPaterno().setText("");
        componente.getTxtAMaterno().setText("");
        componente.getJcDoc().setSelectedIndex(0);
        componente.getTxtNumDoc().setText("");
        componente.getTxtDireccion().setText("");
        componente.getTxtTelefono().setText("");
        componente.getTxtEmail().setText("");
        componente.getTxtSueldo().setText("");
        componente.getJcAcceso().setSelectedIndex(0);
        componente.getTxtLogin().setText("");
        componente.getTxtPassword().setText("");
        componente.getJcEstado().setSelectedIndex(0);
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
        componente.getTxtNombre().setEnabled(activar);
        componente.getTxtAPaterno().setEnabled(activar);
        componente.getTxtAMaterno().setEnabled(activar);
        componente.getJcDoc().setEnabled(activar);
        componente.getTxtNumDoc().setEnabled(activar);
        componente.getTxtDireccion().setEnabled(activar);
        componente.getTxtTelefono().setEnabled(activar);
        componente.getTxtEmail().setEnabled(activar);
        componente.getTxtSueldo().setEnabled(activar);
        componente.getJcAcceso().setEnabled(activar);
        componente.getTxtLogin().setEnabled(activar);
        componente.getTxtPassword().setEnabled(activar);
        componente.getJcEstado().setEnabled(activar);
    }
}
