/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.domain;

import java.util.Date;

/**
 *
 * @author jovan
 */
public class PagoDTO {
    private int idPago;
    private int idReserva;
    private String tipoComprobante;
    private String numComprobante;
    private double igv;
    private double totalPago;
    private Date fechaEmision;
    private Date fechaPago;

    public PagoDTO() {
    }

    public PagoDTO(int idPago) {
        this.idPago = idPago;
    }

    public PagoDTO(int idReserva, String tipoComprobante, String numComprobante, double igv, double totalPago, Date fechaEmision, Date fechaPago) {
        this.idReserva = idReserva;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.igv = igv;
        this.totalPago = totalPago;
        this.fechaEmision = fechaEmision;
        this.fechaPago = fechaPago;
    }

    public PagoDTO(int idPago, int idReserva, String tipoComprobante, String numComprobante, double igv, double totalPago, Date fechaEmision, Date fechaPago) {
        this.idPago = idPago;
        this.idReserva = idReserva;
        this.tipoComprobante = tipoComprobante;
        this.numComprobante = numComprobante;
        this.igv = igv;
        this.totalPago = totalPago;
        this.fechaEmision = fechaEmision;
        this.fechaPago = fechaPago;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Pagos{" + "idPago=" + idPago + ", idReserva=" + idReserva + ", tipoComprobante=" + tipoComprobante + ", numComprobante=" + numComprobante + ", igv=" + igv + ", totalPago=" + totalPago + ", fechaEmision=" + fechaEmision + ", fechaPago=" + fechaPago + '}';
    }
    
}
