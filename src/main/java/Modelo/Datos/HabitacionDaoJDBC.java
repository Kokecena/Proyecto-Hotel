/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.HabitacionDTO;
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
public class HabitacionDaoJDBC implements HabitacionDAO {

    private Connection conexionTransaccional;

    private static final String SQL_INSERT = "INSERT INTO habitacion (numero, piso, descripcion, caracteristicas, precio_diario, estado, tipo_habitacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE habitacion SET numero = ?, piso = ?, descripcion = ?, caracteristicas = ?, precio_diario = ?, estado = ?, tipo_habitacion = ? WHERE idhabitacion = ?";
    private static final String SQL_DELETE = "DELETE FROM habitacion WHERE idhabitacion = ?";
    private static final String SQL_SELECT = "SELECT idhabitacion, numero, piso, descripcion, caracteristicas, precio_diario, estado, tipo_habitacion FROM habitacion";

    public HabitacionDaoJDBC() {
    }

    public HabitacionDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(HabitacionDTO habitacion) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;

        try {
            pst = cn.prepareStatement(SQL_INSERT);
            pst.setString(1, habitacion.getNumero());
            pst.setString(2, habitacion.getPiso());
            pst.setString(3, habitacion.getDescripcion());
            pst.setString(4, habitacion.getCaracteristicas());
            pst.setDouble(5, habitacion.getPrecioDiario());
            pst.setString(6, habitacion.getEstado());
            pst.setString(7, habitacion.getTipoHabitacion());
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
    public int update(HabitacionDTO habitacion) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;

        try {
            pst = cn.prepareStatement(SQL_UPDATE);
            pst.setString(1, habitacion.getNumero());
            pst.setString(2, habitacion.getPiso());
            pst.setString(3, habitacion.getDescripcion());
            pst.setString(4, habitacion.getCaracteristicas());
            pst.setDouble(5, habitacion.getPrecioDiario());
            pst.setString(6, habitacion.getEstado());
            pst.setString(7, habitacion.getTipoHabitacion());
            pst.setInt(8, habitacion.getIdHabitacion());
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
    public int delete(HabitacionDTO habitacion) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;
        try {
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, habitacion.getIdHabitacion());
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
    public List<HabitacionDTO> select() throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        HabitacionDTO habitacion = null;
        List<HabitacionDTO> habitaciones = new ArrayList();
        try {
            pst = cn.prepareStatement(SQL_SELECT);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idhabitacion");
                String numero = rs.getString("numero");
                String piso = rs.getString("piso");
                String descripcion = rs.getString("descripcion");
                String caracteristicas = rs.getString("caracteristicas");
                double precioDiario = rs.getDouble("precio_diario");
                String estado = rs.getString("estado");
                String tipoHabitacion = rs.getString("tipo_habitacion");
                habitacion = new HabitacionDTO(id, numero, piso, descripcion, caracteristicas, precioDiario, estado, tipoHabitacion);
                habitaciones.add(habitacion);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return habitaciones;
    }

}
