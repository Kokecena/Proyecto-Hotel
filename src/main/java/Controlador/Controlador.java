package Controlador;

import Modelo.Logica.LogicaSistema;
import Vista.Sistema.VentanaSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jovan
 */
public class Controlador implements ActionListener {

    private VentanaSistema vista;
    private LogicaSistema modelo;

    public Controlador(LogicaSistema modelo, VentanaSistema vista) {
        this.vista = vista;
        this.modelo = modelo;
        modelo.ventanaUsuario();
        actionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "habitacion":
                modelo.ventanaHabitacion();
                break;
            case "productos":
                modelo.ventanaProducto();
                break;
            case "clientes":
                modelo.ventanaClientes();
                break;
            case "pagos":
                modelo.ventanaPagos();
                break;
            case "reservas":
                modelo.ventanaReserva();
                break;
            case "trabajador":
                modelo.ventanaTrabajador();
                break;
        }
    }

    private void actionListener(ActionListener e) {
        modelo.permisosModificar(e);
    }

}
