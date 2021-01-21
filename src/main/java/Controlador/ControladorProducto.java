/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Logica.LogicaProducto;
import Vista.Producto.VentanaProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author jovan
 */
public class ControladorProducto implements ActionListener {

    private VentanaProducto vista;
    private LogicaProducto modelo;

    private class SeleccionarFila extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            modelo.actualizarProducto();
        }
    }

    public ControladorProducto(LogicaProducto modelo, VentanaProducto vista) {
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
                modelo.nuevoProducto();
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
        vista.getpListadoProducto().getBtnEliminar().addActionListener(e);
        vista.getpRegistroProducto().getBtnNuevo().addActionListener(e);
        vista.getpRegistroProducto().getBtnGuardar().addActionListener(e);
        vista.getpRegistroProducto().getBtnCancelar().addActionListener(e);
    }

    private void mouseListener() {
        vista.getpListadoProducto().getTbl().addMouseListener(new SeleccionarFila());
    }

}
