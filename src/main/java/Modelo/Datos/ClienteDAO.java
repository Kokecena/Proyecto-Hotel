/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.ClienteDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jovan
 */
public interface ClienteDAO {

    int insert(ClienteDTO cliente) throws SQLException;

    int update(ClienteDTO cliente) throws SQLException;

    int delete(ClienteDTO cliente) throws SQLException;

    List<ClienteDTO> select() throws SQLException;
}
