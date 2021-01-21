/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Login;

import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static Metodos.GBCMetodos.*;
import Metodos.Iconos;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jovan
 */
public class VentanaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIniciar;
    private JButton btnCancelar;
    private JPanel panelCampos;
    private JPanel panelBotones;
    private JLabel imagen;

    public VentanaLogin() {
        initFrame();
    }
    
    private void initFrame() {
        setTitle("Iniciar Sesion");
        setLayout(new BorderLayout());
        initComponents();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addFields();
        addButtons();
        add(imagen, BorderLayout.WEST);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        txtUsuario = new JTextField(14);
        txtPassword = new JPasswordField(14);
        btnIniciar = new JButton("Iniciar Sesion", Iconos.ICONO_MENU_SISTEMA);
        btnCancelar = new JButton("Cancelar", Iconos.ICONO_BOTON_CANCELAR);
        panelCampos = new JPanel(new GridBagLayout());
        panelBotones = new JPanel(new FlowLayout());
        imagen = new JLabel(Iconos.ICONO_USUARIO);
    }

    private void addFields() {
        Insets inset = new Insets(0, 0, 10, 10);
        addComponentGBLayout(panelCampos, new JLabel("Usuario: "), 0, 0, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelCampos, txtUsuario, 2, 0, GridBagConstraints.WEST, inset);
        addComponentGBLayout(panelCampos, new JLabel("Contraseña: "), 0, 2, GridBagConstraints.EAST, inset);
        addComponentGBLayout(panelCampos, txtPassword, 2, 2, GridBagConstraints.WEST, inset);
        add(panelCampos, BorderLayout.CENTER);
    }

    private void addButtons() {
        panelBotones.add(btnIniciar);
        panelBotones.add(btnCancelar);
        addActionCommands();
        add(panelBotones, BorderLayout.SOUTH);
    }

    private void addActionCommands(){
        btnIniciar.setActionCommand("login");
        btnCancelar.setActionCommand("cancel");
    }

    public JTextField getTxtUsuario() {
        return txtUsuario;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    
}
