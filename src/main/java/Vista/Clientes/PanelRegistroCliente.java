package Vista.Clientes;

import javax.swing.JLabel;
import javax.swing.JTextField;
import static Metodos.GBCMetodos.*;
import Vista.Persona.PanelPersona;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;

/**
 *
 * @author jovan
 */
public class PanelRegistroCliente extends PanelPersona {

    private JTextField txtCodigo;

    public PanelRegistroCliente() {
        super();
        initMainPanel();
        initSubPanels();
    }

    @Override
    protected void initSubPanels() {
        super.initSubPanels();
    }

    @Override
    protected void initMainPanel() {
        super.initMainPanel();
        setBorder(BorderFactory.createTitledBorder("Registro de Clientes"));
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
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(JTextField txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

}
