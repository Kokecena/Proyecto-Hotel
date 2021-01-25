/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumosPagos;

import Metodos.Iconos;
import Modelo.Datos.HabitacionDaoJDBC;
import Modelo.domain.HabitacionDTO;
import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author jovan
 */
public class VentanaListadoHabitaciones extends JInternalFrame {

    private PanelTabla pHabitaciones;

    public VentanaListadoHabitaciones() {
        
        initFrame();
    }

    private void initFrame() {
        setLayout(new BorderLayout());
        setTitle("Habitaciones");
        setClosable(true);
        setResizable(true);
        setFrameIcon(Iconos.ICONO_SUBMENU_HABITACIONES);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        actualizarTabla();
        pack();
    }

    public void actualizarTabla() {
        HabitacionDaoJDBC habitacionDao = new HabitacionDaoJDBC();
        pHabitaciones.getModelo().setRowCount(0);
        List<HabitacionDTO> habitaciones;
        try {
            habitaciones = habitacionDao.select();
            habitaciones.forEach(h -> {
                pHabitaciones.getModelo().addRow(
                        new Object[]{
                            h.getIdHabitacion(),
                            h.getNumero(),
                            h.getPiso(),
                            h.getDescripcion(),
                            h.getCaracteristicas(),
                            h.getPrecioDiario(),
                            h.getEstado(),
                            h.getTipoHabitacion()});
            });
            pHabitaciones.getEtiRegistros().setText("Registros: " + habitaciones.size());
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void initComponents() {
        pHabitaciones = new PanelTabla("Listado de habitaciones", new String[]{"ID", "Numero", "Piso", "Descripción", "Caracteristicas", "Precio diario", "Estado", "Tipo de habitación"}, PanelTabla.CON_BOTON_BUSQUEDA);
        add(pHabitaciones, BorderLayout.CENTER);
    }

    public PanelTabla getpHabitaciones() {
        return pHabitaciones;
    }

    
}
