/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import Metodos.Ayuda;
import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.PagoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jovan
 */
public class PagoDaoJDBC implements PagoDAO {

    private Connection conexionTransaccional;

    private final static String SQL_SELECT = "SELECT idpago, idreserva, tipo_comprobante, num_comprobante, igv, total_pago, fecha_emision, fecha_pago FROM pago WHERE idreserva = ?";
    private final static String SQL_INSERT = "INSERT INTO pago (idreserva, tipo_comprobante, num_comprobante, igv, total_pago, fecha_emision, fecha_pago) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE pago SET idreserva = ?, tipo_comprobante = ?, igv = ?, total_pago = ?, fecha_emision = ?, fecha_pago = ? WHERE idpago = ?";
    private final static String SQL_DELETE = "DELETE FROM pago WHERE idpago = ?";

    public PagoDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<PagoDTO> select(int idReserva) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        PagoDTO pago = null;
        List<PagoDTO> pagos = new ArrayList();
        try {
            pst = cn.prepareStatement(SQL_SELECT);
            pst.setInt(1, idReserva);
            rs = pst.executeQuery();
            while (rs.next()) {
                int idpago = rs.getInt("idpago");
                int idreserva = rs.getInt("idreserva");
                String tipoComprobante = rs.getString("tipo_comprobante");
                String numComprobante = rs.getString("num_comprobante");
                double igv = rs.getDouble("igv");
                double totalPago = rs.getDouble("total_pago");
                Date fechaEmision = Ayuda.convertirDeSQLDateAJavaDate(rs.getDate("fecha_emision"));
                Date fechaPago = Ayuda.convertirDeSQLDateAJavaDate(rs.getDate("fecha_pago"));
                pago = new PagoDTO(idpago, idreserva, tipoComprobante, numComprobante, igv, totalPago, fechaEmision, fechaPago);
                pagos.add(pago);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return pagos;
    }

    @Override
    public int insert(PagoDTO pago) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_INSERT);
            pst.setInt(1, pago.getIdReserva());
            pst.setString(2, pago.getTipoComprobante());
            pst.setString(3, pago.getNumComprobante());
            pst.setDouble(4, pago.getIgv());
            pst.setDouble(5, pago.getTotalPago());
            pst.setDate(6, Ayuda.convertirDeJavaDateASQLDate(pago.getFechaEmision()));
            pst.setDate(7, Ayuda.convertirDeJavaDateASQLDate(pago.getFechaPago()));
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
    public int update(PagoDTO pago) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_UPDATE);
            pst.setInt(1, pago.getIdReserva());
            pst.setString(2, pago.getTipoComprobante());
            pst.setString(3, pago.getNumComprobante());
            pst.setDouble(4, pago.getTotalPago());
            pst.setDate(5, Ayuda.convertirDeJavaDateASQLDate(pago.getFechaEmision()));
            pst.setDate(6, Ayuda.convertirDeJavaDateASQLDate(pago.getFechaPago()));
            pst.setInt(7, pago.getIdPago());
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
    public int delete(PagoDTO pago) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, pago.getIdPago());
            registros = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registros;
    }

}
