package com.mordor.lloguer.model;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;
import oracle.jdbc.OracleTypes;

public class MyOracleDB implements Model {

	// STATEMENTS
	@Override
	public ArrayList<Employee> getEmployees() {
		return getEmployees(null);
	}

	@Override
	public boolean authenticate(String dni, String password) throws Exception {

		DataSource ds = MyDataSource.getOracleDataSource();
		boolean authenticated = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND password=ENCRYPT_PASWD.encrypt_val(?)";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setString(1, dni);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			int cantidad = 0;

			if (rs.next())
				cantidad = rs.getInt(1);

			authenticated = cantidad == 1;

			if (!authenticated)
				throw new Exception("Usuario/Password incorrecto");

		} catch (SQLException e) {
			if (e.getErrorCode() == 1017)
				throw new Exception("Connection refused. Check server configuration.");
			else
				throw new Exception(e.getMessage());			
		}

		return authenticated;
	}

	@Override
	public List<String> getTableAttributes(String table) {

		List<String> atributos = new ArrayList<String>();
		DataSource ds = MyDataSource.getOracleDataSource();

		/*
		 * SELECT column_name FROM all_tab_columns WHERE table_name = 'EMPLEADO'
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
			e.printStackTrace();
		}

		return atributos;

	}

	@Override
	public ArrayList<Employee> getEmployeesByField(String field, int direction) {

		String where = " ORDER BY " + field;

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
	
	public boolean addEmployee(Employee employee) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "INSERT INTO EMPLEADO (DNI,nombre,apellidos,domicilio,CP,email,fechaNac,cargo,CHANGEDBY,CHANGEDTS) VALUES (?,?,?,?,?,?,?,?,?,?)";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(query);) {

			int pos = 0;

			pstmt.setString(++pos, employee.getDNI());
			pstmt.setString(++pos, employee.getNombre());
			pstmt.setString(++pos, employee.getApellidos());
			pstmt.setString(++pos, employee.getDomicilio());
			pstmt.setString(++pos, employee.getCP());
			pstmt.setString(++pos, employee.getEmail());
			pstmt.setDate(++pos, employee.getFechaNac());
			pstmt.setString(++pos, employee.getCargo());
			pstmt.setString(++pos, "mlloguer_addEmployee");
			pstmt.setTimestamp(++pos, new Timestamp(System.currentTimeMillis()));

			added = (pstmt.executeUpdate() == 1) ? true : false;

		}

		return added;
	}

	public boolean updateEmployee(Employee employee) {
		boolean actualizado = false;
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection(); Statement stmt = con.createStatement();) {

			String query = "UPDATE EMPLEADO SET nombre='" + employee.getNombre() + "', " + "apellidos='"
					+ employee.getApellidos() + "'," + "domicilio='" + employee.getDomicilio() + "'," + "CP='"
					+ employee.getCP() + "'," + "email='" + employee.getEmail() + "'," + "fechaNac=TO_DATE('"
					+ employee.getFechaNac() + "','yyyy-mm-dd'), " + "cargo='" + employee.getCargo() + "' ";
			
			if(employee.getPassword()!=null)
				query += ",password=ENCRYPT_PASWD.encrypt_val('"+ employee.getPassword()+"') ";
			
			query += "WHERE DNI='"+employee.getDNI()+"'";

			actualizado = (stmt.executeUpdate(query) == 1) ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actualizado;
	}

	private ArrayList<Employee> getEmployees(String where) {
		DataSource ds = MyDataSource.getOracleDataSource();
		ArrayList<Employee> empleados = new ArrayList<Employee>();

		String query = "SELECT * FROM EMPLEADO";

		if (where != null)
			query += where;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {

			Employee empleado;

			while (rs.next()) {

				empleado = new Employee(rs.getInt("IDEMPLEADO"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("domicilio"), rs.getString("CP"), rs.getString("email"),
						rs.getDate("fechaNac"), rs.getString("cargo"),null);

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return empleados;
	}

	@Override

	public boolean deleteEmployee(String dni) throws SQLException {
		
		boolean removed = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "DELETE FROM EMPLEADO WHERE DNI=?";
						
		try (Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);) {

			int pos=0;
			
			pstmt.setString(++pos, dni);
			
						
			removed = (pstmt.executeUpdate()==1)?true:false;
			
		} 
					
		return removed;
	}
	

	@Override
	public ArrayList<Customer> getCustomers() throws SQLException {
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call ?:=GESTIONALQUILER.listarClientes() }";
		ResultSet rs = null;
		
		try(
				Connection con = ds.getConnection();
				CallableStatement cs = con.prepareCall(query);
				
			){
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			rs = (ResultSet)cs.getObject(1);
			
			Customer customer;

			String DNI;
			String name;
			String surname;
			String address;
			String CP;
			String email;
			Date birthday;
			char license;
			Blob photo;
			byte[] content = null;

			
			while(rs.next()) {
				
				DNI = rs.getString("DNI");
				name = rs.getString("nombre");
				surname = rs.getString("apellidos");
				address= rs.getString("domicilio"); 
				CP = rs.getString("CP");
				email = rs.getString("email");
				birthday = rs.getDate("fechaNac"); 
				license = rs.getString("carnet").charAt(0);
				photo = rs.getBlob("foto");
				
				if (photo != null) {
					content = photo.getBytes(1L, (int) photo.length());					
				} 
				customer = new Customer(DNI, name, surname, address, CP, email, birthday, license, content);
				customers.add(customer);
			}
		} finally {
			if(rs!=null)
				rs.close();
		}
		
		return customers;
	}


//	@Override
//	public ArrayList<Customer> getCustomers() throws SQLException {
//		
//		ArrayList<Customer> customers = new ArrayList<Customer>();
//		DataSource ds = MyDataSource.getOracleDataSource();
//		String query = "SELECT * FROM CLIENTE";
//		
//		try(
//				Connection con = ds.getConnection();
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(query)
//			){
//			
//			Customer customer;
//
//			String DNI;
//			String name;
//			String surname;
//			String address;
//			String CP;
//			String email;
//			Date birthday;
//			char license;
//			Blob photo;
//			byte[] content = null;
//
//			
//			while(rs.next()) {
//				
//				DNI = rs.getString("DNI");
//				name = rs.getString("nombre");
//				surname = rs.getString("apellidos");
//				address= rs.getString("domicilio"); 
//				CP = rs.getString("CP");
//				email = rs.getString("email");
//				birthday = rs.getDate("fechaNac"); 
//				license = rs.getString("carnet").charAt(0);
//				photo = rs.getBlob("foto");
//				
//				if (photo != null) {
//					content = photo.getBytes(1L, (int) photo.length());					
//				} 
//				customer = new Customer(DNI, name, surname, address, CP, email, birthday, license, content);
//				customers.add(customer);
//			}
//		} 	
//		
//		return customers;
//	}

	@Override
	public boolean addCustomer(Customer customer) throws SQLException {
		boolean added = false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "{ call GESTIONALQUILER.grabarCliente(?,?,?,?,?,?,?,?)}";

		try (Connection con = ds.getConnection(); 
				CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;
						
			cstmt.setString(++pos, customer.getDNI());
			cstmt.setString(++pos, customer.getNombre());
			cstmt.setString(++pos, customer.getApellidos());
			cstmt.setString(++pos, customer.getEmail());
			cstmt.setDate(++pos, customer.getFechaNac());
			cstmt.setString(++pos, String.valueOf(customer.getCarnet()));
			cstmt.setBytes(++pos, customer.getFoto());
			cstmt.setString(++pos, customer.getDomicilio());
			cstmt.setString(++pos, customer.getCP());			

			added = (cstmt.executeUpdate() == 1) ? true : false;
			}
		return added;
	}

	private ArrayList<Vehicle> getVehicles(String table) throws Exception {

		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "{ call GESTIONVEHICULOS.listarvehiculos(?,?)}";
		

		try (Connection con = ds.getConnection(); CallableStatement cstmt = con.prepareCall(query);) {

			int pos = 0;
			
			cstmt.setString(++pos, table);
			cstmt.registerOutParameter(++pos, OracleTypes.CURSOR);
			cstmt.execute();
			
			try(ResultSet rs = (ResultSet)cstmt.getObject(2)){
				
				String matricula,marca,descripcion,color,motor,estado,carnet;
				float precioDia,medida;
				int cilindrada,numPuertas,numPlazas,MMA,numRuedas;
				Date fechaAdq;
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				
				while(rs.next()) {						
					
					matricula = rs.getString("c1");
					marca = rs.getString("c2");
					descripcion = rs.getString("c3");
					color = rs.getString("c4");
					motor = rs.getString("c5");
					fechaAdq = new Date(format.parse(rs.getString("c6")).getTime());
					estado = rs.getString("c7");
					carnet = rs.getString("c8");
					precioDia=rs.getFloat("n1");
					cilindrada = rs.getInt("n2");
					
					if(table.equals(Model.CAR)) {
						
						numPuertas = rs.getInt("n3");
						numPlazas = rs.getInt("n4");

						vehicles.add(new Coche(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
								fechaAdq, estado, carnet, numPlazas, numPuertas));

					} else if(table.equals(Model.MINIBUS)) {
						
						numPlazas = rs.getInt("n3");
						medida = rs.getFloat("n4");
						
						vehicles.add(new Microbus(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
								fechaAdq, estado, carnet, numPlazas, medida));
						
					}else if(table.equals(Model.VAN)) {		
						
						MMA = rs.getInt("n3");
						
						vehicles.add(new Furgoneta(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
								fechaAdq, estado, carnet, MMA));
						
					}else if(table.equals(Model.TRUCK)) {
						MMA = rs.getInt("n3");
						numRuedas = rs.getInt("n4");
						
						vehicles.add(new Camion(matricula, precioDia, marca, descripcion, color, motor, cilindrada,
								fechaAdq, estado, carnet, MMA,numRuedas));
						
					}					
				}	
			}	
		}

		return vehicles;
	}

	@Override
	public ArrayList<Coche> getCars() throws Exception {
						
		return new ArrayList<Coche>(getVehicles(Model.CAR).stream()
														.map(v -> ((Coche)v))
														.collect(Collectors.toList()));
	}

	@Override
	public ArrayList<Camion> getTrucks() throws Exception {
		
		return new ArrayList<Camion>(getVehicles(Model.TRUCK).stream()
														.map(v -> ((Camion)v))
														.collect(Collectors.toList()));
	}

	@Override
	public ArrayList<Furgoneta> getVan() throws Exception {
		
		return new ArrayList<Furgoneta>(getVehicles(Model.VAN).stream()
														.map(v -> ((Furgoneta)v))
														.collect(Collectors.toList()));
	}

	@Override
	public ArrayList<Microbus> getMinibus() throws Exception {
		
		return new ArrayList<Microbus>(getVehicles(Model.MINIBUS).stream()
														.map(v -> ((Microbus)v))
														.collect(Collectors.toList()));
	}

	@Override
	public Connection getConnection() throws SQLException {
		DataSource ds = MyDataSource.getOracleDataSource();
		boolean authenticated = false;
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND password=ENCRYPT_PASWD.encrypt_val(?)";

		return ds.getConnection();
	}


}
