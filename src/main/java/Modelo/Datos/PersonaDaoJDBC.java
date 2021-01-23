/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.PersonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jovan
 */
public class PersonaDaoJDBC implements PersonaDAO {

    private static final String SQL_SELECT = "SELECT idpersona, nombre, apaterno, amaterno FROM persona WHERE idpersona = ?";

    @Override
    public PersonaDTO search(PersonaDTO p) throws SQLException {
        Connection cn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        try {
            pst = cn.prepareCall(SQL_SELECT);
            pst.setInt(1, p.getIdPersona());
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String aPaterno = rs.getString("apaterno");
                String aMaterno = rs.getString("amaterno");
                persona = new PersonaDTO(id, nombre, aPaterno, aMaterno);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            Conexion.close(cn);
        }
        return persona;
    }
}
