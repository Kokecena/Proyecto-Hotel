/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.ProductoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jovan
 */
public class ProductoDaoJDBC implements ProductoDAO {

    private Connection conexionTransaccional;

    private static final String SQL_INSERT = "INSERT INTO producto (nombre, descripcion, unidad_medida, precio_venta) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombre = ?, descripcion = ?, unidad_medida = ?, precio_venta = ? WHERE idproducto = ?";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE idproducto = ?";
    private static final String SQL_SELECT = "SELECT idproducto, nombre, descripcion, unidad_medida, precio_venta FROM producto";
    private static final String SQL_SEARCH = "SELECT idproducto, nombre FROM producto WHERE idproducto = ?";

    public ProductoDaoJDBC() {
    }

    public ProductoDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(ProductoDTO producto) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_INSERT);
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setString(3, producto.getUnidadMedida());
            pst.setDouble(4, producto.getPrecioVenta());
            registros = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registros;
    }

    @Override
    public int update(ProductoDTO producto) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_UPDATE);
            pst.setString(1, producto.getNombre());
            pst.setString(2, producto.getDescripcion());
            pst.setString(3, producto.getUnidadMedida());
            pst.setDouble(4, producto.getPrecioVenta());
            pst.setInt(5, producto.getIdProducto());
            registros = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registros;
    }

    @Override
    public int delete(ProductoDTO producto) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, producto.getIdProducto());
            registros = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registros;
    }

    @Override
    public List<ProductoDTO> select() throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductoDTO producto = null;
        List<ProductoDTO> productos = new ArrayList();
        try {
            pst = cn.prepareStatement(SQL_SELECT);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idproducto");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String unidadMedida = rs.getString("unidad_medida");
                double precioVenta = rs.getDouble("precio_venta");
                producto = new ProductoDTO(id, nombre, descripcion, unidadMedida, precioVenta);
                productos.add(producto);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return productos;
    }

    @Override
    public ProductoDTO search(int idProducto) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductoDTO producto = null;
        try {
            pst = cn.prepareStatement(SQL_SEARCH);
            pst.setInt(1, idProducto);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idproducto");
                String nombre = rs.getString("nombre");
                producto = new ProductoDTO(id, nombre);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return producto;
    }

}
