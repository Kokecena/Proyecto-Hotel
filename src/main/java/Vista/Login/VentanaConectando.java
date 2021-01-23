/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Login;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author jovan
 */
public class VentanaConectando extends JFrame {

    private JPanel panel;
    private JProgressBar barra;

    public VentanaConectando() {
        initFrame();
        System.out.println(this.getWidth() + " " + this.getHeight());
    }

    private void initFrame() {
        setTitle("Conectando...");
        setLayout(new BorderLayout());
        initComponents();
        addComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(272, 112);
        setLocationRelativeTo(null);
    }

    public void initComponents() {
        panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Iniciando sesión"));
        barra = new JProgressBar();
        barra.setIndeterminate(true);
    }

    public void addComponents() {
        Insets inset = new Insets(0, 0, 10, 10);
        Metodos.GBCMetodos.addComponentGBLayout(panel, new JLabel("Conectando a base de datos..."), 0, 0, GridBagConstraints.EAST, inset);
        Metodos.GBCMetodos.addComponentGBLayout(panel, barra, 0, 2, GridBagConstraints.CENTER, inset);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new VentanaConectando().setVisible(true);
    }
}
