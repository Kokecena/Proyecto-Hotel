package Vista.ClientesTrabajadores;

import Metodos.Iconos;
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
public class PanelClientes extends JInternalFrame {

    private PanelRegistroCliente pRegistroCliente;
    private PanelTabla pListadoClientes;
    private JPanel pRegistroListado;

    public PanelClientes() {
        setLayout(new BorderLayout());
        initComponents();
        initFrame();
    }

    private void initFrame() {
        setTitle("Clientes");
        setFrameIcon(Iconos.ICONO_PESTANA_CLIENTES);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistroCliente = new PanelRegistroCliente();
        pListadoClientes = new PanelTabla("Listado clientes", new String[]{"ID", "Nombre", "A. Paterno", "A. Materno", "Tipo documento", "Numero documento", "Direccion", "Telefono", "Email", "Codigo cliente"}, PanelTabla.CON_BOTONES_BUSQUEDA_ELIMINAR);
        pRegistroListado = new JPanel();
        pRegistroListado.setLayout(new BoxLayout(pRegistroListado, BoxLayout.X_AXIS));
        pRegistroListado.add(pRegistroCliente);
        pRegistroListado.add(Box.createHorizontalStrut(10));
        pRegistroListado.add(pListadoClientes);
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroListado, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    public PanelRegistroCliente getpRegistroCliente() {
        return pRegistroCliente;
    }

    public PanelTabla getpListadoClientes() {
        return pListadoClientes;
    }

}
