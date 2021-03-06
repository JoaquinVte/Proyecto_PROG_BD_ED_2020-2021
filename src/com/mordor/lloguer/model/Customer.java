package com.mordor.lloguer.model;

import java.sql.Date;

public class Customer {
	
	private int idCliente;
	private String DNI;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String CP;
	private String email;
	private Date fechaNac;
	private char carnet;
	private byte[] foto;
		
	public Customer(String dNI, String nombre, String apellidos, String domicilio, String cP, String email,
			Date fechaNac, char carnet, byte[] foto) {
		super();

		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.carnet = carnet;
		this.foto = foto;
	}
	public Customer(int idCliente,String dNI, String nombre, String apellidos, String domicilio, String cP, String email,
			Date fechaNac, char carnet, byte[] foto) {
		this(dNI, nombre, apellidos, domicilio, cP, email, fechaNac, carnet, foto);
		this.idCliente = idCliente;
	}
	

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public char getCarnet() {
		return carnet;
	}

	public void setCarnet(char carnet) {
		this.carnet = carnet;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getIdCliente() {
		return idCliente;
	}
	
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}	
	
}
