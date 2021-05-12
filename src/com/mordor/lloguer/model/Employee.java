package com.mordor.lloguer.model;

import java.sql.Date;

public class Employee {

	private int id;
	private String DNI;
	private String nombre;
	private String apellidos;
	private String domicilio;
	private String CP;
	private String email;
	private Date fechaNac;
	private String cargo;
	private String password;
	
	public Employee(int id, String dNI, String nombre, String apellidos, String domicilio, String cP, String email,
			Date fechaNac, String cargo,String password) {
		this(dNI,nombre,apellidos,domicilio,cP,email,fechaNac,cargo,password);
		this.id = id;
	}
	public Employee( String dNI, String nombre, String apellidos, String domicilio, String cP, String email,
			Date fechaNac, String cargo, String password) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.domicilio = domicilio;
		CP = cP;
		this.email = email;
		this.fechaNac = fechaNac;
		this.cargo = cargo;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Employee) {
			return ((Employee) o).getDNI().equalsIgnoreCase(DNI);
		}else
			return false;
	}
	@Override
	public String toString() {
		return "Empleado [id=" + id + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", domicilio=" + domicilio + ", CP=" + CP + ", email=" + email + ", fechaNac=" + fechaNac + ", cargo="
				+ cargo + "]";
	}
		
}
