package Correr;

import Controlador.Controlador;
import Metodos.Ayuda;
import Vista.Sistema.VentanaSistema;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jovan
 */
public class Main {

    public static void main(String[] args) {
        VentanaSistema vista = new VentanaSistema();
        Controlador c;
        try {
            c = new Controlador(vista);
            c.iniciarVentanaSistema();
        } catch (SQLException ex) {
            Ayuda.ventanaMensaje(vista.getVentana(), ex.getMessage(), "No se pudo conectar a la base de datos...", JOptionPane.ERROR_MESSAGE);
        }
    }
}
