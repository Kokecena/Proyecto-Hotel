/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Logica.LogicaPagos;
import Vista.ReservasConsumosPagos.VentanaPagos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author jovan
 */
public class ControladorPago implements ActionListener {
    
    public VentanaPagos vista;
    public LogicaPagos modelo;
    
    private class SeleccionarFila extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
            modelo.seleccionarRegistro();
        }
    }
    
    public ControladorPago(LogicaPagos modelo,VentanaPagos vista){
        this.vista = vista;
        this.modelo = modelo;
        actionListener(this);
        mouseListener();
        modelo.activarBotones(false, 1);
        modelo.actualizarTabla();
    }
    
    private void actionListener(ActionListener e){
        vista.getpRegistroPagos().getBtnNuevo().addActionListener(e);
        vista.getpRegistroPagos().getBtnGuardar().addActionListener(e);
        vista.getpRegistroPagos().getBtnCancelar().addActionListener(e);
        vista.getpListadoPagos().getBtnEliminar().addActionListener(e);
        vista.getpListadoPagos().getOpcionUno().addActionListener(e);
        vista.getpListadoPagos().getOpcionDos().addActionListener(e);
    }
    
    private void mouseListener(){
        vista.getpListadoPagos().getTbl().addMouseListener(new SeleccionarFila());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch(comando){
            case "new":
                modelo.activarBotones(true, 1);
                break;
            case "save":
                modelo.nuevoPago();
                break;
            case "cancel":
                modelo.activarBotones(false, 1);
                modelo.limpiarCampos();
                break;
            case "delete":
                modelo.borrarRegistro();
                break;
            case "optionone":
                modelo.imprimirComprobante();
                break;
            case "optiontwo":
                modelo.imprimirComprobanteConsumo();
                break;
        }
    }
    
}
