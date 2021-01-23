/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.TrabajadorDTO;
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
public class TrabajadorDaoJDBC implements TrabajadorDAO {

    private Connection conexionTransaccional;

    private static final String SQL_INSERT_PERSONA = "INSERT INTO persona (nombre, apaterno, amaterno, tipo_documento, num_documento, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_TRABAJADOR = "INSERT INTO trabajador (idpersona, sueldo, acceso, login, password, estado) VALUES (LAST_INSERT_ID(), ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona, trabajador SET persona.nombre = ?, persona.apaterno = ?, persona.amaterno = ?, persona.tipo_documento = ?, persona.num_documento = ?, persona.direccion = ?, persona.telefono = ?, persona.email = ?, trabajador.sueldo = ?, trabajador.acceso = ?, trabajador.login = ?, trabajador.password = ?, trabajador.estado = ?  WHERE persona.idpersona = trabajador.idpersona AND trabajador.idpersona = ?";
    private static final String SQL_DELETE = "DELETE p.*, t.* FROM persona p LEFT JOIN trabajador t ON t.idpersona = p.idpersona WHERE p.idpersona = ?";
    private static final String SQL_SELECT = "SELECT p.*, t.* FROM persona p INNER JOIN trabajador t ON p.idpersona = t.idpersona";
    private static final String SQL_SEARCH = "SELECT p.*, t.* FROM persona p INNER JOIN trabajador t ON p.idpersona = t.idpersona WHERE login = ?";

    public TrabajadorDaoJDBC() {
    }

    public TrabajadorDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(TrabajadorDTO trabajador) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registroPersona = 0;
        int registroCliente = 0;

        try {
            pst = cn.prepareStatement(SQL_INSERT_PERSONA);
            pst.setString(1, trabajador.getNombre());
            pst.setString(2, trabajador.getaPaterno());
            pst.setString(3, trabajador.getaMaterno());
            pst.setString(4, trabajador.getTipoDocumento());
            pst.setString(5, trabajador.getNumDocumento());
            pst.setString(6, trabajador.getDireccion());
            pst.setString(7, trabajador.getTelefono());
            pst.setString(8, trabajador.getEmail());
            registroPersona = pst.executeUpdate();

            pst = cn.prepareStatement(SQL_INSERT_TRABAJADOR);
            pst.setDouble(1, trabajador.getSueldo());
            pst.setString(2, trabajador.getAcceso());
            pst.setString(3, trabajador.getLogin());
            pst.setString(4, trabajador.getPassword());
            pst.setString(5, trabajador.getEstado());
            registroCliente = pst.executeUpdate();
        } finally {
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return registroPersona + registroCliente;
    }

    @Override
    public int update(TrabajadorDTO trabajador) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;

        try {
            pst = cn.prepareStatement(SQL_UPDATE);
            pst.setString(1, trabajador.getNombre());
            pst.setString(2, trabajador.getaPaterno());
            pst.setString(3, trabajador.getaMaterno());
            pst.setString(4, trabajador.getTipoDocumento());
            pst.setString(5, trabajador.getNumDocumento());
            pst.setString(6, trabajador.getDireccion());
            pst.setString(7, trabajador.getTelefono());
            pst.setString(8, trabajador.getEmail());
            pst.setDouble(9, trabajador.getSueldo());
            pst.setString(10, trabajador.getAcceso());
            pst.setString(11, trabajador.getLogin());
            pst.setString(12, trabajador.getPassword());
            pst.setString(13, trabajador.getEstado());
            pst.setInt(14, trabajador.getIdPersona());
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
    public int delete(TrabajadorDTO trabajador) throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;

        try {
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, trabajador.getIdPersona());
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
    public List<TrabajadorDTO> select() throws SQLException {
        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TrabajadorDTO trabajador = null;
        List<TrabajadorDTO> trabajadores = new ArrayList();

        try {
            pst = cn.prepareCall(SQL_SELECT);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String aPaterno = rs.getString("apaterno");
                String aMaterno = rs.getString("amaterno");
                String tipoDocumento = rs.getString("tipo_documento");
                String numDocumento = rs.getString("num_documento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                double sueldo = rs.getDouble("sueldo");
                String acceso = rs.getString("acceso");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String estado = rs.getString("estado");
                trabajador = new TrabajadorDTO(id, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email, sueldo, acceso, login, password, estado);
                trabajadores.add(trabajador);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }

        return trabajadores;
    }

    @Override
    public TrabajadorDTO userSearch(String user) throws SQLException {
        Connection cn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        TrabajadorDTO trabajador = null;
        try {
            pst = cn.prepareCall(SQL_SEARCH);
            pst.setString(1, user);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("idpersona");
                String nombre = rs.getString("nombre");
                String aPaterno = rs.getString("apaterno");
                String aMaterno = rs.getString("amaterno");
                String tipoDocumento = rs.getString("tipo_documento");
                String numDocumento = rs.getString("num_documento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                double sueldo = rs.getDouble("sueldo");
                String acceso = rs.getString("acceso");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String estado = rs.getString("estado");
                trabajador = new TrabajadorDTO(id, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email, sueldo, acceso, login, password, estado);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }
        return trabajador;
    }
}
