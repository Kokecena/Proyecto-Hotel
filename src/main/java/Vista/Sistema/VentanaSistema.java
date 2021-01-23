package Vista.Sistema;

import Metodos.Iconos;
import Vista.ClientesTrabajadores.VentanaClientes;
import Vista.Producto.VentanaProducto;
import Vista.ClientesTrabajadores.VentanaTrabajador;
import Vista.habitacion.VentanaHabitacion;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author jovan
 */
public class VentanaSistema {

    private VentanaHabitacion vHabitacion;
    private VentanaProducto vProducto;
    private VentanaClientes vClientes;
    private VentanaTrabajador vTrabajador;
    private JFrame ventana;
    private MenuOpciones menu;
    public static JDesktopPane escritorio;

    public VentanaSistema() {
        initComponents();
        initFrame();
        addMenu();
        addDesktop();
    }

    private void initFrame() {
        ventana.setIconImage(Iconos.ICONO_SISTEMA.getImage());
        ventana.setTitle("Sistema Hotelero");
        ventana.setSize(1000, 700);
        ventana.setVisible(true);
        ventana.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        ventana = new JFrame();
        menu = new MenuOpciones();
        escritorio = new JDesktopPane() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Iconos.FONDO_ESCRITORIO.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
    }

    private void addMenu() {
        ventana.setJMenuBar(menu);
    }

    private void addDesktop() {
        ventana.add(escritorio);
    }

    public VentanaHabitacion getvHabitacion() {
        return vHabitacion;
    }

    public VentanaProducto getvProducto() {
        return vProducto;
    }

    public VentanaClientes getvClientes() {
        return vClientes;
    }

    public VentanaTrabajador getvTrabajador() {
        return vTrabajador;
    }

    public MenuOpciones getMenu() {
        return menu;
    }

    public JDesktopPane getEscritorio() {
        return escritorio;
    }

    public JFrame getVentana() {
        return ventana;
    }

}
