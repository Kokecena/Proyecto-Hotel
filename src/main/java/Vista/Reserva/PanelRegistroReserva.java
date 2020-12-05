/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Reserva;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
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
public class PanelRegistroReserva extends JPanel {

    private JTextField txtId;
    private JTextField txtIdHabitacion;
    private JTextField txtIdCliente;
    private JTextField txtIdTrabajador;
    private JTextField txtNumero;
    private JTextField txtCliente;
    private JTextField txtTrabajador;
    private JTextField txtCosto;
    private JButton btnBuscarHabitacion;
    private JButton btnBuscarCliente;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JComboBox<String> cbTipoReserva;
    private JComboBox<String> cbEstadoReserva;
    private JDateChooser dcReserva;
    private JDateChooser dcIngreso;
    private JDateChooser dcSalida;
    private JPanel pBotones;
    private JPanel pCampos;
    private Insets inset;
    private Font fontDc;
    public PanelRegistroReserva() {
        initComponents();
        initMainPanel();
        initSubPanels();
    }

    private void initComponents() {
        txtId = new JTextField(2);
        txtId.setHorizontalAlignment(JTextField.CENTER);
        txtIdHabitacion = new JTextField(2);
        txtIdHabitacion.setHorizontalAlignment(JTextField.CENTER);
        txtIdCliente = new JTextField(2);
        txtIdCliente.setHorizontalAlignment(JTextField.CENTER);
        txtIdTrabajador = new JTextField(2);
        txtIdTrabajador.setHorizontalAlignment(JTextField.CENTER);
        txtNumero = new JTextField(2);
        txtCliente = new JTextField(15);
        txtTrabajador = new JTextField(15);
        txtCosto = new JTextField();
        btnBuscarHabitacion = new JButton("...");
        btnBuscarCliente = new JButton("...");
        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        cbTipoReserva = new JComboBox(new String[]{"Reserva", "Alquiler"});
        cbEstadoReserva = new JComboBox(new String[]{"Alquiler", "Pagada", "Anulada"});
        fontDc = new Font("Arial",Font.PLAIN,11);
        dcReserva = new JDateChooser();
        dcReserva.setFont(fontDc);
        dcIngreso = new JDateChooser();
        dcSalida = new JDateChooser();
        pBotones = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        pCampos = new JPanel(new GridBagLayout());
        inset = new Insets(10, 10, 0, 0);
    }

    private void initMainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Registro de Reservas"));
    }

    private void initSubPanels() {
        addFields();
        addButtons();
    }

    private void addFields() {
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("ID:"), 0, 0, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtId, 2, 0, GridBagConstraints.EAST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Habitacion:"), 0, 2, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtIdHabitacion, 2, 2, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtNumero, 4, 2, GridBagConstraints.WEST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, btnBuscarHabitacion, 6, 2, GridBagConstraints.EAST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Cliente:"), 0, 4, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtIdCliente, 2, 4, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtCliente, 4, 4, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, btnBuscarCliente, 6, 4, GridBagConstraints.EAST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Trabajador:"), 0, 6, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtIdTrabajador, 2, 6, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtTrabajador, 4, 6, GridBagConstraints.EAST, inset);
        
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Tipo Reserva:"), 0, 8, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, cbTipoReserva, 4, 8, GridBagConstraints.WEST, inset);

        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Fecha Reserva:"), 0, 10, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, dcReserva, 4, 10, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, inset);
        
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Fecha Ingreso:"), 0, 12, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, dcIngreso, 4, 12, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, inset);
        
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Fecha Salida:"), 0, 14, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, dcSalida, 4, 14, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, inset);
        
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Costo:"), 0, 16, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, txtCosto, 4, 16, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, inset);
        
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, new JLabel("Estado Reserva:"), 0, 18, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(pCampos, cbEstadoReserva, 4, 18, GridBagConstraints.WEST, inset);
        
        add(pCampos);
    }

    private void addButtons() {
        pBotones.add(btnNuevo);
        pBotones.add(btnGuardar);
        pBotones.add(btnCancelar);
        add(pBotones);
    }

}
