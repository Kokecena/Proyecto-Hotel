/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Producto;

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
public class PanelProducto extends JInternalFrame {

    private PanelRegistroProducto pRegistroProducto;
    private PanelTabla pListadoProducto;
    private JPanel pRegistroListado;
    
    public PanelProducto() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Producto");
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
        pListadoProducto = new PanelTabla("Listado de productos",new String[]{"Test 1","Test 2","Test 3","Test 4"});
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

}
