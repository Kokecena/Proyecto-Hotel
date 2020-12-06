/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ClientesTrabajadores;

import Metodos.Iconos;
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
        pestanas.addTab("Clientes",Iconos.ICONO_PESTANA_CLIENTES, panelClientes);
        pestanas.addTab("Trabajadores",Iconos.ICONO_PESTANA_TRABAJADORES, panelTrabajador);
        add(pestanas);
    }

    private void initFrame() {
        setTitle("Clientes y Trabajadores");
        setFrameIcon(Iconos.ICONO_SUBMENU_CLIENTES_Y_TRABAJADORES);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
}
