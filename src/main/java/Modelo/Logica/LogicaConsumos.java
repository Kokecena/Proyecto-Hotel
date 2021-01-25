/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Logica;

import Metodos.Ayuda;
import static Metodos.Ayuda.*;
import Modelo.Datos.ConsumoDAO;
import Modelo.Datos.ConsumoDaoJDBC;
import Modelo.Datos.ProductoDaoJDBC;
import Modelo.domain.ConsumoDTO;
import Modelo.domain.ProductoDTO;
import Vista.Formularios.PanelTabla;
import Vista.ReservasConsumosPagos.PanelRegistroConsumo;
import Vista.ReservasConsumosPagos.VentanaConsumo;
import Vista.Sistema.VentanaSistema;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
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
public class LogicaConsumos {
    
    private final int MAX_CARACTERES_CANTIDAD = 11;
    private final int MAX_CARACTERES_PRECIO_VENTA = 10;
    private JInternalFrame ventanaLista;
    private PanelTabla listaTabla;
    
    private ConsumoDTO consumo = null;
    private ConsumoDAO consumoDao = null;
    
    private final Connection conexion;
    private final PanelRegistroConsumo componente;
    private final PanelTabla componenteTabla;
    private final int idReserva;
    
    private List<ConsumoDTO> consumos;
    
    public LogicaConsumos(VentanaConsumo componente, int idReserva, String nombreCliente, Connection conexion) {
        this.componente = componente.getpRegistroConsumo();
        this.componenteTabla = componente.getpListadoConsumo();
        this.idReserva = idReserva;
        this.conexion = conexion;
        this.consumoDao = new ConsumoDaoJDBC(conexion);
        this.componente.getTxtIdReserva().setText(String.valueOf(idReserva));
        this.componente.getTxtCliente().setText(nombreCliente);
    }
    
    public double calcularImporte() {
        double total = 0;
        for (ConsumoDTO c : consumos) {
            total += c.getCantidad() * c.getPrecioVenta();
        }
        return total;
    }
    
    public void nuevoConsumo() {
        try {
            insertarConsumo();
            conexion.commit();
            ventanaMensaje(componente,
                    consumo.getIdReserva() == 0 ? "Se a registrado con exito el consumo"
                    : "La informacion del consumo con ID " + consumo.getIdReserva() + "\n ha sido actualizada con exito.",
                    consumo.getIdReserva() == 0 ? "¡Registro con exito!"
                    : "¡Actualizacion con exito!",
                    JOptionPane.INFORMATION_MESSAGE);
            actualizarTabla();
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
            ex.printStackTrace(System.out);
        }
    }
    
    private void insertarConsumo() throws SQLException, Exception {
        String error = comprobarCampos();
        if (error.isEmpty()) {
            consumo = obtenerDatos();
            boolean inserta = consumo.getIdConsumo() == 0;
            if (inserta) {
                consumoDao.insert(consumo);
            } else {
                consumoDao.update(consumo);
            }
            actualizarTabla();
            limpiarCampos();
        } else {
            throw new Exception(error);
        }
    }
    
    public void crearVentanaTabla(boolean activo, JInternalFrame ventana, PanelTabla tabla, InternalFrameListener ifl, MouseListener ml) {
        this.listaTabla = tabla;
        this.ventanaLista = ventana;
        componente.getBtnBuscarProducto().setEnabled(!activo);
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
        componente.getTxtIdProducto().setText(String.valueOf(modelo.getValueAt(fila, 0)));
        componente.getTxtProducto().setText(String.valueOf(modelo.getValueAt(fila, 1)));
        componente.getTxtPrecioVenta().setText(String.valueOf(modelo.getValueAt(fila, 4)));
        ventanaLista.doDefaultCloseAction();
    }
    
