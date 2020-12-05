/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Persona;

import static Metodos.GBCMetodos.addComponentGBLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jovan
 */
public class PanelPersona extends JPanel {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtAPaterno;
    private JTextField txtAMaterno;
    private JComboBox<String> jcDoc;
    private JTextField txtNumDoc;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private Insets insetFields;
    private JPanel pBotones;
    private JPanel pCampos;

    public PanelPersona() {
        initComponents();
    }
    
    protected void initMainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    protected void initSubPanels() {
        addFields();
        addButtons();
        add(pCampos);
        add(pBotones);
    }
    
    protected void addButtons() {
        pBotones.add(btnNuevo);
        pBotones.add(btnGuardar);
        pBotones.add(btnCancelar);
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
        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
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

    public void setTxtId(JTextField txtId) {
        this.txtId = txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtAPaterno() {
        return txtAPaterno;
    }

    public void setTxtAPaterno(JTextField txtAPaterno) {
        this.txtAPaterno = txtAPaterno;
    }

    public JTextField getTxtAMaterno() {
        return txtAMaterno;
    }

    public void setTxtAMaterno(JTextField txtAMaterno) {
        this.txtAMaterno = txtAMaterno;
    }

    public JComboBox<String> getJcDoc() {
        return jcDoc;
    }

    public void setJcDoc(JComboBox<String> jcDoc) {
        this.jcDoc = jcDoc;
    }

    public JTextField getTxtNumDoc() {
        return txtNumDoc;
    }

    public void setTxtNumDoc(JTextField txtNumDoc) {
        this.txtNumDoc = txtNumDoc;
    }

    public JTextField getTxtDireccion() {
        return txtDireccion;
    }

    public void setTxtDireccion(JTextField txtDireccion) {
        this.txtDireccion = txtDireccion;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public void setTxtTelefono(JTextField txtTelefono) {
        this.txtTelefono = txtTelefono;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(JTextField txtEmail) {
        this.txtEmail = txtEmail;
    }

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public void setBtnNuevo(JButton btnNuevo) {
        this.btnNuevo = btnNuevo;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        this.btnGuardar = btnGuardar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(JButton btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public JPanel getpBotones() {
        return pBotones;
    }

    public void setpBotones(JPanel pBotones) {
        this.pBotones = pBotones;
    }

    public JPanel getpCampos() {
        return pCampos;
    }

    public Insets getInsetFields() {
        return insetFields;
    }

    public void setInsetFields(Insets insetFields) {
        this.insetFields = insetFields;
    }

    public void setpCampos(JPanel pCampos) {
        this.pCampos = pCampos;
    }
}
