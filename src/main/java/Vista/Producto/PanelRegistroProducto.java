/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Producto;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static Metodos.GBCMetodos.*;
import Vista.Formularios.PanelRegistro;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 *
 * @author jovan
 */
public class PanelRegistroProducto extends PanelRegistro {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextArea txtDescripcion;
    private JScrollPane jsDescripcion;
    private JComboBox<String> jcUnidadMedida;
    private JTextField txtPrecioVenta;
    private JPanel panelDatos;
    private JPanel panelDescripcion;

    public PanelRegistroProducto(String nombreRegistro, Dimension dimension) {
        super(nombreRegistro, dimension);
        initComponents();
        super.initMainPanel();
        initSubPanels();
    }

    private void initSubPanels() {
        addInfo();
        addButtons();       
    }

    private void initComponents() {
        txtId = new JTextField(3);
        txtId.setHorizontalAlignment(JTextField.CENTER);
        txtNombre = new JTextField(8);
        txtDescripcion = new JTextArea(6, 15);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setMargin(new Insets(5,5,5,5));
        jsDescripcion = new JScrollPane(txtDescripcion);
        jcUnidadMedida = new JComboBox(new String[]{"KG", "Unidad", "LTS", "MINUTOS", "GLOBAL"});
        txtPrecioVenta = new JTextField(8);
    }

    private void addInfo() {
        Insets inset = new Insets(0, 0, 10, 10);
        panelDatos = new JPanel(new GridBagLayout());
        addComponentGBLayout(panelDatos, new JLabel("ID:"), 0, 0, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelDatos, txtId, 2, 0, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelDatos, new JLabel("Nombre:"), 0, 2, GridBagConstraints.EAST,inset);
        addComponentGBLayout(panelDatos, txtNombre, 2, 2, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelDatos, new JLabel("Descripción:"), 0,4, GridBagConstraints.NORTHEAST, inset);
        addComponentGBLayout(panelDatos, jsDescripcion, 2, 4,GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelDatos, new JLabel("Unidad de medida:"), 0,6, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelDatos, jcUnidadMedida, 2,6, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelDatos, new JLabel("Precio de venta:"), 0, 8, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelDatos, txtPrecioVenta, 2, 8, GridBagConstraints.WEST, inset);
        add(panelDatos);
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextArea getTxtDescripcion() {
        return txtDescripcion;
    }

    public JScrollPane getJsDescripcion() {
        return jsDescripcion;
    }

    public JComboBox<String> getJcUnidadMedida() {
        return jcUnidadMedida;
    }

    public JTextField getTxtPrecioVenta() {
        return txtPrecioVenta;
    }
    
}
