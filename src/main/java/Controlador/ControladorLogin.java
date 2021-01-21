/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Logica.LogicaLogin;
import Vista.Login.VentanaLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jovan
 */
public class ControladorLogin implements ActionListener {

    private VentanaLogin vista;
    private LogicaLogin modelo;

    public ControladorLogin(VentanaLogin vista, LogicaLogin modelo) {
        this.vista = vista;
        this.modelo = modelo;
        actionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "login":
                modelo.iniciarSesion();
                break;
            case "cancel":
                System.exit(0);
                break;
        }
    }

    private void actionListener(ActionListener e) {
        vista.getBtnIniciar().addActionListener(e);
        vista.getBtnCancelar().addActionListener(e);

    }
}
