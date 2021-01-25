/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumosPagos;

import Vista.Formularios.PanelRegistro;
import static Metodos.GBCMetodos.*;
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
public class PanelRegistroConsumo extends PanelRegistro {

    private JTextField txtIdConsumo;
    private JTextField txtIdReserva;
    private JTextField txtCliente;
    private JTextField txtIdProducto;
    private JTextField txtProducto;
    private JButton btnBuscarProducto;
    private JTextField txtCantidad;
    private JTextField txtPrecioVenta;
    private JComboBox cbEstado;
    private JPanel pCampos;
    private Insets inset;

    public PanelRegistroConsumo() {
        super("Registro de consumos", new Dimension(300, 800));
        initComponents();
        super.initMainPanel();
        initSubPanels();
    }

    private void initSubPanels() {
        addFields();
        addButtons();
    }

    private void initComponents() {
        txtIdConsumo = new JTextField(2);
        txtIdReserva = new JTextField(2);
        txtCliente = new JTextField(12);
        txtIdProducto = new JTextField(2);
        txtProducto = new JTextField(12);
        btnBuscarProducto = new JButton("...");
        btnBuscarProducto.setActionCommand("searchp");
        txtCantidad = new JTextField(8);
        txtPrecioVenta = new JTextField(8);
        cbEstado = new JComboBox(new String[]{"Aceptado", "Cancelado"});
        pCampos = new JPanel(new GridBagLayout());
        inset = new Insets(0, 10, 15, 5);
    }

    private void addFields() {
        addComponentGBLayout(pCampos, new JLabel("ID:"), 0, 0, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, txtIdConsumo, 2, 0, GridBagConstraints.WEST, inset);
        addComponentGBLayout(pCampos, new JLabel("Reserva:"), 0, 2, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, txtIdReserva, 2, 2, GridBagConstraints.WEST, inset);
        addComponentGBLayout(pCampos, txtCliente, 4, 2, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, new JLabel("Producto:"), 0, 4, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, txtIdProducto, 2, 4, GridBagConstraints.WEST, inset);
        addComponentGBLayout(pCampos, txtProducto, 4, 4, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, btnBuscarProducto, 6, 4, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, new JLabel("Cantidad:"), 0, 6, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, txtCantidad, 2, 6, GridBagConstraints.WEST, inset);
        addComponentGBLayout(pCampos, new JLabel("Precio venta:"), 0, 8, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, txtPrecioVenta, 2, 8, GridBagConstraints.WEST, inset);
        addComponentGBLayout(pCampos, new JLabel("Estado:"), 0, 10, GridBagConstraints.EAST, inset);
        addComponentGBLayout(pCampos, cbEstado, 2, 10, GridBagConstraints.EAST, inset);
        add(pCampos);
    }

    public JTextField getTxtIdConsumo() {
        return txtIdConsumo;
    }

    public JTextField getTxtIdReserva() {
        return txtIdReserva;
    }

    public JTextField getTxtCliente() {
        return txtCliente;
    }

    public JTextField getTxtIdProducto() {
        return txtIdProducto;
    }

    public JTextField getTxtProducto() {
        return txtProducto;
    }

    public JButton getBtnBuscarProducto() {
        return btnBuscarProducto;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    public JTextField getTxtPrecioVenta() {
        return txtPrecioVenta;
    }

    public JComboBox getCbEstado() {
        return cbEstado;
    }

}
