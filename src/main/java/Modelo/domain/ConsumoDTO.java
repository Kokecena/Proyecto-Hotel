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
public class ConsumoDTO {

    private int idConsumo;
    private int idReserva;
    private int idProducto;
    private int cantidad;
    private double precioVenta;
    private String estado;

    public ConsumoDTO() {
    }

    public ConsumoDTO(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public ConsumoDTO(int idConsumo, int idReserva, int idProducto, int cantidad, double precioVenta, String estado) {
        this.idConsumo = idConsumo;
        this.idReserva = idReserva;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.estado = estado;
    }

    public ConsumoDTO(int idReserva, int idProducto, int cantidad, double precioVenta, String estado) {
        this.idReserva = idReserva;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.estado = estado;
    }

    public int getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        this.idConsumo = idConsumo;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Consumos{" + "idConsumo=" + idConsumo + ", idReserva=" + idReserva + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", precioVenta=" + precioVenta + ", estado=" + estado + '}';
    }

}
