package com.mordor.lloguer.model;

import java.sql.Date;

public class Microbus extends Vehicle{
	
	protected int numPlazas;
	protected float medida;
	
	public Microbus(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet, int numPlazas, float medida) {
		super(matricula, precioDia, marca, descripcion, color, motor, cilindrada, fechaAdq, estado, carnet);
		this.numPlazas = numPlazas;
		this.medida = medida;
	}


	

}
