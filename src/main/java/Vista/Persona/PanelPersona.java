/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Persona;

import static Metodos.GBCMetodos.addComponentGBLayout;
import Vista.Formularios.PanelRegistro;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jovan
 */
public class PanelPersona extends PanelRegistro {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtAPaterno;
    private JTextField txtAMaterno;
    private JComboBox<String> jcDoc;
    private JTextField txtNumDoc;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private Insets insetFields;
    private JPanel pCampos;

    public PanelPersona(String titulo, Dimension dimension) {
        super(titulo, dimension);
        initComponents();
    }
    
    protected void initComponents() {
        txtId = new JTextField(3);
        txtId.setHorizontalAlignment(JTextField.CENTER);
        txtNombre = new JTextField(15);
        txtAPaterno = new JTextField(15);
        txtAMaterno = new JTextField(15);
        jcDoc = new JComboBox(new String[]{"DNI", "IFE", "CARNET", "MILITAR", "CARNET EXTRANJERO", "OTROS"});
        txtNumDoc = new JTextField(18);
        txtDireccion = new JTextField(18);
        txtTelefono = new JTextField(18);
        txtEmail = new JTextField(18);
        pCampos = new JPanel(new GridBagLayout());
        insetFields = new Insets(10, 10, 3, 3);
    }
    
    protected void addFields() {
        addComponentGBLayout(pCampos, new JLabel("ID:"), 0, 0, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtId, 1, 0, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("Nombre:"), 0, 2, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtNombre, 1, 2, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("A. Paterno:"), 0, 4, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtAPaterno, 1, 4, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("A. Materno:"), 0, 6, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtAMaterno, 1, 6, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("Tipo Documento:"), 0, 8, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, jcDoc, 1, 8, GridBagConstraints.NONE, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("Num. Documento:"), 0, 10, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtNumDoc, 1, 10, GridBagConstraints.NONE, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("Direccion:"), 0, 12, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtDireccion, 1, 12, GridBagConstraints.NONE, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("Teléfono:"), 0, 14, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtTelefono, 1, 14, GridBagConstraints.NONE, GridBagConstraints.WEST, insetFields);
        addComponentGBLayout(pCampos, new JLabel("Email:"), 0, 16, GridBagConstraints.EAST, insetFields);
        addComponentGBLayout(pCampos, txtEmail, 1, 16, GridBagConstraints.NONE, GridBagConstraints.WEST, insetFields);
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtAPaterno() {
        return txtAPaterno;
    }

    public JTextField getTxtAMaterno() {
        return txtAMaterno;
    }

    public JComboBox<String> getJcDoc() {
        return jcDoc;
    }

    public JTextField getTxtNumDoc() {
        return txtNumDoc;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public Insets getInsetFields() {
        return insetFields;
    }

    public JPanel getpCampos() {
        return pCampos;
    }
    
    
}
