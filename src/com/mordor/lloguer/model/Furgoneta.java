package com.mordor.lloguer.model;

import java.sql.Date;

public class Furgoneta extends Vehicle {

	protected int MMA;

	public Furgoneta(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet, int mMA) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaAdq, estado, carnet);
		MMA = mMA;
	}

	public int getMMA() {
		return MMA;
	}

	public void setMMA(int mMA) {
		MMA = mMA;
	}


}
