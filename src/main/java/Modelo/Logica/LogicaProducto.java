package Modelo.Logica;

import Modelo.Datos.ProductoDaoJDBC;
import Modelo.domain.ProductoDTO;
import Vista.Formularios.PanelTabla;
import Vista.Producto.PanelRegistroProducto;
import Vista.Producto.VentanaProducto;
import java.sql.Connection;
import javax.swing.JOptionPane;
import static Metodos.Ayuda.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovan
 */
public class LogicaProducto {

    private final int MAX_CARACTERES_NOMBRE = 45;
    private final int MAX_CARACTERES_DESCRIPCION = 255;
    private final int MAX_CARACTERES_PRECIO_VENTA = 10;

    private Connection conexion = null;
    private ProductoDTO producto = null;
    private ProductoDaoJDBC productoDao = null;
    private final PanelRegistroProducto componente;
    private final PanelTabla componenteTabla;

    public LogicaProducto(VentanaProducto componente, Connection conexion) {
        this.componente = componente.getpRegistroProducto();
        this.componenteTabla = componente.getpListadoProducto();
        this.conexion = conexion;
        this.productoDao = new ProductoDaoJDBC(conexion);
    }

    public void nuevoProducto() {
        try {
            insertarProducto();
            conexion.commit();
            ventanaMensaje(componente,
                    producto.getIdProducto() == 0 ? "Se a registrado con exito el producto"
                    : "La informacion del producto con ID " + producto.getIdProducto() + "\n ha sido actualizada con exito.",
                    producto.getIdProducto() == 0 ? "¡Registro con exito!"
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

    private void insertarProducto() throws SQLException, Exception {
        String error = comprobarCampos();
        if (error.isEmpty()) {
            producto = obtenerDatos();
            boolean inserta = producto.getIdProducto() == 0;
            if (inserta) {
                productoDao.insert(producto);
            } else {
                productoDao.update(producto);
            }
            actualizarTabla();
            limpiarCampos();
        } else {
            throw new Exception(error);
        }
    }

    public void buscarProducto() {
    }

    public void actualizarProducto() {
        int fila = componenteTabla.getTbl().getSelectedRow();
        DefaultTableModel modelo = componenteTabla.getModelo();
        componente.getTxtId().setText(String.valueOf(modelo.getValueAt(fila, 0)));
        componente.getTxtNombre().setText(String.valueOf(modelo.getValueAt(fila, 1)));
        componente.getTxtDescripcion().setText(String.valueOf(modelo.getValueAt(fila, 2)));
        componente.getJcUnidadMedida().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 3)));
        componente.getTxtPrecioVenta().setText(String.valueOf(modelo.getValueAt(fila, 4)));
        activarBotones(true, 3);
    }

    public void borrarRegistro() {
        if (Metodos.Ayuda.ventanaMensaje(componenteTabla, "¿Desea borrar este registro?", "Confirmar opción") == 0) {
            int fila = componenteTabla.getTbl().getSelectedRow();
            DefaultTableModel modelo = componenteTabla.getModelo();
            String idStr = String.valueOf(modelo.getValueAt(fila, 0));
            producto = new ProductoDTO(Integer.parseInt(idStr));
            try {
                productoDao.delete(producto);
                conexion.commit();
                ventanaMensaje(componenteTabla, "El producto con id " + idStr + " a sido eliminado", "Se ha eliminado con exito el registro", JOptionPane.INFORMATION_MESSAGE);
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
        List<ProductoDTO> productos;
        try {
            productos = productoDao.select();
            productos.forEach(p -> {
                componenteTabla.getModelo().addRow(
                        new Object[]{
                            p.getIdProducto(),
                            p.getNombre(),
                            p.getDescripcion(),
                            p.getUnidadMedida(),
                            p.getPrecioVenta()});
            });
            componenteTabla.getEtiRegistros().setText("Registros: " + productos.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private ProductoDTO obtenerDatos() {
        String idStr = componente.getTxtId().getText();
        int id = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
        String nombre = componente.getTxtNombre().getText();
        String descripcion = componente.getTxtDescripcion().getText();
        String unidadMedida = (String) componente.getJcUnidadMedida().getSelectedItem();
        double precioVenta = Double.parseDouble(componente.getTxtPrecioVenta().getText());
        return new ProductoDTO(id, nombre, descripcion, unidadMedida, precioVenta);
    }

    private String comprobarCampos() {
        String error = "";
        error += comprobarCampo(componente.getTxtNombre().getText(), "Nombre", MAX_CARACTERES_NOMBRE);
        error += comprobarCampo(componente.getTxtDescripcion().getText(), "Descripcion", MAX_CARACTERES_DESCRIPCION);
        error += comprobarCampo(componente.getTxtPrecioVenta().getText(), "Precio venta", MAX_CARACTERES_PRECIO_VENTA);
        return error;
    }

    public void limpiarCampos() {
        componente.getTxtId().setText("");
        componente.getTxtNombre().setText("");
        componente.getTxtDescripcion().setText("");
        componente.getJcUnidadMedida().setSelectedIndex(0);
        componente.getTxtPrecioVenta().setText("");
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
        componente.getTxtDescripcion().setEnabled(activar);
        componente.getJcUnidadMedida().setEnabled(activar);
        componente.getTxtPrecioVenta().setEnabled(activar);
    }
}
