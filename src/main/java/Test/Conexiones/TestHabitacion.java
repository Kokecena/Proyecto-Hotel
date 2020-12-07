/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Conexiones;

import Modelo.Datos.Conexion;
import Modelo.Datos.HabitacionDaoJDBC;
import Modelo.domain.HabitacionDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jovan
 */
public class TestHabitacion {
    public static void main(String[] args) {
        Connection conexion = null;
        HabitacionDaoJDBC habitaciones = null;
        HabitacionDTO habitacion = new HabitacionDTO(27,"3","3","Cacas","Cacas",3999.99,"Popos","Mierdas");
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            habitaciones = new HabitacionDaoJDBC(conexion);
            habitaciones.insert(habitacion);
            System.out.println("Haciendo commit");
            conexion.commit();
            habitaciones.select().forEach(System.out::println);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                System.out.println("Haciendo Rollback...");
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }
}
