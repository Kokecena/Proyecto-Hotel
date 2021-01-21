/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Datos;

import static Modelo.Datos.Conexion.getConnection;
import Modelo.domain.ClienteDTO;
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
public class ClienteDaoJDBC implements ClienteDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT p.idpersona, p.nombre, p.apaterno, p.amaterno, p.tipo_documento, p.num_documento, p.direccion, p.telefono, p.email, c.codigo_cliente FROM persona p INNER JOIN cliente c ON p.idpersona = c.idpersona";
    private static final String SQL_INSERT_PERSONA = "INSERT INTO persona (nombre, apaterno, amaterno, tipo_documento, num_documento, direccion, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_CLIENTE = "INSERT INTO cliente (idpersona, codigo_cliente) VALUES (LAST_INSERT_ID(), ?)";
    private static final String SQL_UPDATE = "UPDATE persona, cliente SET persona.nombre = ?, persona.apaterno = ?, persona.amaterno = ?, persona.tipo_documento = ?, persona.num_documento = ?, persona.direccion = ?, persona.telefono = ?, persona.email = ?, cliente.codigo_cliente = ?  WHERE persona.idpersona = cliente.idpersona AND cliente.idpersona = ?";
    private static final String SQL_DELETE = "DELETE p.*, c.* FROM persona p LEFT JOIN cliente c ON c.idpersona = p.idpersona WHERE p.idpersona = ?";

    public ClienteDaoJDBC() {
    }

    public ClienteDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(ClienteDTO cliente) throws SQLException {

        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registroPersona = 0;
        int registroCliente = 0;

        try {
            pst = cn.prepareStatement(SQL_INSERT_PERSONA);
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getaPaterno());
            pst.setString(3, cliente.getaMaterno());
            pst.setString(4, cliente.getTipoDocumento());
            pst.setString(5, cliente.getNumDocumento());
            pst.setString(6, cliente.getDireccion());
            pst.setString(7, cliente.getTelefono());
            pst.setString(8, cliente.getEmail());
            registroPersona = pst.executeUpdate();

            pst = cn.prepareStatement(SQL_INSERT_CLIENTE);
            pst.setString(1, cliente.getCodigoCliente());
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
    public int update(ClienteDTO cliente) throws SQLException {

        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;

        try {
            pst = cn.prepareStatement(SQL_UPDATE);
            pst.setString(1, cliente.getNombre());
            pst.setString(2, cliente.getaPaterno());
            pst.setString(3, cliente.getaMaterno());
            pst.setString(4, cliente.getTipoDocumento());
            pst.setString(5, cliente.getNumDocumento());
            pst.setString(6, cliente.getDireccion());
            pst.setString(7, cliente.getTelefono());
            pst.setString(8, cliente.getEmail());
            pst.setString(9, cliente.getCodigoCliente());
            pst.setInt(10, cliente.getIdPersona());
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
    public int delete(ClienteDTO cliente) throws SQLException {

        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        int registros = 0;

        try {
            pst = cn.prepareStatement(SQL_DELETE);
            pst.setInt(1, cliente.getIdPersona());
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
    public List<ClienteDTO> select() throws SQLException {

        Connection cn = conexionTransaccional != null ? conexionTransaccional : getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        ClienteDTO cliente = null;
        List<ClienteDTO> clientes = new ArrayList();

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
                String codigoCliente = rs.getString("codigo_cliente");
                cliente = new ClienteDTO(id, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email, codigoCliente);
                clientes.add(cliente);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(pst);
            if (this.conexionTransaccional == null) {
                Conexion.close(cn);
            }
        }

        return clientes;
    }

}
