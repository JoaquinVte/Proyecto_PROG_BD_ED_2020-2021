package com.mordor.lloguer.model;

import java.sql.Date;

public class Rent {

	private int idAlquiler;
	private int idFactura;
	private String vehiculoMatricula;
	private Date fechaInicio;
	private Date fechaFin;
	private float precio;
	
	public Rent(int idAlquiler, int idFactura, String vehiculoMatricula, Date fechaInicio, Date fechaFin,
			float precio) {
		super();
		this.idAlquiler = idAlquiler;
		this.idFactura = idFactura;
		this.vehiculoMatricula = vehiculoMatricula;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}
	public int getFacturaId() {
		return idFactura;
	}
	public void setFacturaId(int facturaId) {
		this.idFactura = facturaId;
	}
	public String getVehiculoMatricula() {
		return vehiculoMatricula;
	}
	public void setVehiculoMatricula(String vehiculoMatricula) {
		this.vehiculoMatricula = vehiculoMatricula;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getIdAlquiler() {
		return idAlquiler;
	}
	public void setIdAlquiler(int idAlquiler) {
		this.idAlquiler = idAlquiler;
	}
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
}
