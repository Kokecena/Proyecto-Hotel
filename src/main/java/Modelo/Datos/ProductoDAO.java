/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Modelo.domain.ProductoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jovan
 */
public interface ProductoDAO {
    int insert(ProductoDTO producto) throws SQLException;

    int update(ProductoDTO producto) throws SQLException;

    int delete(ProductoDTO producto) throws SQLException;

    List<ProductoDTO> select() throws SQLException;
    
    ProductoDTO search(int idProducto) throws SQLException;
}
