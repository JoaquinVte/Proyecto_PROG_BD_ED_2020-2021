package com.mordor.lloguer.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class MyOracleDB implements Model {

	// STATEMENTS
	@Override
	public ArrayList<Empleado> getEmpleados() {
		
		DataSource ds = MyDataSource.getOracleDataSource();
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLEADO");) {

			Empleado empleado;

						
			while (rs.next()) {
				
				empleado = new Empleado(rs.getInt("IDEMPLEADO"), 
										rs.getString("DNI"), 
										rs.getString("nombre"),
										rs.getString("apellidos"),
										rs.getString("domicilio"),
										rs.getString("CP"),
										rs.getString("email"),
										rs.getDate("fechaNac"),
										rs.getString("cargo"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empleados;
	}
	
	// NUNCA REALIZAR ESTO CON UN STATEMENT PELIGRO DE INYECCION DE SQL
	@Override
	public boolean athenticate(String dni, String password) {
		
		DataSource ds = MyDataSource.getOracleDataSource();
		boolean authenticated = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI='" + dni + "' AND password='"+password+"'";
		//System.out.println(query);
		try(Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)){
			
			int cantidad=0;
			
			if(rs.next())
				cantidad = rs.getInt(1);
			
			authenticated = cantidad == 1;
				
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return authenticated;
	}
}
