package Modelo.Logica;

import static Metodos.Ayuda.*;
import Modelo.Datos.HabitacionDaoJDBC;
import Modelo.domain.HabitacionDTO;
import Vista.Formularios.PanelTabla;
import Vista.habitacion.PanelRegistroHabitacion;
import Vista.habitacion.VentanaHabitacion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovan
 */
public class LogicaHabitacion {

    private final int MAX_CARACTERES_NUMERO = 4;
    private final int MAX_CARACTERES_DESCRIPCION = 255;
    private final int MAX_CARACTERES_CARACTERISTICAS = 512;
    private final int MAX_CARACTERES_PRECIO_DIARIO = 10;

    private Connection conexion = null;
    private HabitacionDTO habitacion = null;
    private HabitacionDaoJDBC habitacionDao = null;
    private final PanelRegistroHabitacion componente;
    private final PanelTabla componenteTabla;

    public LogicaHabitacion(VentanaHabitacion componente, Connection conexion) {
        this.componente = componente.getpRegistro();
        this.componenteTabla = componente.getpListado();
        this.conexion = conexion;
        this.habitacionDao = new HabitacionDaoJDBC(conexion);
    }

    public void nuevaHabitacion() {
        try {
            insertarHabitacion();
            conexion.commit();
            ventanaMensaje(componente,
                    habitacion.getIdHabitacion() == 0 ? "Se a registrado con exito la habitacion"
                    : "La informacion de la habitacion con ID " + habitacion.getIdHabitacion() + "\n ha sido actualizada con exito.",
                    habitacion.getIdHabitacion() == 0 ? "¡Registro con exito!"
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

    private void insertarHabitacion() throws SQLException, Exception {
        String error = comprobarCampos();
        if (error.isEmpty()) {
            habitacion = obtenerDatos();
            boolean inserta = habitacion.getIdHabitacion() == 0;
            if (inserta) {
                habitacionDao.insert(habitacion);
            } else {
                habitacionDao.update(habitacion);
            }
            actualizarTabla();
            limpiarCampos();
        } else {
            throw new Exception(error);
        }
    }

    public void buscarHabitacion() {
    }

    public void actualizarHabitacion() {
        int fila = componenteTabla.getTbl().getSelectedRow();
        DefaultTableModel modelo = componenteTabla.getModelo();
        componente.getTxtId().setText(String.valueOf(modelo.getValueAt(fila, 0)));
        componente.getTxtNumero().setText(String.valueOf(modelo.getValueAt(fila, 1)));
        componente.getJcPiso().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 2)));
        componente.getTxaDescripcion().setText(String.valueOf(modelo.getValueAt(fila, 3)));
        componente.getTxaCaracteristicas().setText(String.valueOf(modelo.getValueAt(fila, 4)));
        componente.getTxtPrecioDiario().setText(String.valueOf(modelo.getValueAt(fila, 5)));
        componente.getJcEstado().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 6)));
        componente.getJcTipo().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 7)));
        activarBotones(true, 3);
    }

    public void borrarRegistro() {
        if (Metodos.Ayuda.ventanaMensaje(componenteTabla, "¿Desea borrar este registro?", "Confirmar opción") == 0) {
            int fila = componenteTabla.getTbl().getSelectedRow();
            DefaultTableModel modelo = componenteTabla.getModelo();
            String idStr = String.valueOf(modelo.getValueAt(fila, 0));
            habitacion = new HabitacionDTO(Integer.parseInt(idStr));
            try {
                habitacionDao.delete(habitacion);
                conexion.commit();
                ventanaMensaje(componenteTabla, "La habitacion con id " + idStr + " a sido eliminado", "Se ha eliminado con exito el registro", JOptionPane.INFORMATION_MESSAGE);
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
        List<HabitacionDTO> habitaciones;
        try {
            habitaciones = habitacionDao.select();
            habitaciones.forEach(h -> {
                componenteTabla.getModelo().addRow(
                        new Object[]{
                            h.getIdHabitacion(),
                            h.getNumero(),
                            h.getPiso(),
                            h.getDescripcion(),
                            h.getCaracteristicas(),
                            h.getPrecioDiario(),
                            h.getEstado(),
                            h.getTipoHabitacion()});
            });
            componenteTabla.getEtiRegistros().setText("Registros: " + habitaciones.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private HabitacionDTO obtenerDatos() {
        String idStr = componente.getTxtId().getText();
        int id = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
        String numero = componente.getTxtNumero().getText();
        String piso = (String) componente.getJcPiso().getSelectedItem();
        String descripcion = componente.getTxaDescripcion().getText();
        String caracteristicas = componente.getTxaCaracteristicas().getText();
        double precioDiario = Double.parseDouble(componente.getTxtPrecioDiario().getText());
        String estado = (String) componente.getJcEstado().getSelectedItem();
        String tipo = (String) componente.getJcTipo().getSelectedItem();
        return new HabitacionDTO(id, numero, piso, descripcion, caracteristicas, precioDiario, estado, tipo);
    }

    private String comprobarCampos() {
        String error = "";
        error += comprobarCampo(componente.getTxtNumero().getText(), "Numero", MAX_CARACTERES_NUMERO);
        error += comprobarCampo(componente.getTxaCaracteristicas().getText(), "Caracteristicas", MAX_CARACTERES_CARACTERISTICAS);
        error += comprobarCampo(componente.getTxaDescripcion().getText(), "Descripcion", MAX_CARACTERES_DESCRIPCION);
        error += comprobarCampo(componente.getTxtPrecioDiario().getText(), "Precio diario", MAX_CARACTERES_PRECIO_DIARIO);
        return error;
    }

    public void limpiarCampos() {
        componente.getTxtId().setText("");
        componente.getTxtNumero().setText("");
        componente.getJcPiso().setSelectedIndex(0);
        componente.getTxaDescripcion().setText("");
        componente.getTxaCaracteristicas().setText("");
        componente.getTxtPrecioDiario().setText("");
        componente.getJcEstado().setSelectedIndex(0);
        componente.getJcTipo().setSelectedIndex(0);
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
        componente.getTxtNumero().setEnabled(activar);
        componente.getJcPiso().setEnabled(activar);
        componente.getTxaDescripcion().setEnabled(activar);
        componente.getTxaCaracteristicas().setEnabled(activar);
        componente.getTxtPrecioDiario().setEnabled(activar);
        componente.getJcEstado().setEnabled(activar);
        componente.getJcTipo().setEnabled(activar);
    }
}
