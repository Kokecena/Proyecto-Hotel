/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Pagos;

import Vista.Formularios.PanelTabla;
import Vista.ReservasConsumos.PanelRegistroReserva;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class PanelPagos extends JInternalFrame {

    private PanelRegistroPagos pRegistroPagos;
    private PanelTabla pListadoPagos;
    private PanelTabla pListadoConsumos;
    private final Dimension TAMANO_TABLA = new Dimension(500,200);
    private JPanel pListados;
    private JPanel pVentana;
    private JPanel pCampos;

    public PanelPagos() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Pagos");
        setLayout(new BorderLayout());
        initComponents();
        addComponents();
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        //setSize(380, 650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
    
    private void addComponents(){
        pListados.add(pListadoConsumos);
        pListados.add(Box.createVerticalStrut(10));
        pListados.add(pListadoPagos);
        pVentana.add(pRegistroPagos);
        pVentana.add(Box.createHorizontalStrut(10));
        pVentana.add(pListados);
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pVentana, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    private void initComponents() {
        pRegistroPagos = new PanelRegistroPagos();
        pListadoPagos = new PanelTabla("Listado de pagos",new String[]{"Test 1", "Test 2", "Test 3", "Test 4"},PanelTabla.SIN_BOTONES);
        pListadoConsumos = new PanelTabla("Listado de consumos",new String[]{"Test 1", "Test 2", "Test 3", "Test 4"},PanelTabla.SIN_BOTONES);
        pListadoPagos.setPreferredSize(TAMANO_TABLA);
        pListadoConsumos.setPreferredSize(TAMANO_TABLA);
        pListados = new JPanel();
        pCampos = new JPanel();
        pVentana = new JPanel();
        pListados.setLayout(new BoxLayout(pListados, BoxLayout.Y_AXIS));
        pVentana.setLayout(new BoxLayout(pVentana,BoxLayout.X_AXIS));
    }

    public PanelRegistroPagos getpRegistroPagos() {
        return pRegistroPagos;
    }

    public PanelTabla getpListadoPagos() {
        return pListadoPagos;
    }

    public PanelTabla getpListadoConsumos() {
        return pListadoConsumos;
    }
    

}
