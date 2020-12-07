/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.HabitacionDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jovan
 */
public interface HabitacionDAO {

    int insert(HabitacionDTO habitacion) throws SQLException;

    int update(HabitacionDTO habitacion) throws SQLException;

    int delete(HabitacionDTO habitacion) throws SQLException;

    List<HabitacionDTO> select() throws SQLException;
}
