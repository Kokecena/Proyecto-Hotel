/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumosPagos;

import static Metodos.Ayuda.tableCellRendererFormatDate;
import Metodos.Iconos;
import Vista.Formularios.PanelTabla;
import java.awt.BorderLayout;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jovan
 */
public class VentanaReserva extends JInternalFrame {

    private VentanaListadoHabitaciones listadoHabitaciones;
    private VentanaListadoClientes listadoClientes;
    private PanelRegistroReserva pRegistroReserva;
    private PanelTabla pListadoReserva;
    private JPanel pRegistroListado;

    public VentanaReserva() {
        setLayout(new BorderLayout());
        initComponents();
        initFrame();
    }

    

    private void initFrame() {
        setTitle("Reservas");
        setFrameIcon(Iconos.ICONO_SUBMENU_RESERVAS_Y_CONSUMOS);
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }

    private void initComponents() {
        pRegistroReserva = new PanelRegistroReserva();
        pListadoReserva = new PanelTabla("Listado de reservas",
                new String[]{
                    "ID", "ID Habitacion", "ID Cliente", "ID Trabajador",
                    "Tipo de reserva", "Fecha de reserva", "Fecha de Ingreso",
                    "Fecha de Salida", "Costo",
                    "Observacion", "Estado"
                },
                PanelTabla.CON_BOTONES_BUSQUEDA_ELIMINAR,
                "Consumos", "Realizar Pagos", 
                Iconos.ICONO_PESTANA_CONSUMOS, Iconos.ICONO_SUBMENU_PAGOS);
        pListadoReserva.getTbl().getColumnModel().getColumn(5).setCellRenderer(tableCellRendererFormatDate);
        pListadoReserva.getTbl().getColumnModel().getColumn(6).setCellRenderer(tableCellRendererFormatDate);
        pListadoReserva.getTbl().getColumnModel().getColumn(7).setCellRenderer(tableCellRendererFormatDate);
        pRegistroListado = new JPanel();
        pRegistroListado.setLayout(new BoxLayout(pRegistroListado, BoxLayout.X_AXIS));
        pRegistroListado.add(pRegistroReserva);
        pRegistroListado.add(Box.createHorizontalStrut(10));
        pRegistroListado.add(pListadoReserva);
        add(Box.createVerticalStrut(10), BorderLayout.NORTH);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
        add(pRegistroListado, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
    }

    public PanelRegistroReserva getpRegistroReserva() {
        return pRegistroReserva;
    }

    public PanelTabla getpListadoReserva() {
        return pListadoReserva;
    }

    public VentanaListadoHabitaciones getListadoHabitaciones() {
        return listadoHabitaciones;
    }

    public VentanaListadoClientes getListadoClientes() {
        return listadoClientes;
    }

}
