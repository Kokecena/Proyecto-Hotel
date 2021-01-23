/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import static Metodos.Ayuda.convertirDeJavaDateASQLDate;
import static Metodos.Ayuda.convertirDeSQLDateAJavaDate;
import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.ReservaDTO;
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
public class ReservaDaoJDBC implements ReservaDAO {

    private Connection conexionTransaccional;

    private static final String SQL_INSERT = "INSERT INTO reserva (idhabitacion, idcliente, idtrabajador, tipo_reserva, fecha_reserva, fecha_ingresa, fecha_salida, costo_alojamiento, observacion, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? , ?)";
    private static final String SQL_UPDATE = "UPDATE reserva SET idhabitacion = ?, idcliente = ?, idtrabajador = ?, tipo_reserva = ?, fecha_reserva = ?, fecha_ingresa = ?, fecha_salida = ?, costo_alojamiento = ?, observacion = ?, estado = ? WHERE idreserva = ?";
    private static final String SQL_DELETE = "DELETE FROM reserva WHERE idreserva = ?";
    private static final String SQL_SELECT = "SELECT * FROM reserva";

    public ReservaDaoJDBC() {
    }

    public ReservaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(ReservaDTO reserva) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_INSERT);
            pst.setInt(1, reserva.getIdHabitacion());
            pst.setInt(2, reserva.getIdCliente());
            pst.setInt(3, reserva.getIdTrabajador());
            pst.setString(4, reserva.getTipoReserva());
            pst.setDate(5, convertirDeJavaDateASQLDate(reserva.getFechaReserva()));
            pst.setDate(6, convertirDeJavaDateASQLDate(reserva.getFechaIngreso()));
            pst.setDate(7, convertirDeJavaDateASQLDate(reserva.getFechaSalida()));
            pst.setDouble(8, reserva.getCostoAlojamiento());
            pst.setString(9, reserva.getObservacion());
            pst.setString(10, reserva.getEstado());
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
    public int update(ReservaDTO reserva) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_UPDATE);
            pst.setInt(1, reserva.getIdHabitacion());
            pst.setInt(2, reserva.getIdCliente());
            pst.setInt(3, reserva.getIdTrabajador());
            pst.setString(4, reserva.getTipoReserva());
            pst.setDate(5, convertirDeJavaDateASQLDate(reserva.getFechaReserva()));
            pst.setDate(6, convertirDeJavaDateASQLDate(reserva.getFechaIngreso()));
            pst.setDate(7, convertirDeJavaDateASQLDate(reserva.getFechaSalida()));
            pst.setDouble(8, reserva.getCostoAlojamiento());
            pst.setString(9, reserva.getObservacion());
            pst.setString(10, reserva.getEstado());
            pst.setInt(11, reserva.getIdReserva());
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

    public int delete(ReservaDTO reserva) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, reserva.getIdReserva());
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
    public List<ReservaDTO> select() throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ReservaDTO reserva = null;
        List<ReservaDTO> reservas = new ArrayList();
        try {
            pst = cn.prepareStatement(SQL_SELECT);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idreserva");
                int idH = rs.getInt("idhabitacion");
                int idC = rs.getInt("idcliente");
                int idT = rs.getInt("idtrabajador");
                String tipoReserva = rs.getString("tipo_reserva");
                Date fechaReserva = convertirDeSQLDateAJavaDate(rs.getDate("fecha_reserva"));
                Date fechaIngresa = convertirDeSQLDateAJavaDate(rs.getDate("fecha_ingresa"));
                Date fechaSalida = convertirDeSQLDateAJavaDate(rs.getDate("fecha_salida"));
                double costoAlojamiento = rs.getDouble("costo_alojamiento");
                String observacion = rs.getString("observacion");
                String estado = rs.getString("estado");
                reserva = new ReservaDTO(id, idH, idC, idT, tipoReserva, fechaReserva, fechaIngresa, fechaSalida, costoAlojamiento, observacion, estado);
                reservas.add(reserva);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return reservas;
    }

}
