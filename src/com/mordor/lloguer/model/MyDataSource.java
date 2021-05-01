package com.mordor.lloguer.model;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.mordor.lloguer.config.MyConfig;

import oracle.jdbc.pool.OracleDataSource;

public class MyDataSource {

	public static DataSource getOracleDataSource() {

		// Objeto DataSource que devolveremos
		OracleDataSource oracleDS = null;

		try {

			// Generamos el DataSource con los datos URL, user y passwd necesarios
			oracleDS = new OracleDataSource();
			oracleDS.setURL(MyConfig.getInstancia().getURL());
			oracleDS.setUser(MyConfig.getInstancia().getUsername());
			oracleDS.setPassword(MyConfig.getInstancia().getPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return oracleDS;
	}
}
