/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Conexiones;

import Modelo.Datos.Conexion;
import Modelo.Datos.TrabajadorDAO;
import Modelo.Datos.TrabajadorDaoJDBC;
import Modelo.domain.TrabajadorDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jovan
 */
public class TestTrabajador {

public static void main(String[] args) {
        Connection conexion = null;
        TrabajadorDAO trabajadores = null;
        TrabajadorDTO trabajador = new TrabajadorDTO("Jovanni", "Sanchez", "Hernandez", "INE", "3345", "CJON. QUIJOTE DE LA MANCHA", "73812349", "jovnni.narciso@gmail.com", 3999.9, "A", "A", "A", "A");
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            trabajadores = new TrabajadorDaoJDBC(conexion);
            trabajadores.insert(trabajador);
            System.out.println("Haciendo commit");
            conexion.commit();
            trabajadores.select().forEach(System.out::println);
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
