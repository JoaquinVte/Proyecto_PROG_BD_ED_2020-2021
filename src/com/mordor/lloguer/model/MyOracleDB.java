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
}
