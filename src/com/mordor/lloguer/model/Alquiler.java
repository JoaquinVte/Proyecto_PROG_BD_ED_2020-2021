package com.mordor.lloguer.model;

import java.sql.Date;

public class Alquiler {

	private int facturaId;
	private String vehiculoMatricula;
	private Date fechaInicio;
	private Date fechaFin;
	private float precio;	
	
	public Alquiler(int facturaId, String vehiculoMatricula, Date fechaInicio, Date fechaFin, float precio) {
		super();
		this.facturaId = facturaId;
		this.vehiculoMatricula = vehiculoMatricula;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.precio = precio;
	}
	
	public int getFacturaId() {
		return facturaId;
	}
	public void setFacturaId(int facturaId) {
		this.facturaId = facturaId;
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
	
	
	
}
