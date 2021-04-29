package com.mordor.lloguer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MyOracleDB implements Model {
	
	// STATEMENTS
	@Override
	public ArrayList<Employee> getEmployees() {
		return getEmployees(null);		
	}
	@Override
	public boolean athenticate(String dni, String password) {

		DataSource ds = MyDataSource.getOracleDataSource();
		boolean authenticated = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND password=?";
		
		try (
				Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(query)
			){

			pstmt.setString(1, dni);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			int cantidad = 0;

			if (rs.next())
				cantidad = rs.getInt(1);

			authenticated = cantidad == 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return authenticated;
	}

	@Override
	public List<String> getTableAttributes(String table) {

		List<String> atributos = new ArrayList<String>();
		DataSource ds = MyDataSource.getOracleDataSource();
	
		/*
		SELECT column_name
		FROM all_tab_columns 
		WHERE table_name = 'EMPLEADO'
		*/

		String fields = "COLUMN_NAME";
		String tables = "all_tab_columns";
		String where = "TABLE_NAME = '" + table + "'";
		String query = "SELECT " + fields + " FROM " + tables + " WHERE " + where;

		try (Connection con = ds.getConnection();
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(query);) {

			while (rs.next()) 
				atributos.add(rs.getString("COLUMN_NAME"));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return atributos;

	}
	@Override
	public ArrayList<Employee> getEmployeesByField(String field, int direction) {
		
		String where  = " ORDER BY " + field ;
		
		switch (direction) {
		case ASCENDING:
			where += " ASC ";
			break;
		case DESCENDING:
			where += " DESC ";
			break;
		default:
			where += " ASC ";
			break;
		}
		
		return getEmployees(where);
	}
	

	
	private ArrayList<Employee> getEmployees(String where){
		DataSource ds = MyDataSource.getOracleDataSource();
		ArrayList<Employee> empleados = new ArrayList<Employee>();
		
		String query = "SELECT * FROM EMPLEADO";
		
		if(where!=null)
			query+=where;

		try (
				Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			){

			Employee empleado;

						
			while (rs.next()) {
				
				empleado = new Employee(rs.getInt("IDEMPLEADO"), 
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
