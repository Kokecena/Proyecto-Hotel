/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Pagos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovan
 */
public class PanelListadoPagos extends JPanel {

    private JButton btnEliminar;
    private JButton btnSalir;
    private DefaultTableModel tblModelo;
    private JTable tblListadoPagos;
    private JScrollPane jscListadoPagos;
    private JLabel lbRegistros;
    private final String[] nombresColumnas = {"Test 1", "Test 2", "Test 3", "Test 4"};
    private JPanel pBotones;
    private JPanel pRegistros;

    public PanelListadoPagos() {
        initComponents();
        initMainPanel();
        initSubPanels();
    }

    private void initComponents() {
        btnEliminar = new JButton("Eliminar");
        btnSalir = new JButton("Salir");
        tblModelo = new DefaultTableModel(null, nombresColumnas);
        tblListadoPagos = new JTable(tblModelo);
        jscListadoPagos = new JScrollPane(tblListadoPagos);
        lbRegistros = new JLabel("Registros:");
        pBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        pRegistros = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
    }

    private void initMainPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Listado de Pagos"));
    }

    private void initSubPanels() {
        addButtons();
        add(jscListadoPagos, BorderLayout.CENTER);
        addLabel();
    }

    private void addLabel() {
        pRegistros.add(lbRegistros);
        add(pRegistros, BorderLayout.SOUTH);
    }

    private void addButtons() {
        pBotones.add(btnEliminar);
        pBotones.add(btnSalir);
        add(pBotones, BorderLayout.NORTH);
    }

}
