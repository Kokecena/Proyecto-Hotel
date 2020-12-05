package Vista.Clientes;

import java.beans.PropertyVetoException;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author jovan
 */
public class PanelClientes extends JInternalFrame {
    
    private PanelRegistroCliente pRegistroCliente;
    
    public PanelClientes() {
        initComponents();
        setTitle("Cliente");
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        setVisible(true);
        this.setClosable(true);
        this.setIconifiable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
    
    private void initComponents(){
        pRegistroCliente = new PanelRegistroCliente();
        add(pRegistroCliente);
    }

    public PanelRegistroCliente getpRegistroCliente() {
        return pRegistroCliente;
    }
}
