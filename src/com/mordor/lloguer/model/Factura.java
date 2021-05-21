package com.mordor.lloguer.model;

import java.sql.Date;

public class Factura {
	
	private int id;
	private Date fecha;
	private float importeBase;
	private float importeIva;
	private int clienteId;
	
	public Factura(int id, Date fecha, float importeBase, float importeIva, int clienteId) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.importeBase = importeBase;
		this.importeIva = importeIva;
		this.clienteId = clienteId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public float getImporteBase() {
		return importeBase;
	}

	public void setImporteBase(float importeBase) {
		this.importeBase = importeBase;
	}

	public float getImporteIva() {
		return importeIva;
	}

	public void setImporteIva(float importeIva) {
		this.importeIva = importeIva;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}	

}
