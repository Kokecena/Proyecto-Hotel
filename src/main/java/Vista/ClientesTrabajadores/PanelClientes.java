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
public class PanelClientes extends JPanel {

    private PanelRegistroCliente pRegistroCliente;
    private PanelTabla pListadoClientes;
    private JPanel pRegistroListado;

    public PanelClientes() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        pRegistroCliente = new PanelRegistroCliente();
        pListadoClientes = new PanelTabla("Listado clientes", new String[]{"Test 1", "Test 2", "Test 3", "Test 4"});
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
