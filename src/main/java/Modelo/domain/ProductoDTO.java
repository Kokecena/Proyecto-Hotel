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
public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private double precioVenta;

    public ProductoDTO() {
    }

    public ProductoDTO(int idProducto) {
        this.idProducto = idProducto;
    }
    
    public ProductoDTO(int idProducto, String nombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
    }

    public ProductoDTO(String nombre, String descripcion, String unidadMedida, double precioVenta) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.precioVenta = precioVenta;
    }

    public ProductoDTO(int idProducto, String nombre, String descripcion, String unidadMedida, double precioVenta) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.precioVenta = precioVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    @Override
    public String toString() {
        return "Productos{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", unidadMedida=" + unidadMedida + ", precioVenta=" + precioVenta + '}';
    }

    
    
}
