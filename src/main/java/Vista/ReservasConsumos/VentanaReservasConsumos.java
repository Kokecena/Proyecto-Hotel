/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumos;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 *
 * @author jovan
 */
public class VentanaReservasConsumos extends JInternalFrame {

    private JTabbedPane pestanas;
    private PanelReserva panelReserva;
    private PanelConsumo panelConsumo;
    
    public VentanaReservasConsumos() {
        initComponents();
        addComponents();
        initFrame();
    }

    private void initFrame() {
        setTitle("Reservas y consumos");
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pestanas = new JTabbedPane();
        panelReserva = new PanelReserva();
        panelConsumo = new PanelConsumo();
    }

    private void addComponents() {
        pestanas.addTab("Reservas", panelReserva);
        pestanas.addTab("Consumos", panelConsumo);
        add(pestanas);
    }

}
