package com.mordor.lloguer.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Model {
	
	public static final int ASCENDING = 0;
	public static final int DESCENDING = 1;
	public static final String CAR = "COCHE";
	public static final String TRUCK = "CAMION";
	public static final String MINIBUS = "MICROBUS";
	public static final String VAN = "FURGONETA";

	public Connection getConnection() throws SQLException;
	
	// Employee
	public ArrayList<Employee> getEmployees();
	public ArrayList<Employee> getEmployeesByField(String field,int direction);
	public boolean authenticate(String dni, String password) throws Exception;
	public List<String> getTableAttributes(String table);
	public boolean updateEmployee(Employee employee);
	public boolean addEmployee(Employee employee) throws SQLException ;
	public boolean deleteEmployee(String dni) throws SQLException;
	
	
	// Customers
	public ArrayList<Customer> getCustomers() throws SQLException;
	public boolean addCustomer(Customer customer) throws SQLException;
	
	
	// Vehicles
	public ArrayList<Coche> getCars() throws Exception;
	public ArrayList<Camion> getTrucks() throws Exception;
	public ArrayList<Furgoneta> getVan() throws Exception;
	public ArrayList<Microbus> getMinibus() throws Exception;
}
