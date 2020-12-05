/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formularios;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jovan
 */
public class PanelTabla extends JPanel {

    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private DefaultTableModel modelo;
    private JTable tbl;
    private JScrollPane jsc;
    private JLabel etiRegistros;
    private JPanel panelOpciones;
    private JPanel panelRegistros;
    private final String nombreTabla;
    private final String[] nombresColumnas;

    public PanelTabla(String nombreTabla, String nombresColumnas[]) {
        this.nombreTabla = nombreTabla;
        this.nombresColumnas = nombresColumnas;
        initMainPanel();
        initComponents();
        addActionCommands();
    }
    
    
    
    private void initMainPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(nombreTabla));
    }

    private void initComponents() {
        txtBuscar = new JTextField(12);
        modelo = new DefaultTableModel(null, nombresColumnas);
        tbl= new JTable(modelo);
        jsc = new JScrollPane(tbl);
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");
        etiRegistros = new JLabel("Registros: ");
        addOptions();
        add(Box.createHorizontalStrut(7), BorderLayout.WEST);
        add(Box.createHorizontalStrut(7), BorderLayout.EAST);
        add(jsc, BorderLayout.CENTER);
        addRegisters();
    }
    
    private void addActionCommands(){
        btnBuscar.setActionCommand("search");
        btnEliminar.setActionCommand("delete");
    }

    private void addOptions() {
        panelOpciones = new JPanel(new FlowLayout());
        panelOpciones.add(new JLabel("Buscar:"));
        panelOpciones.add(txtBuscar);
        panelOpciones.add(btnBuscar);
        panelOpciones.add(btnEliminar);
        add(panelOpciones, BorderLayout.NORTH);
    }

    private void addRegisters() {
        panelRegistros = new JPanel(new FlowLayout(FlowLayout.RIGHT, 40, 5));
        panelRegistros.add(etiRegistros);
        add(panelRegistros, BorderLayout.SOUTH);
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public JTable getTbl() {
        return tbl;
    }

    public JScrollPane getJsc() {
        return jsc;
    }

    public JLabel getEtiRegistros() {
        return etiRegistros;
    }

    public JPanel getPanelOpciones() {
        return panelOpciones;
    }
}

