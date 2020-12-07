/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Conexiones;

import Modelo.Datos.ClienteDAO;
import Modelo.Datos.ClienteDaoJDBC;
import Modelo.Datos.Conexion;
import Modelo.domain.ClienteDTO;
import java.sql.Connection;
import java.sql.SQLException;


/**
 *
 * @author jovan
 */
public class TestCliente {

    public static void main(String[] args) {
        Connection conexion = null;
        ClienteDAO cliente = null;
        ClienteDTO cliente1 = new ClienteDTO("Jobanni", "Sanchez", "Hernandez", "INE", "3345", "CJON. QUIJOTE DE LA MANCHA", "73812349", "jovnni.narciso@gmail.com", 2);
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            cliente = new ClienteDaoJDBC(conexion);
            cliente.insert(cliente1);
            System.out.println("Haciendo commit");
            conexion.commit();
            cliente.select().forEach(System.out::println);

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
