/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Logica.LogicaConsumos;
import Vista.ReservasConsumosPagos.VentanaConsumo;
import Vista.ReservasConsumosPagos.VentanaListadoProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author jovan
 */
public class ControladorConsumos implements ActionListener {

    private final VentanaConsumo vista;
    private final LogicaConsumos modelo;
    private int clics;

    public ControladorConsumos(LogicaConsumos modelo, VentanaConsumo vista) {
        this.vista = vista;
        this.modelo = modelo;
        actionListener(this);
        mouseListener();
        modelo.activarBotones(false, 1);
        modelo.actualizarTabla();
    }

    private class SeleccionarFila extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            modelo.actualizarConsumo();
        }
    }

    private class CerrarVentana extends InternalFrameAdapter {

        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
            super.internalFrameClosing(e); //To change body of generated methods, choose Tools | Templates.
            vista.getpRegistroConsumo().getBtnBuscarProducto().setEnabled(true);
        }
    }

    private class SeleccionarRegistro extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            clics = e.getClickCount();
            if (clics == 2) {
                modelo.seleccionarRegistro();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "new":
                modelo.activarBotones(true, 1);
                break;
            case "save":
                modelo.nuevoConsumo();
                break;
            case "delete":
                System.out.println("Borrar");
                modelo.borrarRegistro();
                break;
            case "cancel":
                modelo.activarBotones(false, 1);
                modelo.limpiarCampos();
                break;
            case "searchp":
                VentanaListadoProductos vlp = new VentanaListadoProductos();
                modelo.crearVentanaTabla(true, vlp, vlp.getpProductos(), new CerrarVentana(), new SeleccionarRegistro());
                break;
        }
    }

    private void actionListener(ActionListener e) {
        vista.getpListadoConsumo().getBtnEliminar().addActionListener(e);
        vista.getpRegistroConsumo().getBtnBuscarProducto().addActionListener(e);
        vista.getpRegistroConsumo().getBtnNuevo().addActionListener(e);
        vista.getpRegistroConsumo().getBtnGuardar().addActionListener(e);
        vista.getpRegistroConsumo().getBtnCancelar().addActionListener(e);
    }

    private void mouseListener() {
        vista.getpListadoConsumo().getTbl().addMouseListener(new SeleccionarFila());
    }

}
