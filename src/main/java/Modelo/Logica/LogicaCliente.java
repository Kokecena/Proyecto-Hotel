package Modelo.Logica;

import static Metodos.Ayuda.*;
import Modelo.Datos.ClienteDaoJDBC;
import Modelo.domain.ClienteDTO;
import Vista.ClientesTrabajadores.VentanaClientes;
import Vista.ClientesTrabajadores.PanelRegistroCliente;
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
public class LogicaCliente {

    private final int MAX_CARACTERES_NOMBRE = 45;
    private final int MAX_CARACTERES_APATERNO = 20;
    private final int MAX_CARACTERES_AMATERNO = 20;
    private final int MAX_CARACTERES_NUM_DOCUMENTO = 10;
    private final int MAX_CARACTERES_DIRECCION = 100;
    private final int MAX_CARACTERES_TELEFONO = 10;
    private final int MAX_CARACTERES_EMAIL = 25;
    private final int MAX_CARACTERES_CODIGO = 10;

    private Connection conexion = null;
    private ClienteDTO cliente = null;
    private ClienteDaoJDBC clienteDao = null;
    private final PanelRegistroCliente componente;
    private final PanelTabla componenteTabla;

    public LogicaCliente(VentanaClientes componente, Connection conexion) {
        this.componente = componente.getpRegistroCliente();
        this.componenteTabla = componente.getpListadoClientes();
        this.conexion = conexion;
        this.clienteDao = new ClienteDaoJDBC(conexion);
    }

    public void nuevoCliente() {
        try {
            insertarCliente();
            conexion.commit();
            ventanaMensaje(componente,
                    cliente.getIdPersona() == 0 ? "Se a registrado con exito el nuevo cliente"
                    : "La informacion del cliente con ID " + cliente.getIdPersona() + "\n ha sido actualizada con exito.",
                    cliente.getIdPersona() == 0 ? "¡Registro con exito!"
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

    private void insertarCliente() throws SQLException, Exception {
        String error = comprobarCampos();
        if (error.isEmpty()) {
            cliente = obtenerDatos();
            boolean inserta = cliente.getIdPersona() == 0;
            if (inserta) {
                clienteDao.insert(cliente);
            } else {
                clienteDao.update(cliente);
            }
            actualizarTabla();
            limpiarCampos();
        } else {
            throw new Exception(error);
        }
    }

    public void buscarProducto() {
    }

    public void actualizarCliente() {
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
        componente.getTxtCodigo().setText(String.valueOf(modelo.getValueAt(fila, 9)));
        activarBotones(true, 3);
    }

    public void borrarRegistro() {
        if (Metodos.Ayuda.ventanaMensaje(componenteTabla, "¿Desea borrar este registro?", "Confirmar opción") == 0) {
            int fila = componenteTabla.getTbl().getSelectedRow();
            DefaultTableModel modelo = componenteTabla.getModelo();
            String idStr = String.valueOf(modelo.getValueAt(fila, 0));
            cliente = new ClienteDTO(Integer.parseInt(idStr));
            try {
                clienteDao.delete(cliente);
                conexion.commit();
                ventanaMensaje(componenteTabla, "El cliente con id " + idStr + " a sido eliminado", "Se ha eliminado con exito el registro", JOptionPane.INFORMATION_MESSAGE);
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
        List<ClienteDTO> clientes = null;
        try {
            clientes = clienteDao.select();
            clientes.forEach(c -> {
                componenteTabla.getModelo().addRow(
                        new Object[]{
                            c.getIdPersona(),
                            c.getNombre(),
                            c.getaPaterno(),
                            c.getaMaterno(),
                            c.getTipoDocumento(),
                            c.getNumDocumento(),
                            c.getDireccion(),
                            c.getTelefono(),
                            c.getEmail(),
                            c.getCodigoCliente()});
            });
            componenteTabla.getEtiRegistros().setText("Registros: " + clientes.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private ClienteDTO obtenerDatos() {
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
        String codigo = componente.getTxtCodigo().getText();
        return new ClienteDTO(id, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email, codigo);
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
        error += comprobarCampo(componente.getTxtCodigo().getText(), "Codigo", MAX_CARACTERES_CODIGO);
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
        componente.getTxtCodigo().setText("");
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
        componente.getTxtCodigo().setEnabled(activar);
    }
}
