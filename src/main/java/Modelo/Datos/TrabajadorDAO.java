/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.TrabajadorDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jovan
 */
public interface TrabajadorDAO {

    int insert(TrabajadorDTO trabajador) throws SQLException;

    int update(TrabajadorDTO trabajador) throws SQLException;

    int delete(TrabajadorDTO trabajador) throws SQLException;

    List<TrabajadorDTO> select() throws SQLException;
}
