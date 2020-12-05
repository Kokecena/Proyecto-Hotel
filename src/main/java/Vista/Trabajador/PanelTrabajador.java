package Vista.Trabajador;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author jovan
 */
public class PanelTrabajador extends JInternalFrame {
     private PanelRegistroTrabajador pRegistroTrabajador;
    
    public PanelTrabajador(){
        initComponents();
        setTitle("Trabajador");
        this.setClosable(true);
        this.setIconifiable(true);
        setLayout(new BoxLayout(getContentPane(),BoxLayout.X_AXIS));
        setSize(350,570);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void initComponents(){
        pRegistroTrabajador = new PanelRegistroTrabajador();
        add(Box.createHorizontalStrut(10));
        add(pRegistroTrabajador,BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10));
    }

    public PanelRegistroTrabajador getpRegistroTrabajador() {
        return pRegistroTrabajador;
    }
}
