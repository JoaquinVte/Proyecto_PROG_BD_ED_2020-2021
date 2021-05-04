package com.mordor.lloguer.model;

import java.util.ArrayList;
import java.util.List;

public interface Model {
	
	public static final int ASCENDING = 0;
	public static final int DESCENDING = 1;

	public ArrayList<Employee> getEmployees();
	public ArrayList<Employee> getEmployeesByField(String field,int direction);
	public boolean authenticate(String dni, String password) throws Exception;
	public List<String> getTableAttributes(String table);
	public boolean updateEmployee(Employee employee);
	public boolean addEmployee(Employee employee) throws Exception ;
	public boolean deleteEmployee(String dni);
	
}
