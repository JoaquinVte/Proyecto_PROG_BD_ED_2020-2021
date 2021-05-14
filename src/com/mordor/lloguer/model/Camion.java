package com.mordor.lloguer.model;

import java.sql.Date;

public class Camion extends Vehicle {

	protected int numRuedas;
	protected int MMA;
	
	public Camion(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet, int numRuedas, int mMA) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaAdq, estado, carnet);
		this.numRuedas = numRuedas;
		MMA = mMA;
	}

	public int getNumRuedas() {
		return numRuedas;
	}

	public void setNumRuedas(int numRuedas) {
		this.numRuedas = numRuedas;
	}

	public int getMMA() {
		return MMA;
	}

	public void setMMA(int mMA) {
		MMA = mMA;
	}
		
}
