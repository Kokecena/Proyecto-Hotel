/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.UsuarioActivo;

import Metodos.Iconos;
import Modelo.domain.TrabajadorDTO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class UsuarioActivo extends JInternalFrame {

    private TrabajadorDTO usuario;
    private JLabel datos;
    private JPanel datosUsuario;
    private int id;
    private String nombre;
    private String login;
    private String apellidos;
    private String permisos;

    public UsuarioActivo(TrabajadorDTO usuario) {
        this.usuario = usuario;
        initFrame();
    }

    private void initFrame() {
        rellenarDatosUsuario();
        setTitle("Usuario: " + login);
        this.setFrameIcon(Iconos.ICONO_PESTANA_TRABAJADORES);
        setLayout(new BorderLayout());
        setIconifiable(true);
        initComponents();
        setVisible(true);
        pack();
    }

    private void rellenarDatosUsuario() {
        id = usuario.getIdPersona();
        nombre = usuario.getNombre();
        login = usuario.getLogin();
        apellidos = usuario.getaPaterno() + " " + usuario.getaMaterno();
        permisos = usuario.getAcceso();
    }

    private void initComponents() {
        datos = new JLabel("<html><table style=\"border-collapse: collapse; width: 80.6061%; height: 73px;\" border=\"1\">\n"
                + "<tbody>\n"
                + "<tr style=\"height: 35px;\">\n"
                + "<td style=\"width: 11.9192%; text-align: center; height: 35px;\">\n"
                + "<h4><strong>No. Id</strong></h4>\n"
                + "</td>\n"
                + "<td style=\"width: 34.2303%; height: 35px; text-align: center;\">" + id + "</td>\n"
                + "</tr>\n"
                + "<tr style=\"height: 10px;\">\n"
                + "<td style=\"width: 11.9192%; height: 10px; text-align: center;\">\n"
                + "<h4><strong>Nombre</strong></h4>\n"
                + "</td>\n"
                + "<td style=\"width: 34.2303%; height: 10px; text-align: center;\">" + nombre + "</td>\n"
                + "</tr>\n"
                + "<tr style=\"height: 10px;\">\n"
                + "<td style=\"width: 11.9192%; height: 10px; text-align: center;\">\n"
                + "<h4><strong>Apellidos</strong></h4>\n"
                + "</td>\n"
                + "<td style=\"width: 34.2303%; height: 10px; text-align: center;\">" + apellidos + "</td>\n"
                + "</tr>\n"
                + "<tr style=\"height: 18px;\">\n"
                + "<td style=\"width: 11.9192%; height: 18px; text-align: center;\">\n"
                + "<h4><strong>Permisos</strong></h4>\n"
                + "</td>\n"
                + "<td style=\"width: 34.2303%; height: 18px; text-align: center;\">" + permisos + "</td>\n"
                + "</tr>\n"
                + "</tbody>\n"
                + "</table></html>");
        datosUsuario = new JPanel(new FlowLayout());
        datosUsuario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del trabajador"));
        datosUsuario.add(datos);
        add(datosUsuario, BorderLayout.CENTER);
    }
}