    public void actualizarConsumo() {
        int fila = componenteTabla.getTbl().getSelectedRow();
        DefaultTableModel modelo = componenteTabla.getModelo();
        componente.getTxtIdConsumo().setText(String.valueOf(modelo.getValueAt(fila, 0)));
        componente.getTxtIdProducto().setText(String.valueOf(modelo.getValueAt(fila, 1)));
        componente.getTxtProducto().setText(String.valueOf(modelo.getValueAt(fila, 2)));
        componente.getTxtCantidad().setText(String.valueOf(modelo.getValueAt(fila, 3)));
        componente.getTxtPrecioVenta().setText(String.valueOf(modelo.getValueAt(fila, 4)));
        componente.getCbEstado().setSelectedItem(String.valueOf(modelo.getValueAt(fila, 5)));
        activarBotones(true, 3);
    }
    
    public void borrarRegistro() {
        if (Metodos.Ayuda.ventanaMensaje(componenteTabla, "¿Desea borrar este registro?", "Confirmar opción") == 0) {
            int fila = componenteTabla.getTbl().getSelectedRow();
            DefaultTableModel modelo = componenteTabla.getModelo();
            String idStr = String.valueOf(modelo.getValueAt(fila, 0));
            consumo = new ConsumoDTO(Integer.parseInt(idStr));
            try {
                consumoDao.delete(consumo);
                conexion.commit();
                ventanaMensaje(componenteTabla, "El consumo con id " + idStr + " a sido eliminado", "Se ha eliminado con exito el registro", JOptionPane.INFORMATION_MESSAGE);
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
        try {
            consumos = consumoDao.select(idReserva);
            consumos.forEach(c -> {
                componenteTabla.getModelo().addRow(
                        new Object[]{
                            c.getIdConsumo(),
                            c.getIdProducto(),
                            obtenerProducto(c.getIdProducto()).getNombre(),
                            c.getCantidad(),
                            c.getPrecioVenta(),
                            c.getEstado()});
            });
            componenteTabla.getEtiDato().setText("Consumo Total: $" + calcularImporte());
            componenteTabla.getEtiRegistros().setText("Registros: " + consumos.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    private ProductoDTO obtenerProducto(int idProducto) {
        ProductoDTO producto = null;
        try {
            producto = new ProductoDaoJDBC().search(idProducto);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return producto;
    }
    
    private ConsumoDTO obtenerDatos() {
        String idStr = componente.getTxtIdConsumo().getText();
        int idConsumo = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
        int idReserva = Integer.parseInt(componente.getTxtIdReserva().getText());
        int idProducto = Integer.parseInt(componente.getTxtIdProducto().getText());
        int cantidad = Integer.parseInt(componente.getTxtCantidad().getText());
        double precioVenta = Double.parseDouble(componente.getTxtPrecioVenta().getText());
        String estado = (String) componente.getCbEstado().getSelectedItem();
        return new ConsumoDTO(idConsumo, idReserva, idProducto, cantidad, precioVenta, estado);
    }
    
    private String comprobarCampos() {
        String error = "";
        error += comprobarCampo(componente.getTxtCantidad().getText(), "Cantidad", MAX_CARACTERES_CANTIDAD, COMPROBAR_NUMEROS_ENTEROS);
        error += comprobarCampo(componente.getTxtCantidad().getText(), "Cantidad", MAX_CARACTERES_PRECIO_VENTA, COMPROBAR_NUMEROS_ENTEROS);
        return error;
    }
    
    public void limpiarCampos() {
        componente.getTxtIdConsumo().setText("");
        componente.getTxtIdProducto().setText("");
        componente.getTxtProducto().setText("");
        componente.getTxtCantidad().setText("");
        componente.getTxtPrecioVenta().setText("");
        componente.getCbEstado().setSelectedIndex(0);
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
        componente.getTxtIdProducto().setEnabled(false);
        componente.getTxtProducto().setEnabled(false);
        componente.getTxtIdConsumo().setEnabled(false);
        componente.getTxtIdReserva().setEnabled(false);
        componente.getTxtCliente().setEnabled(false);
        componente.getTxtCantidad().setEnabled(activar);
        componente.getTxtPrecioVenta().setEnabled(activar);
        componente.getCbEstado().setEnabled(activar);
        componente.getBtnBuscarProducto().setEnabled(activar);
    }
}
