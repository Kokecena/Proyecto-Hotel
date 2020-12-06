package Vista.ClientesTrabajadores;

import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class PanelTrabajador extends JPanel {

    private PanelRegistroTrabajador pRegistroTrabajador;
    private PanelTabla pListadoTrabajador;
    private JPanel pRegistroListado;

    public PanelTrabajador() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        pRegistroTrabajador = new PanelRegistroTrabajador();
        pListadoTrabajador = new PanelTabla("Listado trabajadores", new String[]{"Test 1", "Test 2", "Test 3", "Test 4"});
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
