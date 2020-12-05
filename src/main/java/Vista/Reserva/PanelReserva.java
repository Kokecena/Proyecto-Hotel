/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Reserva;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class PanelReserva extends JInternalFrame {
    
    private PanelRegistroReserva pRegistroReserva;
    
    public PanelReserva(){
        initFrame();
    }
    
    private void initFrame(){
        setTitle("Reserva");
        setLayout(new BorderLayout());
        initComponents();
        setClosable(true);
        setIconifiable(true);
        setSize(380, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistroReserva = new PanelRegistroReserva();
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroReserva, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }
}
