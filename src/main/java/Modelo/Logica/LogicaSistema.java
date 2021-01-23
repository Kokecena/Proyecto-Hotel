/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Logica;

import Controlador.ControladorCliente;
import Controlador.ControladorHabitacion;
import Controlador.ControladorProducto;
import Controlador.ControladorReserva;
import Controlador.ControladorTrabajador;
import Modelo.domain.TrabajadorDTO;
import Vista.ClientesTrabajadores.VentanaClientes;
import Vista.ClientesTrabajadores.VentanaTrabajador;
import Vista.Pagos.VentanaPagos;
import Vista.Producto.VentanaProducto;
import Vista.ReservasConsumos.VentanaConsumo;
import Vista.ReservasConsumos.VentanaReserva;
import Vista.Sistema.VentanaSistema;
import Vista.UsuarioActivo.UsuarioActivo;
import Vista.habitacion.VentanaHabitacion;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 *
 * @author jovan
 */
public class LogicaSistema {

    private TrabajadorDTO usuario;
    private VentanaSistema componente;
    private Connection conexion;

    public LogicaSistema(VentanaSistema componente, Connection conexion, TrabajadorDTO usuario) {
        this.componente = componente;
        this.conexion = conexion;
        this.usuario = usuario;
    }

    public void ventanaClientes() {
        VentanaClientes pc = new VentanaClientes();
        LogicaCliente lc = new LogicaCliente(pc, conexion);
        ControladorCliente cc = new ControladorCliente(lc, pc);
        pc.setVisible(true);
        componente.getEscritorio().add(pc);
    }

    public void ventanaHabitacion() {
        VentanaHabitacion ph = new VentanaHabitacion();
        LogicaHabitacion lh = new LogicaHabitacion(ph, conexion);
        ControladorHabitacion ch = new ControladorHabitacion(lh, ph);
        ph.setVisible(true);
        componente.getEscritorio().add(ph);
    }

    public void ventanaProducto() {
        VentanaProducto pp = new VentanaProducto();
        LogicaProducto lp = new LogicaProducto(pp, conexion);
        ControladorProducto cp = new ControladorProducto(lp, pp);
        pp.setVisible(true);
        componente.getEscritorio().add(pp);
    }

    public void ventanaTrabajador() {
        VentanaTrabajador pt = new VentanaTrabajador();
        LogicaTrabajador lt = new LogicaTrabajador(pt, conexion);
        ControladorTrabajador ct = new ControladorTrabajador(lt, pt);
        pt.setVisible(true);
        componente.getEscritorio().add(pt);
    }

    public void ventanaReserva() {
        VentanaReserva vr = new VentanaReserva();
        LogicaReserva lr = new LogicaReserva(vr,usuario,conexion);
        ControladorReserva cr = new ControladorReserva(lr,vr);
        vr.setVisible(true);
        componente.getEscritorio().add(vr);
    }

    public void ventanaPagos() {
        VentanaPagos ppg = new VentanaPagos();
        ppg.setVisible(true);
        componente.getEscritorio().add(ppg);
    }

    public void ventanaUsuario() {
        UsuarioActivo ua = new UsuarioActivo(usuario);
        componente.getEscritorio().add(ua);
    }

    public void permisosModificar(ActionListener e) {
        if (usuario.getAcceso().equals("ADMINISTRADOR")) {
            componente.getMenu().getMiClientes().addActionListener(e);
            componente.getMenu().getMiHabitaciones().addActionListener(e);
            componente.getMenu().getMiProductos().addActionListener(e);
            componente.getMenu().getMiUsuariosAccesos().addActionListener(e);
            componente.getMenu().getMiReservasConsumos().addActionListener(e);
            componente.getMenu().getMiPagos().addActionListener(e);
        } else {
            componente.getMenu().getmArchivo().setEnabled(false);
            componente.getMenu().getmConfiguraciones().setEnabled(false);
            componente.getMenu().getMiClientes().addActionListener(e);
            componente.getMenu().getMiReservasConsumos().addActionListener(e);
            componente.getMenu().getMiPagos().addActionListener(e);
        }
    }

}
