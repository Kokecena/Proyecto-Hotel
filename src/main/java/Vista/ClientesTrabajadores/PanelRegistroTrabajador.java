package Vista.ClientesTrabajadores;

import static Metodos.GBCMetodos.addComponentGBLayout;
import Vista.Persona.PanelPersona;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author jovan
 */
public class PanelRegistroTrabajador extends PanelPersona {

    private JTextField txtSueldo;
    private JComboBox<String> jcAcceso;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JComboBox<String> jcEstado;

    public PanelRegistroTrabajador() {
        super("Registro de Trabajadores", new Dimension(380,800));
        initComponents();
        super.initMainPanel();
        initSubPanels();
    }

    private void initSubPanels() {
        addFields();
        addButtons();
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        txtSueldo = new JTextField(18);
        jcAcceso = new JComboBox(new String[]{"ADMINISTRADOR","INVITADO"});
        txtLogin = new JTextField(12);
        txtPassword = new JPasswordField(12);
        jcEstado = new JComboBox(new String[]{"A","D"});
    }

    @Override
    protected void addFields() {
        super.addFields();
        addComponentGBLayout(super.getpCampos(), new JLabel("Sueldo: "), 0, 18, GridBagConstraints.EAST,super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), txtSueldo, 1, 18, GridBagConstraints.NONE, GridBagConstraints.WEST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), new JLabel("Acceso:"), 0, 20, GridBagConstraints.EAST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), jcAcceso, 1, 20, GridBagConstraints.NONE, GridBagConstraints.WEST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), new JLabel("Login:"), 0, 22, GridBagConstraints.EAST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), txtLogin, 1, 22, GridBagConstraints.NONE, GridBagConstraints.WEST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), new JLabel("Password:"), 0, 24, GridBagConstraints.EAST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), txtPassword, 1, 24, GridBagConstraints.NONE, GridBagConstraints.WEST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), new JLabel("Estado:"), 0, 26, GridBagConstraints.EAST, super.getInsetFields());
        addComponentGBLayout(super.getpCampos(), jcEstado, 1, 26, GridBagConstraints.NONE, GridBagConstraints.WEST, super.getInsetFields());
        add(getpCampos());
    }

    public JTextField getTxtSueldo() {
        return txtSueldo;
    }

    public JComboBox<String> getJcAcceso() {
        return jcAcceso;
    }

    public JTextField getTxtLogin() {
        return txtLogin;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JComboBox<String> getJcEstado() {
        return jcEstado;
    }

}
