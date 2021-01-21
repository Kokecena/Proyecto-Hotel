/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Logica;

import Controlador.ControladorCliente;
import Controlador.ControladorHabitacion;
import Controlador.ControladorLogin;
import Controlador.ControladorProducto;
import Controlador.ControladorTrabajador;
import Metodos.Ayuda;
import static Modelo.Datos.Conexion.getConnection;
import Modelo.Datos.TrabajadorDaoJDBC;
import Modelo.domain.TrabajadorDTO;
import Vista.ClientesTrabajadores.PanelClientes;
import Vista.ClientesTrabajadores.PanelTrabajador;
import Vista.Login.VentanaLogin;
import Vista.Pagos.VentanaPagos;
import Vista.Producto.VentanaProducto;
import Vista.ReservasConsumos.VentanaReservasConsumos;
import Vista.Sistema.VentanaSistema;
import Vista.habitacion.VentanaHabitacion;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jovan
 */
public class LogicaSistema {

    private VentanaSistema componente;
    private Connection conexion;

    public LogicaSistema(VentanaSistema componente, Connection conexion) {
        this.componente = componente;
        this.conexion = conexion;
    }

    public void ventanaClientes() {
        PanelClientes pc = new PanelClientes();
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
        PanelTrabajador pt = new PanelTrabajador();
        LogicaTrabajador lt = new LogicaTrabajador(pt, conexion);
        ControladorTrabajador ct = new ControladorTrabajador(lt, pt);
        pt.setVisible(true);
        componente.getEscritorio().add(pt);
    }

    public void ventanaConsumos() {
        VentanaReservasConsumos vrc = new VentanaReservasConsumos();
        vrc.setVisible(true);
        componente.getEscritorio().add(vrc);
    }

    public void ventanaPagos() {
        VentanaPagos ppg = new VentanaPagos();
        ppg.setVisible(true);
        componente.getEscritorio().add(ppg);
    }

}
