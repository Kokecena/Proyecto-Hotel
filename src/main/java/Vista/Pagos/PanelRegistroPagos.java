package Vista.Pagos;

import Vista.Formularios.PanelRegistro;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jovan
 */
public class PanelRegistroPagos extends PanelRegistro {

    private JTextField txtId;
    private JTextField txtIdReserva;
    private JTextField txtCliente;
    private JTextField txtTotalReserva;
    private JTextField txtIdHabitacion;
    private JTextField txtIgv;
    private JTextField txtHabitacion;
    private JComboBox<String> cbComprobante;
    private JTextField txtNumComprobante;
    private JTextField txtTotalPago;
    private JDateChooser dcFechaEmision;
    private JDateChooser dcFechaPago;
    private JPanel pCampos;
    private Insets inset;

    public PanelRegistroPagos() {
        super("Registro de pagos",new Dimension(300,800));
        initComponents();
        super.initMainPanel();
        initSubPanels();
    }

    private void initSubPanels() {
        addFields();
        addButtons();
    }

    private void initComponents() {
        txtId = new JTextField(2);
        txtId.setHorizontalAlignment(JTextField.CENTER);
        txtIdReserva = new JTextField(2);
        txtIdReserva.setHorizontalAlignment(JTextField.CENTER);
        txtCliente = new JTextField(15);
        txtTotalReserva = new JTextField(5);
        txtIdHabitacion = new JTextField(2);
        txtIdHabitacion.setHorizontalAlignment(JTextField.CENTER);
        txtHabitacion = new JTextField(15);
        txtNumComprobante = new JTextField(5);
        txtNumComprobante.setHorizontalAlignment(JTextField.CENTER);
        txtIgv = new JTextField();
        txtTotalPago = new JTextField();
        cbComprobante = new JComboBox(new String[]{"Boleto", "Factura", "Ticket", "Otro"});
        dcFechaEmision = new JDateChooser();
        dcFechaPago = new JDateChooser();
        pCampos = new JPanel(new GridBagLayout());
        inset = new Insets(5, 5, 0, 0);
    }

    private void addFields() {
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("ID:"), 0, 0, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtId, 2, 0, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Reserva:"), 0, 2, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtIdReserva, 2, 2, GridBagConstraints.WEST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtCliente, 4, 2, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Total Reserva:"), 0, 4, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtTotalReserva, 2, 4, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Habitación:"), 0, 6, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtIdHabitacion, 2, 6, GridBagConstraints.WEST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtHabitacion, 4, 6, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Tipo Comprobante:"), 0, 8, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, cbComprobante, 4, 8, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Num. Comprobante:"), 0, 10, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtNumComprobante, 4, 10, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Igv:"), 0, 12, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtIgv, 4, 12, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Total Pago:"), 0, 14, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtTotalPago, 4, 14, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Fecha Emisión:"), 0, 16, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, dcFechaEmision, 4, 16, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Fecha Pago:"), 0, 18, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, dcFechaPago, 4, 18, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, inset);

        add(pCampos);
    }

    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtIdReserva() {
        return txtIdReserva;
    }

    public JTextField getTxtCliente() {
        return txtCliente;
    }

    public JTextField getTxtTotalReserva() {
        return txtTotalReserva;
    }

    public JTextField getTxtIdHabitacion() {
        return txtIdHabitacion;
    }

    public JTextField getTxtIgv() {
        return txtIgv;
    }

    public JTextField getTxtHabitacion() {
        return txtHabitacion;
    }

    public JComboBox<String> getCbComprobante() {
        return cbComprobante;
    }

    public JTextField getTxtNumComprobante() {
        return txtNumComprobante;
    }

    public JTextField getTxtTotalPago() {
        return txtTotalPago;
    }

    public JDateChooser getDcFechaEmision() {
        return dcFechaEmision;
    }

    public JDateChooser getDcFechaPago() {
        return dcFechaPago;
    }
    
}
