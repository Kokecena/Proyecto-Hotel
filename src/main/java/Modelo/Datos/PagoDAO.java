/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.PagoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jovan
 */
public interface PagoDAO {
    List<PagoDTO> select(int idReserva) throws SQLException;
    int insert(PagoDTO pago) throws SQLException;
    int update(PagoDTO pago) throws SQLException;
    int delete(PagoDTO pago) throws SQLException;
}
