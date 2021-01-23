/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.ReservaDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jovan
 */
public interface ReservaDAO {

    int insert(ReservaDTO reserva) throws SQLException;

    int update(ReservaDTO reserva) throws SQLException;

    int delete(ReservaDTO reserva) throws SQLException;

    List<ReservaDTO> select() throws SQLException;
}
