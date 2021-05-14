package com.mordor.lloguer.model;

import java.sql.Date;

public abstract class Vehicle {
	
	protected String matricula;
	protected float precioDia;
	protected String marca;
	protected String descripcion;
	protected String color;
	protected String motor;
	protected int cilindrada;
	protected Date fechaAdq;
	protected String estado;
	protected String carnet;
	
	public Vehicle(String matricula, float precioDia, String marca, String descripcion, String color, String motor,
			int cilindrada, Date fechaAdq, String estado, String carnet) {
		super();
		this.matricula = matricula;
		this.precioDia = precioDia;
		this.marca = marca;
		this.descripcion = descripcion;
		this.color = color;
		this.motor = motor;
		this.cilindrada = cilindrada;
		this.fechaAdq = fechaAdq;
		this.estado = estado;
		this.carnet = carnet;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public float getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(float precioDia) {
		this.precioDia = precioDia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public Date getFechaAdq() {
		return fechaAdq;
	}

	public void setFechaAdq(Date fechaAdq) {
		this.fechaAdq = fechaAdq;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}
	
}
