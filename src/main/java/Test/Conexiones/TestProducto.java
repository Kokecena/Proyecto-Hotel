/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test.Conexiones;

import Modelo.Datos.Conexion;
import Modelo.Datos.ProductoDaoJDBC;
import Modelo.domain.ProductoDTO;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author jovan
 */
public class TestProducto {
    public static void main(String[] args) {
        Connection conexion = null;
        ProductoDaoJDBC productos = null;
        ProductoDTO producto = new ProductoDTO(9,"Pixel 3a","Google","Dolares",333.99);
        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            productos = new ProductoDaoJDBC(conexion);
            productos.update(producto);
            System.out.println("Haciendo commit");
            conexion.commit();
            productos.select().forEach(System.out::println);

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
