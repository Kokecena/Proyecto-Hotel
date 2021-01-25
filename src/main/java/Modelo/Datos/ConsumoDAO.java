/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.ConsumoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jovan
 */
public interface ConsumoDAO {
    int insert(ConsumoDTO consumo) throws SQLException;

    int update(ConsumoDTO consumo) throws SQLException;

    int delete(ConsumoDTO consumo) throws SQLException;

    List<ConsumoDTO> select(int idReserva) throws SQLException;
}
