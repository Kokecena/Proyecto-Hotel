/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Logica.LogicaReserva;
import Vista.ReservasConsumos.VentanaListadoClientes;
import Vista.ReservasConsumos.VentanaListadoHabitaciones;
import Vista.ReservasConsumos.VentanaReserva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author jovan
 */
public class ControladorReserva implements ActionListener {

    private VentanaReserva vista;
    private LogicaReserva modelo;
    private int clics;

    private class SeleccionarFila extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            modelo.actualizarReserva();

        }
    }

    private class CerrarVentana extends InternalFrameAdapter {

        @Override
        public void internalFrameClosing(InternalFrameEvent e) {
            super.internalFrameClosing(e); //To change body of generated methods, choose Tools | Templates.
            if (e.getInternalFrame() instanceof VentanaListadoHabitaciones) {
                vista.getpRegistroReserva().getBtnBuscarHabitacion().setEnabled(true);
            } else {
                vista.getpRegistroReserva().getBtnBuscarCliente().setEnabled(true);
            }
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

    public ControladorReserva(LogicaReserva modelo, VentanaReserva vista) {
        this.vista = vista;
        this.modelo = modelo;
        actionListener(this);
        mouseListener();
        modelo.activarBotones(false, 1);
        modelo.actualizarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "new":
                modelo.activarBotones(true, 1);
                break;
            case "save":
                modelo.nuevaReserva();
                break;
            case "delete":
                modelo.borrarRegistro();
                break;
            case "cancel":
                modelo.activarBotones(false, 1);
                modelo.limpiarCampos();
                break;
            case "buscarh":
                VentanaListadoHabitaciones vlh = new VentanaListadoHabitaciones();
                modelo.crearVentanaTabla(1, true, vlh, vlh.getpHabitaciones(), vista.getpRegistroReserva().getBtnBuscarHabitacion(), new CerrarVentana(), new SeleccionarRegistro());
                break;
            case "buscarc":
                VentanaListadoClientes vlc = new VentanaListadoClientes();
                modelo.crearVentanaTabla(2, true, vlc, vlc.getpClientes(), vista.getpRegistroReserva().getBtnBuscarCliente(), new CerrarVentana(), new SeleccionarRegistro());
                break;
        }
    }

    private void actionListener(ActionListener e) {
        vista.getpListadoReserva().getBtnEliminar().addActionListener(e);
        vista.getpRegistroReserva().getBtnNuevo().addActionListener(e);
        vista.getpRegistroReserva().getBtnGuardar().addActionListener(e);
        vista.getpRegistroReserva().getBtnCancelar().addActionListener(e);
        vista.getpRegistroReserva().getBtnBuscarCliente().addActionListener(e);
        vista.getpRegistroReserva().getBtnBuscarHabitacion().addActionListener(e);
    }

    private void mouseListener() {
        vista.getpListadoReserva().getTbl().addMouseListener(new SeleccionarFila());
//        vista.getListadoHabitaciones().getpHabitaciones().getTbl().addMouseListener(new SeleccionarHabitacion());
    }
}
