/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumosPagos;

import Metodos.Iconos;
import Modelo.Datos.HabitacionDaoJDBC;
import Modelo.Datos.ProductoDaoJDBC;
import Modelo.domain.HabitacionDTO;
import Modelo.domain.ProductoDTO;
import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JInternalFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author jovan
 */
public class VentanaListadoProductos extends JInternalFrame {

    private PanelTabla pProductos;

    public VentanaListadoProductos() {
        initFrame();
    }

    private void initFrame() {
        setLayout(new BorderLayout());
        setTitle("Productos");
        setClosable(true);
        setResizable(true);
        setFrameIcon(Iconos.ICONO_SUBMENU_PRODUCTOS);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        actualizarTabla();
        pack();
    }

    public void actualizarTabla() {
        ProductoDaoJDBC productoDao = new ProductoDaoJDBC();
        pProductos.getModelo().setRowCount(0);
        List<ProductoDTO> productos;
        try {
            productos = productoDao.select();
            productos.forEach(p -> {
                pProductos.getModelo().addRow(
                        new Object[]{
                            p.getIdProducto(),
                            p.getNombre(),
                            p.getDescripcion(),
                            p.getUnidadMedida(),
                            p.getPrecioVenta()
                        });
            });
            pProductos.getEtiRegistros().setText("Registros: " + productos.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void initComponents() {
        pProductos = new PanelTabla("Listado de productos", new String[]{"ID", "Nombre", "Descripcion", "Unidad de medida", "Precio de venta"}, PanelTabla.CON_BOTON_BUSQUEDA);
        add(pProductos, BorderLayout.CENTER);
    }

    public PanelTabla getpProductos() {
        return pProductos;
    }

}
