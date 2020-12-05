package Controlador;

import Vista.Clientes.PanelClientes;
import Vista.Pagos.PanelPagos;
import Vista.Producto.PanelProducto;
import Vista.Reserva.PanelReserva;
import Vista.Sistema.VentanaSistema;
import Vista.Trabajador.PanelTrabajador;
import Vista.habitacion.PanelHabitacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author jovan
 */
public class Controlador implements ActionListener {

    VentanaSistema vista;

    public Controlador(VentanaSistema vista) {
        this.vista = vista;
        actionListener(this);
    }
    
    public void iniciarVentanaSistema(){
        vista.getVentana().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getMenu().getMiClientes()) {
            PanelClientes pc = new PanelClientes();
            vista.getEscritorio().add(pc);
            pc.show();
        }
        if (e.getSource() == vista.getMenu().getMiHabitaciones()) {
            PanelHabitacion ph = new PanelHabitacion();
            vista.getEscritorio().add(ph);
            ph.show();

        }
        if (e.getSource() == vista.getMenu().getMiProductos()) {
            PanelProducto pp = new PanelProducto();
            vista.getEscritorio().add(pp);
            pp.show();
        }
       if(e.getSource() == vista.getMenu().getMiReservasConsumos()){
           PanelReserva pr = new PanelReserva();
           vista.getEscritorio().add(pr);
           pr.show();
       }
       if(e.getSource() == vista.getMenu().getMiPagos()){
           PanelPagos ppg = new PanelPagos();
           vista.getEscritorio().add(ppg);
           ppg.show();
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
