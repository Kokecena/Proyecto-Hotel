/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumosPagos;

import Metodos.Iconos;
import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class VentanaConsumo extends JInternalFrame {

    private PanelRegistroConsumo pRegistroConsumo;
    private PanelTabla pListadoConsumo;
    private JPanel pRegistroListado;

    public VentanaConsumo(int disposicionBoton) {
        setLayout(new BorderLayout());
        initComponents();
        initFrame();
    }

    private void initFrame() {
        setTitle("Consumos");
        setFrameIcon(Iconos.ICONO_PESTANA_CONSUMOS);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistroConsumo = new PanelRegistroConsumo();
        pListadoConsumo = new PanelTabla("Listado de consumos",
                new String[]{"ID", "ID Producto", "Producto", "Cantidad", "Precio de venta", "Estado"});

        pRegistroListado = new JPanel();
        pRegistroListado.setLayout(new BoxLayout(pRegistroListado, BoxLayout.X_AXIS));
        pRegistroListado.add(pRegistroConsumo);
        pRegistroListado.add(Box.createHorizontalStrut(10));
        pRegistroListado.add(pListadoConsumo);
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroListado, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    public PanelRegistroConsumo getpRegistroConsumo() {
        return pRegistroConsumo;
    }

    public PanelTabla getpListadoConsumo() {
        return pListadoConsumo;
    }

}
