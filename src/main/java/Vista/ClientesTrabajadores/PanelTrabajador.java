package Vista.ClientesTrabajadores;

import Metodos.Iconos;
import Vista.ClientesTrabajadores.PanelRegistroTrabajador;
import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author jovan
 */
public class PanelTrabajador extends JInternalFrame {

    private PanelRegistroTrabajador pRegistroTrabajador;
    private PanelTabla pListadoTrabajador;
    private JPanel pRegistroListado;

    public PanelTrabajador() {
        setLayout(new BorderLayout());
        initComponents();
        initFrame();
    }
    
    private void initFrame() {
        setTitle("Trabajadores");
        setFrameIcon(Iconos.ICONO_PESTANA_TRABAJADORES);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistroTrabajador = new PanelRegistroTrabajador();
        pListadoTrabajador = new PanelTabla("Listado trabajadores", new String[]{"ID", "Nombre", "A. Paterno", "A. Materno", "Tipo documento", "Numero documento", "Direccion", "Telefono", "Email", "Sueldo", "Acceso", "Login", "Password", "Estado"}, PanelTabla.CON_BOTONES_BUSQUEDA_ELIMINAR);
        pRegistroListado = new JPanel();
        pRegistroListado.setLayout(new BoxLayout(pRegistroListado, BoxLayout.X_AXIS));
        pRegistroListado.add(pRegistroTrabajador);
        pRegistroListado.add(Box.createHorizontalStrut(10));
        pRegistroListado.add(pListadoTrabajador);
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroListado, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    public PanelRegistroTrabajador getpRegistroTrabajador() {
        return pRegistroTrabajador;
    }

    public PanelTabla getpListadoTrabajador() {
        return pListadoTrabajador;
    }

}
