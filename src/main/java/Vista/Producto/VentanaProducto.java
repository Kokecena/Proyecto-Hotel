/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Producto;

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
public class VentanaProducto extends JInternalFrame {

    private PanelRegistroProducto pRegistroProducto;
    private PanelTabla pListadoProducto;
    private final Dimension TAMANO_TABLA = new Dimension(500,300);
    private JPanel pRegistroListado;
    
    public VentanaProducto() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Producto");
        setFrameIcon(Iconos.ICONO_SUBMENU_PRODUCTOS);
        setLayout(new BorderLayout());
        initComponents();
        setClosable(true);
        setIconifiable(true);
        this.setResizable(true);
        //etSize(340, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistroProducto = new PanelRegistroProducto("Registro de producto",new Dimension(300,800));
        pListadoProducto = new PanelTabla("Listado de productos",new String[]{"ID","Nombre","Descripcion","Unidad de medida","Precio en Venta"},PanelTabla.CON_BOTONES_BUSQUEDA_ELIMINAR);
        pListadoProducto.setPreferredSize(TAMANO_TABLA);
        pRegistroListado = new JPanel();
        pRegistroListado.setLayout(new BoxLayout(pRegistroListado, BoxLayout.X_AXIS));
        pRegistroListado.add(pRegistroProducto);
        pRegistroListado.add(Box.createHorizontalStrut(10));
        pRegistroListado.add(pListadoProducto);
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroListado, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    public PanelRegistroProducto getpRegistroProducto() {
        return pRegistroProducto;
    }

    public PanelTabla getpListadoProducto() {
        return pListadoProducto;
    }

    
}
