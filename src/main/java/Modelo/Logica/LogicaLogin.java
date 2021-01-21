package Modelo.Logica;

import Controlador.Controlador;
import static Metodos.Ayuda.*;
import static Modelo.Datos.Conexion.getConnection;
import Modelo.Datos.TrabajadorDaoJDBC;
import Modelo.domain.TrabajadorDTO;
import Vista.Login.VentanaLogin;
import Vista.Sistema.VentanaSistema;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author jovan
 */
public class LogicaLogin {

    private String usuario;
    private String password;

    private VentanaLogin componente = null;
    private Connection conexion;
    private TrabajadorDTO trabajador = null;
    private TrabajadorDaoJDBC trabajadorDao = null;

    public LogicaLogin(VentanaLogin componente) {
        this.componente = componente;
        this.trabajadorDao = new TrabajadorDaoJDBC();
    }

    public void iniciarSesion() {
        String user = componente.getTxtUsuario().getText();
        try {
            trabajador = trabajadorDao.search(user);
            comprobarUsuario();
        } catch (SQLException ex) {
            ventanaMensaje(componente, ex.getMessage(), "No se pudo conectar a la base de datos...", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void comprobarUsuario() {
        String error = validarDatos();
        if (error.isEmpty() && trabajador != null) {
            if (trabajador.getPassword().equals(password) && comprobarConexion()) {
                componente.dispose();
                iniciarSistema(trabajador);
            } else {
                ventanaMensaje(componente, "Contraseña incorrecta", "No se pudo iniciar sesion", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            ventanaMensaje(componente,
                    error.isEmpty() ? "No se encontro el usuario" : error,
                    error.isEmpty() ? "¡El usuario ingresado no existe!" : "Falta rellenar campos",
                    error.isEmpty() ? JOptionPane.ERROR_MESSAGE : JOptionPane.WARNING_MESSAGE);
        }
    }

    private void iniciarSistema(TrabajadorDTO trabajador) {
        VentanaSistema vista = new VentanaSistema();
        LogicaSistema modelo = new LogicaSistema(vista, conexion, trabajador);
        Controlador c = new Controlador(modelo, vista);
    }

    private String validarDatos() {
        usuario = componente.getTxtUsuario().getText();
        password = verPassword(componente.getTxtPassword().getPassword());
        return comprobarCampos();
    }

    private String comprobarCampos() {
        StringBuilder sb = new StringBuilder();
        sb.append(comprobarCampo(usuario, "Usuario"));
        sb.append(comprobarCampo(password, "Contraseña"));
        return sb.toString();
    }

    private boolean comprobarConexion() {
        try {
            conexion = getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            return true;
        } catch (SQLException ex) {
            ventanaMensaje(componente, ex.getMessage(), "No se pudo conectar a la base de datos...", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public TrabajadorDTO getTrabajador() {
        return trabajador;
    }

}
