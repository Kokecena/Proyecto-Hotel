/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.PersonaDTO;
import java.sql.SQLException;

/**
 *
 * @author jovan
 */
public interface PersonaDAO {
    PersonaDTO search(PersonaDTO persona) throws SQLException;
}
