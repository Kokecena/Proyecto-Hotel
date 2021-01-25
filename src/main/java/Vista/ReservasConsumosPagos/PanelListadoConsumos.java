/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.ReservasConsumosPagos;

import Modelo.Datos.ConsumoDAO;
import Modelo.Datos.ConsumoDaoJDBC;
import Modelo.Datos.ProductoDaoJDBC;
import Modelo.domain.ConsumoDTO;
import Modelo.domain.ProductoDTO;
import Vista.Formularios.PanelTabla;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jovan
 */
public class PanelListadoConsumos extends PanelTabla {

    private int idReserva;

    
    public PanelListadoConsumos(int idReserva, String nombreTabla, String[] nombresColumnas, int disposicionBotones) {
        super(nombreTabla, nombresColumnas, disposicionBotones);
        this.idReserva = idReserva;
        actualizarTabla();    
    }

    private void actualizarTabla() {
        ConsumoDAO consumoDao = new ConsumoDaoJDBC();
        this.getModelo().setRowCount(0);
        List<ConsumoDTO> consumos;
        try {
            consumos = consumoDao.select(idReserva);
            consumos.forEach(c -> {
                this.getModelo().addRow(
                        new Object[]{
                            c.getIdConsumo(),
                            buscarProducto(c.getIdProducto()).getNombre(),
                            c.getCantidad(),
                            c.getPrecioVenta()
                        });
            });
            this.getEtiRegistros().setText("Registros: " + consumos.size());
            this.getEtiDato().setText("Consumo total: $" + calcularImporte(consumos));
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public double calcularImporte(List<ConsumoDTO> consumos){
        double total = 0d;
        for(ConsumoDTO c:consumos){
            total+=c.getCantidad()*c.getPrecioVenta();
        }
        return total;
    }
    
    private ProductoDTO buscarProducto(int id){
        try {
            return new ProductoDaoJDBC().search(id);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }

}
