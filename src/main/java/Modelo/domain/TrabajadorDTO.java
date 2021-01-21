/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.domain;

/**
 *
 * @author jovan
 */
public class TrabajadorDTO extends PersonaDTO {

    private double sueldo;
    private String acceso;
    private String login;
    private String password;
    private String estado;

    public TrabajadorDTO() {
    }

    public TrabajadorDTO(int idPersona) {
        super(idPersona);
    }

    public TrabajadorDTO(String nombre, String aPaterno, String aMaterno, String tipoDocumento, String numDocumento, String direccion, String telefono, String email, double sueldo, String acceso, String login, String password, String estado) {
        super(nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email);
        this.sueldo = sueldo;
        this.acceso = acceso;
        this.login = login;
        this.password = password;
        this.estado = estado;
    }

    public TrabajadorDTO(int idPersona, String nombre, String aPaterno, String aMaterno, String tipoDocumento, String numDocumento, String direccion, String telefono, String email, double sueldo, String acceso, String login, String password, String estado) {
        super(idPersona, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email);
        this.sueldo = sueldo;
        this.acceso = acceso;
        this.login = login;
        this.password = password;
        this.estado = estado;
    }

    public TrabajadorDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return super.toString() + " Trabajador{" + "sueldo=" + sueldo + ", acceso=" + acceso + ", login=" + login + ", password=" + password + ", estado=" + estado + '}';
    }

}
