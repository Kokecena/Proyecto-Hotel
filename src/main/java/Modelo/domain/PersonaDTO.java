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
public class PersonaDTO {

    private int idPersona;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String tipoDocumento;
    private String numDocumento;
    private String direccion;
    private String telefono;
    private String email;

    public PersonaDTO() {
    }

    public PersonaDTO(int idPersona) {
        this.idPersona = idPersona;
    }

    public PersonaDTO(int idPersona, String nombre, String aPaterno, String aMaterno) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
    }

    public PersonaDTO(String nombre, String aPaterno, String aMaterno, String tipoDocumento, String numDocumento, String direccion, String telefono, String email) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public PersonaDTO(int idPersona, String nombre, String aPaterno, String aMaterno, String tipoDocumento, String numDocumento, String direccion, String telefono, String email) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nombre=" + nombre + ", aPaterno=" + aPaterno + ", aMaterno=" + aMaterno + ", tipoDocumento=" + tipoDocumento + ", numDocumento=" + numDocumento + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + '}';
    }
 
}
