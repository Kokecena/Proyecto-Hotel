package Vista.ClientesTrabajadores;

import javax.swing.JLabel;
import javax.swing.JTextField;
import static Metodos.GBCMetodos.*;
import Vista.Persona.PanelPersona;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

/**
 *
 * @author jovan
 */
public class PanelRegistroCliente extends PanelPersona {

    private JTextField txtCodigo;

    public PanelRegistroCliente() {
        super("Registro de clientes",new Dimension(380,800));
        initComponents();
        super.initMainPanel();
        initSubPanels();
    }

    private void initSubPanels(){
        addFields();
        addButtons();
    }
    
    @Override
    protected void initComponents() {
        super.initComponents();
        txtCodigo = new JTextField(18);
    }

    @Override
    protected void addFields() {
        super.addFields();
        addComponentGBLayout(super.getpCampos(), new JLabel("Codigo: "), 0, 18, GridBagConstraints.EAST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), txtCodigo, 1, 18, GridBagConstraints.NONE, GridBagConstraints.WEST, super.getInsetFields());
        add(getpCampos());
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

}
