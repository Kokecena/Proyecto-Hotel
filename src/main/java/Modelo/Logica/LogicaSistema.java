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
import Vista.ReservasConsumosPagos.VentanaPagos;
import Vista.Producto.VentanaProducto;
import Vista.ReservasConsumosPagos.VentanaReserva;
import Vista.Sistema.VentanaSistema;
import Vista.UsuarioActivo.UsuarioActivo;
import Vista.habitacion.VentanaHabitacion;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jovan
 */
public class LogicaSistema {

    private final TrabajadorDTO usuario;
    private final VentanaSistema componente;
    private final Connection conexion;

    public LogicaSistema(VentanaSistema componente, Connection conexion, TrabajadorDTO usuario) {
        this.componente = componente;
        this.conexion = conexion;
        this.usuario = usuario;
    }

    public void ventanaClientes() {
        VentanaClientes pc = new VentanaClientes();
        LogicaCliente lc = new LogicaCliente(pc, conexion);
        ControladorCliente cc = new ControladorCliente(lc, pc);
        VentanaSistema.escritorio.add(pc);
        pc.setVisible(true);
    }

    public void ventanaHabitacion() {
        VentanaHabitacion ph = new VentanaHabitacion();
        LogicaHabitacion lh = new LogicaHabitacion(ph, conexion);
        ControladorHabitacion ch = new ControladorHabitacion(lh, ph);
        VentanaSistema.escritorio.add(ph);
        ph.setVisible(true);
    }

    public void ventanaProducto() {
        VentanaProducto pp = new VentanaProducto();
        LogicaProducto lp = new LogicaProducto(pp, conexion);
        ControladorProducto cp = new ControladorProducto(lp, pp);
        VentanaSistema.escritorio.add(pp);
        pp.setVisible(true);
    }

    public void ventanaTrabajador() {
        VentanaTrabajador pt = new VentanaTrabajador();
        LogicaTrabajador lt = new LogicaTrabajador(pt, conexion);
        ControladorTrabajador ct = new ControladorTrabajador(lt, pt);
        VentanaSistema.escritorio.add(pt);
        pt.setVisible(true);
    }

    public void ventanaReserva() {
        VentanaReserva vr = new VentanaReserva();
        LogicaReserva lr = new LogicaReserva(vr, usuario, conexion);
        ControladorReserva cr = new ControladorReserva(lr, vr);
        VentanaSistema.escritorio.add(vr);
        vr.setVisible(true);
    }

    public void ventanaPagos() {
        VentanaPagos ppg = new VentanaPagos(null);
        VentanaSistema.escritorio.add(ppg);
        ppg.setVisible(true);
    }

    public void ventanaUsuario() {
        UsuarioActivo ua = new UsuarioActivo(usuario);
        VentanaSistema.escritorio.add(ua);
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
