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
public class PanelConsumo extends JPanel {

    private PanelRegistroConsumo pRegistroCcnsumo;

    public PanelConsumo() {
        initPanel();
    }

    private void initPanel() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        pRegistroCcnsumo = new PanelRegistroConsumo();
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroCcnsumo, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    public PanelRegistroConsumo getpRegistroCcnsumo() {
        return pRegistroCcnsumo;
    }

}
