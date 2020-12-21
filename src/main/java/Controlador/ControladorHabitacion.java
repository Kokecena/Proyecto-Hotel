/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Logica.LogicaHabitacion;
import Vista.habitacion.VentanaHabitacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author jovan
 */
public class ControladorHabitacion implements ActionListener {

    private VentanaHabitacion vista;
    private LogicaHabitacion modelo;

    private class SeleccionarFila extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            modelo.actualizarHabitacion();
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
            System.out.println("Se perdio el foco");
        }
    }

    public ControladorHabitacion(LogicaHabitacion modelo, VentanaHabitacion vista) {
        this.vista = vista;
        this.modelo = modelo;
        actionListener(this);
        mouseListener();
        modelo.activarBotones(false,1);
        modelo.actualizarTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "new":
                modelo.activarBotones(true,1);
                break;
            case "save":
                modelo.nuevaHabitacion();
                break;
            case "delete":
                System.out.println("Borrar");
                modelo.borrarRegistro();
                break;
            case "cancel":
                modelo.activarBotones(false,1);
                modelo.limpiarCampos();
                break;
        }
    }

    private void actionListener(ActionListener e) {
        vista.getpListado().getBtnEliminar().addActionListener(e);
        vista.getpRegistro().getBtnNuevo().addActionListener(e);
        vista.getpRegistro().getBtnGuardar().addActionListener(e);
        vista.getpRegistro().getBtnCancelar().addActionListener(e);
    }

    private void mouseListener() {
        vista.getpListado().getTbl().addMouseListener(new SeleccionarFila());
    }
    
}
