/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumos;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class PanelReserva extends JPanel {
    
    private PanelRegistroReserva pRegistroReserva;
    
    public PanelReserva(){
        initPanel();
    }
    
    private void initPanel(){
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        pRegistroReserva = new PanelRegistroReserva();
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroReserva, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    public PanelRegistroReserva getpRegistroReserva() {
        return pRegistroReserva;
    }
    
    
}
