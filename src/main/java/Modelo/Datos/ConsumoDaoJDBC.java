package Modelo.Datos;

import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.ConsumoDTO;
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
public class ConsumoDaoJDBC implements ConsumoDAO {

    private Connection conexionTransaccional;

    private static final String SQL_INSERT = "INSERT INTO consumo (idreserva, idproducto, cantidad, precio_venta, estado) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE consumo SET idproducto = ?, cantidad = ?, precio_venta = ?, estado = ? WHERE idconsumo = ?";
    private static final String SQL_DELETE = "DELETE FROM consumo WHERE idconsumo = ?";
    private static final String SQL_SELECT = "SELECT * FROM consumo WHERE idReserva = ?";

    public ConsumoDaoJDBC() {
    }

    public ConsumoDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(ConsumoDTO consumo) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registro = 0;
        try {
            pst = cn.prepareStatement(SQL_INSERT);
            pst.setInt(1, consumo.getIdReserva());
            pst.setInt(2, consumo.getIdProducto());
            pst.setInt(3, consumo.getCantidad());
            pst.setDouble(4, consumo.getPrecioVenta());
            pst.setString(5, consumo.getEstado());
            registro = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registro;
    }

    @Override
    public int update(ConsumoDTO consumo) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registro = 0;
        try {
            pst = cn.prepareStatement(SQL_UPDATE);
            pst.setInt(1, consumo.getIdProducto());
            pst.setInt(2, consumo.getCantidad());
            pst.setDouble(3, consumo.getPrecioVenta());
            pst.setString(4, consumo.getEstado());
            pst.setInt(5, consumo.getIdConsumo());
            registro = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registro;
    }

    @Override
    public int delete(ConsumoDTO consumo) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registro = 0;
        try {
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, consumo.getIdConsumo());
            registro = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registro;
    }

    @Override
    public List<ConsumoDTO> select(int idReserva) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ConsumoDTO consumo = null;
        List<ConsumoDTO> consumos = new ArrayList();
        try {
            pst = cn.prepareStatement(SQL_SELECT);
            pst.setInt(1, idReserva);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idconsumo");
                int idRe = rs.getInt("idreserva");
                int idProducto = rs.getInt("idproducto");
                int cantidad = rs.getInt("cantidad");
                double precioVenta = rs.getDouble("precio_venta");
                String estado = rs.getString("estado");
                consumo = new ConsumoDTO(id, idRe, idProducto, cantidad, precioVenta, estado);
                consumos.add(consumo);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return consumos;
    }

}
