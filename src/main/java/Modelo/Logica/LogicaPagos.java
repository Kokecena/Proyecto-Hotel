/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Logica;

import static Metodos.Ayuda.*;
import Modelo.Datos.PagoDAO;
import Modelo.Datos.PagoDaoJDBC;
import Modelo.domain.PagoDTO;
import Vista.Formularios.PanelTabla;
import Vista.ReservasConsumosPagos.PanelRegistroPagos;
import Vista.ReservasConsumosPagos.VentanaPagos;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author jovan
 */
public class LogicaPagos {

    private final PanelRegistroPagos componente;
    private final PanelTabla tablaPago;
    private final PanelTabla tablaConsumo;
    private final PagoDAO pagoDao;
    private PagoDTO pago;
    private Connection conexion = null;
    private int idReserva;

    public LogicaPagos(VentanaPagos componente, Connection conexion, String idReserva, String idHabitacion, String nombreCliente, String numeroHabitacion, String costoReserva) {
        this.componente = componente.getpRegistroPagos();
        this.tablaPago = componente.getpListadoPagos();
        this.tablaConsumo = componente.getpListadoConsumos();
        this.conexion = conexion;
        this.pagoDao = new PagoDaoJDBC(conexion);
        this.idReserva = Integer.parseInt(idReserva);
        datosReserva(idReserva, idHabitacion, nombreCliente, numeroHabitacion, costoReserva);
        totalPago();
    }

