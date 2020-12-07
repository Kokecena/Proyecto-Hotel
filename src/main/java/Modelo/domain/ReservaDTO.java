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
public class ReservaDTO {

    private int idReserva;
    private int idHabitacion;
    private int idCliente;
    private int idTrabajador;
    private String tipoReserva;
    private String fechaReserva;
    private String fechaIngreso;
    private String fechaSalida;
    private double costoAlojamiento;
    private String observacion;
    private String estado;

    public ReservaDTO() {
    }

    public ReservaDTO(int idReserva) {
        this.idReserva = idReserva;
    }

    public ReservaDTO(int idHabitacion, int idCliente, int idTrabajador, String tipoReserva, String fechaReserva, String fechaIngreso, String fechaSalida, double costoAlojamiento, String observacion, String estado) {
        this.idHabitacion = idHabitacion;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.tipoReserva = tipoReserva;
        this.fechaReserva = fechaReserva;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.costoAlojamiento = costoAlojamiento;
        this.observacion = observacion;
        this.estado = estado;
    }

    public ReservaDTO(int idReserva, int idHabitacion, int idCliente, int idTrabajador, String tipoReserva, String fechaReserva, String fechaIngreso, String fechaSalida, double costoAlojamiento, String observacion, String estado) {
        this.idReserva = idReserva;
        this.idHabitacion = idHabitacion;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.tipoReserva = tipoReserva;
        this.fechaReserva = fechaReserva;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.costoAlojamiento = costoAlojamiento;
        this.observacion = observacion;
        this.estado = estado;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getTipoReserva() {
        return tipoReserva;
    }

    public void setTipoReserva(String tipoReserva) {
        this.tipoReserva = tipoReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public double getCostoAlojamiento() {
        return costoAlojamiento;
    }

    public void setCostoAlojamiento(double costoAlojamiento) {
        this.costoAlojamiento = costoAlojamiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reservas{" + "idReserva=" + idReserva + ", idHabitacion=" + idHabitacion + ", idCliente=" + idCliente + ", idTrabajador=" + idTrabajador + ", tipoReserva=" + tipoReserva + ", fechaReserva=" + fechaReserva + ", fechaIngreso=" + fechaIngreso + ", fechaSalida=" + fechaSalida + ", costoAlojamiento=" + costoAlojamiento + ", observacion=" + observacion + ", estado=" + estado + '}';
    }

}
