/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ClientesTrabajadores;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

/**
 *
 * @author jovan
 */
public class VentanaClientesTrabajadores extends JInternalFrame {

    private JTabbedPane pestanas;
    private PanelClientes panelClientes;
    private PanelTrabajador panelTrabajador;

    public VentanaClientesTrabajadores() {
        initComponents();
        addComponents();
        initFrame();
    }

    private void initComponents() {
        pestanas = new JTabbedPane();
        panelClientes = new PanelClientes();
        panelTrabajador = new PanelTrabajador();
    }

    private void addComponents() {
        pestanas.addTab("Clientes", panelClientes);
        pestanas.addTab("Trabajadores", panelTrabajador);
        add(pestanas);
    }

    private void initFrame() {
        setTitle("Clientes y Trabajadores");
        setClosable(true);
        setIconifiable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
}
