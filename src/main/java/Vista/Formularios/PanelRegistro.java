/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Formularios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class PanelRegistro extends JPanel {

    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JPanel panelBotones;
    private final Dimension dimension;
    private final String nombreRegistro;

    public PanelRegistro(String nombreRegistro, Dimension dimension) {
        this.nombreRegistro = nombreRegistro;
        this.dimension = dimension;
    }

    public void initMainPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setMaximumSize(dimension);
        setBorder(BorderFactory.createTitledBorder(nombreRegistro));
    }

    private void setActionCommands(){
        btnNuevo.setActionCommand("new");
        btnGuardar.setActionCommand("save");
        btnCancelar.setActionCommand("cancel");
    }
    
    public void addButtons() {
        btnNuevo = new JButton("Nuevo");
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        setActionCommands();
        panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnNuevo);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
}
