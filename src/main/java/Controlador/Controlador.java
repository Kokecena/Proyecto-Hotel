package Controlador;

import Vista.ClientesTrabajadores.VentanaClientesTrabajadores;
import Vista.Pagos.PanelPagos;
import Vista.Producto.PanelProducto;
import Vista.ReservasConsumos.VentanaReservasConsumos;
import Vista.Sistema.VentanaSistema;
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
            VentanaClientesTrabajadores vct = new VentanaClientesTrabajadores();
            vista.getEscritorio().add(vct);
            vct.setVisible(true);
        }
        if (e.getSource() == vista.getMenu().getMiHabitaciones()) {
            PanelHabitacion ph = new PanelHabitacion();
            vista.getEscritorio().add(ph);
            ph.setVisible(true);

        }
        if (e.getSource() == vista.getMenu().getMiProductos()) {
            PanelProducto pp = new PanelProducto();
            vista.getEscritorio().add(pp);
            pp.setVisible(true);
        }
       if(e.getSource() == vista.getMenu().getMiReservasConsumos()){
           VentanaReservasConsumos vrc = new VentanaReservasConsumos();
           vista.getEscritorio().add(vrc);
           vrc.setVisible(true);
       }
       if(e.getSource() == vista.getMenu().getMiPagos()){
           PanelPagos ppg = new PanelPagos();
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
