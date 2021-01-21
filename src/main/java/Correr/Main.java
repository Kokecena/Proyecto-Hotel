package Correr;

import Controlador.ControladorLogin;
import Modelo.Logica.LogicaLogin;
import Vista.Login.VentanaLogin;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jovan
 */
public class Main {
    public static void main(String[] args) {
       
        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(System.out);
        }
    
        VentanaLogin vl = new VentanaLogin();
        LogicaLogin ll = new LogicaLogin(vl);
        ControladorLogin cl = new ControladorLogin(vl,ll);
    }
}