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
public class PanelListadoConsumos extends JPanel {

    private DefaultTableModel tblModelo;
    private JTable tblListadoConsumos;
    private JScrollPane jscListadoConsumos;
    private JLabel lbRegistros;
    private final String[] nombresColumnas = {"Test 1", "Test 2", "Test 3", "Test 4"};
    private JPanel pRegistros;

    public PanelListadoConsumos() {
        initComponents();
        initMainPanel();
        initSubPanels();
    }

    private void initComponents() {
        tblModelo = new DefaultTableModel(null, nombresColumnas);
        tblListadoConsumos = new JTable(tblModelo);
        jscListadoConsumos = new JScrollPane(tblListadoConsumos);
        lbRegistros = new JLabel("Registros:");
        pRegistros = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 5));
    }

    private void initMainPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Listado de Consumos"));
    }

    private void initSubPanels() {
        add(jscListadoConsumos, BorderLayout.CENTER);
        addLabel();
    }

    private void addLabel() {
        pRegistros.add(lbRegistros);
        add(pRegistros, BorderLayout.SOUTH);
    }
}