    public void nuevoPago() {
        try {
            insertarPago();
            conexion.commit();
            ventanaMensaje(componente,
                    pago.getIdReserva() == 0 ? "Se a registrado con exito la Reserva"
                    : "La informacion de la Reserva con ID " + pago.getIdReserva() + "\n ha sido actualizada con exito.",
                    pago.getIdReserva() == 0 ? "¡Registro con exito!"
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
            ex.printStackTrace(System.out);
        }
    }

    private void insertarPago() throws SQLException, Exception {
        String error = comprobarCampos();
        if (error.isEmpty()) {
            pago = obtenerDatos();
            boolean inserta = pago.getIdPago() == 0;
            System.out.println(inserta);
            if (inserta) {
                pagoDao.insert(pago);
            } else {
                pagoDao.update(pago);
            }
            actualizarTabla();
            limpiarCampos();
        } else {
            throw new Exception(error);
        }
    }

    private String comprobarCampos() {
        String error = "";
        error += comprobarCampo(componente.getTxtNumComprobante().getText(), "N. comprobante");
        error += comprobarCampo(componente.getDcFechaEmision().getDate() == null ? "" : "0", "Fecha emision");
        error += comprobarCampo(componente.getDcFechaPago().getDate() == null ? "" : "0", "Fecha pago");
        return error;
    }

    public void seleccionarRegistro() {
        int fila = tablaPago.getTbl().getSelectedRow();
        DefaultTableModel modelo = tablaPago.getModelo();
        componente.getTxtId().setText(String.valueOf(modelo.getValueAt(fila, 0)));
        componente.getCbComprobante().setSelectedItem(modelo.getValueAt(fila, 1));
        componente.getTxtNumComprobante().setText(String.valueOf(modelo.getValueAt(fila, 2)));
        componente.getTxtIgv().setText(String.valueOf(modelo.getValueAt(fila, 3)));
        componente.getDcFechaEmision().setDate((Date) modelo.getValueAt(fila, 5));
        componente.getDcFechaPago().setDate((Date) modelo.getValueAt(fila, 6));
        activarBotones(true,1);
    }

    public void actualizarTabla() {
        tablaPago.getModelo().setRowCount(0);
        List<PagoDTO> pagos;
        try {
            pagos = pagoDao.select(idReserva);
            pagos.forEach(p -> {
                tablaPago.getModelo().addRow(
                        new Object[]{
                            p.getIdPago(),
                            p.getTipoComprobante(),
                            p.getNumComprobante(),
                            p.getIgv(),
                            p.getTotalPago(),
                            p.getFechaEmision(),
                            p.getFechaPago()
                        });
            });
            tablaPago.getEtiRegistros().setText("Registros: " + pagos.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public void borrarRegistro() {
        if (Metodos.Ayuda.ventanaMensaje(tablaPago, "¿Desea borrar este registro?", "Confirmar opción") == 0) {
            int fila = tablaPago.getTbl().getSelectedRow();
            DefaultTableModel modelo = tablaPago.getModelo();
            String idStr = String.valueOf(modelo.getValueAt(fila, 0));
            pago = new PagoDTO(Integer.parseInt(idStr));
            try {
                pagoDao.delete(pago);
                conexion.commit();
                ventanaMensaje(tablaPago, "El pago con id " + idStr + " a sido eliminado", "Se ha eliminado con exito el registro", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                actualizarTabla();
            } catch (SQLException ex) {
                ventanaMensaje(tablaPago, ex.getMessage() + "\nHaciendo rollback...", "Ocurrio un error", JOptionPane.INFORMATION_MESSAGE);
                try {
                    conexion.rollback();
                } catch (SQLException ex1) {
                    ex1.printStackTrace(System.out);
                }
            }
        }
    }

    private PagoDTO obtenerDatos() {
        String idStr = componente.getTxtId().getText();
        int id = idStr.isEmpty() ? 0 : Integer.parseInt(idStr);
        int idReserva = Integer.parseInt(componente.getTxtIdReserva().getText());
        String tipoComprobante = (String) componente.getCbComprobante().getSelectedItem();
        String numComprobante = componente.getTxtNumComprobante().getText();
        double igv = Double.parseDouble(componente.getTxtIgv().getText());
        double totalPago = Double.parseDouble(componente.getTxtTotalPago().getText());
        Date fechaReserva = componente.getDcFechaEmision().getDate();
        Date fechaPago = componente.getDcFechaPago().getDate();
        return new PagoDTO(id, idReserva, tipoComprobante, numComprobante, igv, totalPago, fechaReserva, fechaPago);
    }

    private void totalPago() {
        String[] consumo = tablaConsumo.getEtiDato().getText().split(" ");
        double totalConsumo = Double.parseDouble(consumo[2].substring(1));
        consumo = null;
        double totalReserva = Double.parseDouble(componente.getTxtTotalReserva().getText());
        componente.getTxtTotalPago().setText(String.valueOf(totalConsumo + totalReserva));
    }

    public void limpiarCampos() {
        componente.getCbComprobante().setSelectedIndex(0);
        componente.getTxtId().setText("");
        componente.getTxtNumComprobante().setText("");
        componente.getTxtTotalPago().setText("");
        componente.getDcFechaPago().setDate(null);
        componente.getDcFechaEmision().setDate(null);
    }

    private void datosReserva(String idReserva, String idHabitacion, String nombreCliente, String numeroHabitacion, String costoReserva) {
        componente.getTxtIdReserva().setText(idReserva);
        componente.getTxtIdHabitacion().setText(idHabitacion);
        componente.getTxtCliente().setText(nombreCliente);
        componente.getTxtTotalReserva().setText(costoReserva);
        componente.getTxtHabitacion().setText(numeroHabitacion);
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
        componente.getTxtIdReserva().setEnabled(false);
        componente.getTxtTotalReserva().setEnabled(false);
        componente.getTxtCliente().setEnabled(false);
        componente.getTxtIdHabitacion().setEnabled(false);
        componente.getTxtHabitacion().setEnabled(false);
        componente.getTxtTotalPago().setEnabled(false);
        componente.getTxtIgv().setEnabled(activar);
        componente.getCbComprobante().setEnabled(activar);
        componente.getTxtNumComprobante().setEnabled(activar);
        componente.getDcFechaEmision().setEnabled(activar);
        componente.getDcFechaPago().setEnabled(activar);
        tablaPago.getOpcionUno().setEnabled(activar);
        tablaPago.getOpcionDos().setEnabled(activar);
    }

    public void imprimirComprobante() {
        Map p = new HashMap();
        p.put("idpago", componente.getTxtId().getText());
        JasperReport report;
        JasperPrint print;
        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Reportes/rptComprobante.jrxml");
            print = JasperFillManager.fillReport(report, p, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Comprobante");
            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imprimirComprobanteConsumo() {
        JasperReport report;
        JasperPrint print;
        Map p = new HashMap();
        p.put("idpago", componente.getTxtId().getText());
        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/main/java/Reportes/rptComprobanteConsumo.jrxml");
            print = JasperFillManager.fillReport(report, p, conexion);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Comprobante");
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
