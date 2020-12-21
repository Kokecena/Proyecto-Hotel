package Controlador;

import static Modelo.Datos.Conexion.getConnection;
import Modelo.Logica.LogicaHabitacion;
import Vista.ClientesTrabajadores.VentanaClientesTrabajadores;
import Vista.Pagos.VentanaPagos;
import Vista.Producto.VentanaProducto;
import Vista.ReservasConsumos.VentanaReservasConsumos;
import Vista.Sistema.VentanaSistema;
import Vista.habitacion.VentanaHabitacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jovan
 */
public class Controlador implements ActionListener {
    
    private Connection conexion;
    private VentanaSistema vista;
    
    public Controlador(VentanaSistema vista) throws SQLException {
        this.vista = vista;
        this.conexion = getConnection();
        if (conexion.getAutoCommit()) {
            conexion.setAutoCommit(false);
        }
        actionListener(this);
    }
    
    public void iniciarVentanaSistema() {
        vista.getVentana().setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getMenu().getMiClientes()) {
            VentanaClientesTrabajadores vct = new VentanaClientesTrabajadores();
            vista.getEscritorio().add(vct);
            vct.setVisible(true);
        }
        if (e.getSource() == vista.getMenu().getMiHabitaciones()) {
            VentanaHabitacion ph = new VentanaHabitacion();
            LogicaHabitacion lh = new LogicaHabitacion(ph, conexion);
            ControladorHabitacion ch = new ControladorHabitacion(lh, ph);
            vista.getEscritorio().add(ph);
            ph.setVisible(true);
            
        }
        if (e.getSource() == vista.getMenu().getMiProductos()) {
            VentanaProducto pp = new VentanaProducto();
            vista.getEscritorio().add(pp);
            pp.setVisible(true);
        }
        if (e.getSource() == vista.getMenu().getMiReservasConsumos()) {
            VentanaReservasConsumos vrc = new VentanaReservasConsumos();
            vista.getEscritorio().add(vrc);
            vrc.setVisible(true);
        }
        if (e.getSource() == vista.getMenu().getMiPagos()) {
            VentanaPagos ppg = new VentanaPagos();
            vista.getEscritorio().add(ppg);
            ppg.setVisible(true);
        }
        
    }
    
    private void actionListener(ActionListener e) {
        vista.getMenu().getMiClientes().addActionListener(e);
        vista.getMenu().getMiHabitaciones().addActionListener(e);
        vista.getMenu().getMiProductos().addActionListener(e);
        vista.getMenu().getMiUsuariosAccesos().addActionListener(e);
        vista.getMenu().getMiReservasConsumos().addActionListener(e);
        vista.getMenu().getMiPagos().addActionListener(e);
    }
    
}
