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
public class ClienteDTO extends PersonaDTO {

    private String codigoCliente;

    public ClienteDTO() {
    }

    public ClienteDTO(int idPersona) {
        super(idPersona);
    }

    public ClienteDTO(String nombre, String aPaterno, String aMaterno, String tipoDocumento, String numDocumento, String direccion, String telefono, String email, String codigoCliente) {
        super(nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email);
        this.codigoCliente = codigoCliente;
    }

    public ClienteDTO(int idPersona, String nombre, String aPaterno, String aMaterno, String tipoDocumento, String numDocumento, String direccion, String telefono, String email, String codigoCliente) {
        super(idPersona, nombre, aPaterno, aMaterno, tipoDocumento, numDocumento, direccion, telefono, email);
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    @Override
    public String toString() {
        return super.toString() + " Cliente{" + "codigoCliente=" + codigoCliente + '}';
    }

    
    
}
