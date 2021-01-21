/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Logica.LogicaTrabajador;
import Vista.ClientesTrabajadores.PanelTrabajador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author jovan
 */
public class ControladorTrabajador implements ActionListener {
    private PanelTrabajador vista;
    private LogicaTrabajador modelo;

    private class SeleccionarFila extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            modelo.actualizarTrabajador();
        }
    }

    public ControladorTrabajador(LogicaTrabajador modelo, PanelTrabajador vista) {
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
                modelo.nuevoTrabajador();
                break;
            case "delete":
                System.out.println("Borrar");
                modelo.borrarRegistro();
                break;
            case "cancel":
                modelo.activarBotones(false, 1);
                modelo.limpiarCampos();
                break;
        }
    }

    private void actionListener(ActionListener e) {
        vista.getpListadoTrabajador().getBtnEliminar().addActionListener(e);
        vista.getpRegistroTrabajador().getBtnNuevo().addActionListener(e);
        vista.getpRegistroTrabajador().getBtnGuardar().addActionListener(e);
        vista.getpRegistroTrabajador().getBtnCancelar().addActionListener(e);
    }

    private void mouseListener() {
        vista.getpListadoTrabajador().getTbl().addMouseListener(new SeleccionarFila());
    }
}
