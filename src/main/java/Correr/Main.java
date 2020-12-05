package Correr;

import Controlador.Controlador;
import Vista.Sistema.VentanaSistema;

/**
 *
 * @author jovan
 */
public class Main {

    public static void main(String[] args) {
        VentanaSistema vista = new VentanaSistema();
        Controlador c = new Controlador(vista);
        c.iniciarVentanaSistema();
    }
}
