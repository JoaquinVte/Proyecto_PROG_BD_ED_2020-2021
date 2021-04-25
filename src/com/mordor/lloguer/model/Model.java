package com.mordor.lloguer.model;

import java.util.ArrayList;

public interface Model {

	public ArrayList<Empleado> getEmpleados();
	public boolean athenticate(String dni, String password);
	
}
