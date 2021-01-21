package Controlador;

import Modelo.Logica.LogicaSistema;
import Modelo.domain.TrabajadorDTO;
import Vista.Sistema.VentanaSistema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 *
 * @author jovan
 */
public class Controlador implements ActionListener {

    private VentanaSistema vista;
    private LogicaSistema modelo;
    private TrabajadorDTO usuario;

    public Controlador(LogicaSistema modelo, VentanaSistema vista, TrabajadorDTO usuario) {
        this.vista = vista;
        this.modelo = modelo;
        this.usuario = usuario;
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
                modelo.ventanaConsumos();
                break;
            case "trabajador":
                modelo.ventanaTrabajador();
                break;
        }
    }

    private void actionListener(ActionListener e) {
        if (usuario.getAcceso().equals("ADMINISTRADOR")) {
            vista.getMenu().getMiClientes().addActionListener(e);
            vista.getMenu().getMiHabitaciones().addActionListener(e);
            vista.getMenu().getMiProductos().addActionListener(e);
            vista.getMenu().getMiUsuariosAccesos().addActionListener(e);
            vista.getMenu().getMiReservasConsumos().addActionListener(e);
            vista.getMenu().getMiPagos().addActionListener(e);
        } else {
            vista.getMenu().getmArchivo().setEnabled(false);
            vista.getMenu().getmConfiguraciones().setEnabled(false);
            vista.getMenu().getMiClientes().addActionListener(e);
            vista.getMenu().getMiReservasConsumos().addActionListener(e);
            vista.getMenu().getMiPagos().addActionListener(e);
        }
    }

}
