package Vista.habitacion;

import Metodos.Iconos;
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
public class VentanaHabitacion extends JInternalFrame {

    private PanelRegistroHabitacion pRegistro;
    private PanelTabla pListado;
    private JPanel pRegistroListado;

    public VentanaHabitacion() {
        initFrame();
        initComponents();
    }

    private void initFrame() {
        setTitle("Habitaciones");
        setFrameIcon(Iconos.ICONO_SUBMENU_HABITACIONES);
        setLayout(new BorderLayout());
        this.setClosable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistro = new PanelRegistroHabitacion("Registro de Habitaciones",new Dimension(300,800));
        pListado = new PanelTabla("Listado de habitaciones",new String[]{"ID", "Numero", "Piso", "Descripción","Caracteristicas","Precio diario","Estado","Tipo de habitación"},PanelTabla.CON_COMBO_BOTONES_BUSQUEDA_ELIMINAR,new String[]{"ID","Estado","Tipo de habitación"});
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
