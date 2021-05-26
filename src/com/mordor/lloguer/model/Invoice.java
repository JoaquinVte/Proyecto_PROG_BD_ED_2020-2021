package com.mordor.lloguer.model;

import java.sql.Date;

public class Invoice {
	
	private int id;
	private Date fecha;
	private float importeBase;
	private float importeIva;
	private int clienteId;
	
	public Invoice(int id, Date fecha, float importeBase, float importeIva, int clienteId) {
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
	
	public boolean equals(Object o) {
		if(o instanceof Invoice) {
			return ((Invoice) o).getId() == this.getId();
		}else 
			return false;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", fecha=" + fecha + ", importeBase=" + importeBase + ", importeIva=" + importeIva
				+ ", clienteId=" + clienteId + "]";
	}	

}
