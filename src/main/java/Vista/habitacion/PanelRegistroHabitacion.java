package Vista.habitacion;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
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
public class PanelRegistroHabitacion extends PanelRegistro {

    private JTextField txtId;
    private JTextField txtNumero;
    private JComboBox<Integer> jcPiso;
    private JTextArea txaDescripcion;
    private JScrollPane jsDescripcion;
    private JTextArea txaCaracteristicas;
    private JScrollPane jsCaracteristicas;
    private JTextField txtPrecioDiario;
    private JComboBox<String> jcEstado;
    private JComboBox<String> jcTipo;
    private JPanel panelInformacion;
    private JPanel panelDescripcion;
    private JPanel panelEstado;
    private GridBagLayout gbl;
    private Insets inset;

    public PanelRegistroHabitacion(String titulo, Dimension dimension) {
        super(titulo,dimension);
        initComponents();
        super.initMainPanel();
        initSubPanels();
    }

    private void initSubPanels() {
        add(Box.createVerticalGlue());
        addInfo();
        addDescription();
        addState();
        addButtons();
        add(Box.createVerticalGlue());
    }

    private void initComponents() {
        gbl = new GridBagLayout();
        inset = new Insets(5, 5, 5, 5);
        txtId = new JTextField();
        txtId.setHorizontalAlignment(JTextField.CENTER);
        txtNumero = new JTextField(12);
        txtPrecioDiario = new JTextField();
        txaCaracteristicas = new JTextArea(4, 12);
        txaCaracteristicas.setLineWrap(true);
        txaDescripcion = new JTextArea(4, 12);
        txaDescripcion.setLineWrap(true);
        jsCaracteristicas = new JScrollPane(txaCaracteristicas);
        jsDescripcion = new JScrollPane(txaDescripcion);
        jcPiso = new JComboBox(new Integer[]{1, 2, 3, 4, 5, 6, 7});
        jcEstado = new JComboBox(new String[]{"Disponible", "Ocupado", "Mantenimiento"});
        jcTipo = new JComboBox(new String[]{"Individual", "Matrimonial", "Familiar", "Presidencial"});
    }

    private void addInfo() {
        //---------- Box informacion -------------------
        panelInformacion = new JPanel(gbl);
        addComponentGBLayout(panelInformacion, new JLabel("ID:"), 0, 0, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelInformacion, txtId, 2, 0, 1, 1, 1.0, GridBagConstraints.NONE, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelInformacion, new JLabel("Número:"), 0, 2, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelInformacion, txtNumero, 2, 2, 1, 1, 1.0, GridBagConstraints.NONE, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelInformacion, new JLabel("Piso:"), 0, 4,GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelInformacion, jcPiso, 2, 4, 1, 1, 1.0, GridBagConstraints.NONE, GridBagConstraints.WEST, inset);
        add(panelInformacion);
    }

    private void addDescription() {
        panelDescripcion = new JPanel(gbl);
        addComponentGBLayout(panelDescripcion, new JLabel("Descripción:"), 0, 0, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelDescripcion, jsDescripcion, 2, 0, 3, 3, 1.0, GridBagConstraints.NONE, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelDescripcion, new JLabel("Caracteristicas:"), 0, 5,GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelDescripcion, jsCaracteristicas, 2, 5, 3, 3, 1.0, GridBagConstraints.NONE, GridBagConstraints.WEST, inset);
        add(panelDescripcion);
    }

    private void addState() {
        panelEstado = new JPanel(gbl);
        addComponentGBLayout(panelEstado, new JLabel("Precio diario:"), 0, 0,GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelEstado, txtPrecioDiario, 2, 0, 1, 1, 1.0, GridBagConstraints.HORIZONTAL, inset);
        addComponentGBLayout(panelEstado, new JLabel("Estado:"), 0, 2, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelEstado, jcEstado, 2, 2, 1, 1, 1.0, GridBagConstraints.HORIZONTAL, inset);
        addComponentGBLayout(panelEstado, new JLabel("Tipo Habitación:"), 0, 4, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelEstado, jcTipo, 2, 4, 1, 1, 1.0, GridBagConstraints.HORIZONTAL, inset);
        add(panelEstado);
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNumero() {
        return txtNumero;
    }

    public JComboBox<Integer> getJcPiso() {
        return jcPiso;
    }

    public JTextArea getTxaDescripcion() {
        return txaDescripcion;
    }

    public JScrollPane getJsDescripcion() {
        return jsDescripcion;
    }

    public JTextArea getTxaCaracteristicas() {
        return txaCaracteristicas;
    }

    public JScrollPane getJsCaracteristicas() {
        return jsCaracteristicas;
    }

    public JTextField getTxtPrecioDiario() {
        return txtPrecioDiario;
    }

    public JComboBox<String> getJcEstado() {
        return jcEstado;
    }

    public JComboBox<String> getJcTipo() {
        return jcTipo;
    }

}
