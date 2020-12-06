package Vista.Sistema;

import Vista.ClientesTrabajadores.PanelClientes;
import Vista.Producto.PanelProducto;
import Vista.ClientesTrabajadores.PanelTrabajador;
import Vista.habitacion.PanelHabitacion;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jovan
 */
public class VentanaSistema {

    private PanelHabitacion vHabitacion;
    private PanelProducto vProducto;
    private PanelClientes vClientes;
    private PanelTrabajador vTrabajador;
    private JFrame ventana;
    private MenuOpciones menu;
    private JDesktopPane escritorio;

    public VentanaSistema() {
        initLookAndFeel();
        initComponents();
        initFrame();
        addMenu();
        addDesktop();

    }

    private void initLookAndFeel() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel(new FlatDarculaLaf());
            //UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void initFrame() {
        //ventana.setIconImage(Iconos.ICONO_SISTEMA);
        ventana.setTitle("Sistema Hotelero");
        ventana.setSize(1000, 700);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initComponents() {
        ventana = new JFrame();
        menu = new MenuOpciones();
        escritorio = new JDesktopPane();
    }

    private void addMenu() {
        ventana.setJMenuBar(menu);
    }

    private void addDesktop() {
        ventana.add(escritorio);
    }

    public PanelHabitacion getvHabitacion() {
        return vHabitacion;
    }

    public PanelProducto getvProducto() {
        return vProducto;
    }

    public PanelClientes getvClientes() {
        return vClientes;
    }

    public PanelTrabajador getvTrabajador() {
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
