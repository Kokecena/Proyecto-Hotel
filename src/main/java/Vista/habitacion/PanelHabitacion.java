package Vista.habitacion;

import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class PanelHabitacion extends JInternalFrame {

    private PanelRegistroHabitacion pRegistro;
    private PanelTabla pListado;
    private JPanel pRegistroListado;

    public PanelHabitacion() {
        initFrame();
        initComponents();
    }

    private void initFrame() {
        setTitle("Habitacion");
        setLayout(new BorderLayout());
        //setSize(850,550);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setMaximizable(true);
        this.setResizable(true);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistro = new PanelRegistroHabitacion("Registro de Habitaciones",new Dimension(300,800));
        pListado = new PanelTabla("Listado de habitaciones",new String[]{"Test 1", "Test 2", "Test 3", "Test 4"});
        pRegistroListado = new JPanel();
        pRegistroListado.setLayout(new BoxLayout(pRegistroListado, BoxLayout.X_AXIS));
        pRegistroListado.add(pRegistro);
        pRegistroListado.add(Box.createHorizontalStrut(10));
        pRegistroListado.add(pListado);
        add(Box.createVerticalStrut(10),BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10),BorderLayout.EAST);
        add(pRegistroListado,BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10),BorderLayout.WEST);
        add(Box.createVerticalStrut(10),BorderLayout.SOUTH);
    }

    public PanelRegistroHabitacion getpRegistro() {
        return pRegistro;
    }

    public PanelTabla getpListado() {
        return pListado;
    }

}
