/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formularios;

import java.awt.BorderLayout;
import java.awt.Component;
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

    public static final int CON_BOTONES_BUSQUEDA_ELIMINAR = 1;
    public static final int CON_BOTON_ELIMINAR = 2;
    public static final int CON_BOTON_BUSQUEDA = 3;
    public static final int SIN_BOTONES = 0;

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
    }

    public PanelTabla(String nombreTabla, String nombresColumnas[], int disposicionBotones) {
        this.nombreTabla = nombreTabla;
        this.nombresColumnas = nombresColumnas;
        initMainPanel();
        initComponents(disposicionBotones);
    }

    private void initMainPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(nombreTabla));
    }

    private void initComponents() {
        modelo = new DefaultTableModel(null, nombresColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl = new JTable();
        tbl.setFocusable(false);
        tbl.setModel(modelo);
        jsc = new JScrollPane(tbl);
        etiRegistros = new JLabel("Registros: ");
        initButtons(1);
        add(Box.createHorizontalStrut(7), BorderLayout.WEST);
        add(Box.createHorizontalStrut(7), BorderLayout.EAST);
        add(jsc, BorderLayout.CENTER);
        addRegisters();
    }

    private void initComponents(int disposicionBotones) {
        modelo = new DefaultTableModel(null, nombresColumnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tbl = new JTable(modelo);
        jsc = new JScrollPane(tbl);
        etiRegistros = new JLabel("Registros: ");
        initButtons(disposicionBotones);
        add(Box.createHorizontalStrut(7), BorderLayout.WEST);
        add(Box.createHorizontalStrut(7), BorderLayout.EAST);
        add(jsc, BorderLayout.CENTER);
        addRegisters();
    }

    private void initButtons(int disposicionBotones) {
        switch (disposicionBotones) {
            case 1:
                txtBuscar = new JTextField(12);
                btnBuscar = new JButton("Buscar");
                btnEliminar = new JButton("Eliminar");
                addOptions(new JLabel("Buscar:"), txtBuscar, btnBuscar, btnEliminar);
                break;
            case 2:
                btnEliminar = new JButton("Eliminar");
                addOptions(btnEliminar);
                break;
            case 3:
                txtBuscar = new JTextField(12);
                btnBuscar = new JButton("Buscar");
                addOptions(new JLabel("Buscar:"), txtBuscar, btnBuscar);
            default:
        }
        if (disposicionBotones != 0) {
            addActionCommands(disposicionBotones);
        }
    }

    private void addActionCommands(int botones) {
        switch (botones) {
            case 1:
                btnBuscar.setActionCommand("search");
                btnEliminar.setActionCommand("delete");
                break;
            case 2:
                btnEliminar.setActionCommand("delete");
                break;
            case 3:
                btnBuscar.setActionCommand("search");
                break;
        }
    }

    private void addOptions(Component... c) {
        panelOpciones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        for (Component component : c) {
            panelOpciones.add(component);
        }
        add(panelOpciones, BorderLayout.NORTH);
    }

    private void addRegisters() {
        panelRegistros = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 7));
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
