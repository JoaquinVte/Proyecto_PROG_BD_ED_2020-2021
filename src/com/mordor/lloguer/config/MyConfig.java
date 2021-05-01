package com.mordor.lloguer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class MyConfig {

	private static MyConfig instancia = new MyConfig();

	private String defaultFile = "db.properties";
	private String appFile = "app.properties";
	private String key = "ieslavereda.es";
	private Properties properties;
	private Map<String, String> propiedadesSeguras;

	private MyConfig() {

		Properties defaultProperties = new Properties();

		try (FileInputStream fis = new FileInputStream(new File(defaultFile))) {

			defaultProperties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

		properties = new Properties(defaultProperties);

		try (FileInputStream fis = new FileInputStream(new File(appFile))) {

			properties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			checkEncriptedProperties();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkEncriptedProperties() throws Exception {

		// Creamos un map con todas las propiedades que deben ser encriptadas,
		// junto con las propiedades que nos indican el estado de estas.
		propiedadesSeguras = new HashMap<String, String>();
		propiedadesSeguras.put("ORACLE_DB_USERNAME", "IS_ORACLE_DB_USERNAME_ENCRYPTED");
		propiedadesSeguras.put("ORACLE_DB_PASSWORD", "IS_ORACLE_DB_PASSWORD_ENCRYPTED");
		
		// Si no existe la propiedad que indica si esta encriptado una key, la creamos y la ponemos a false
		for(String isEncripted : propiedadesSeguras.values()) {
			if(!properties.containsKey(isEncripted))
				properties.put(isEncripted, "false");
		}

		// Encriptamos las claves si fuera necesario antes de leer las propiedades
		for (String property : propiedadesSeguras.keySet())
			encryptPropertyValue(property, propiedadesSeguras.get(property));
		
		// Guardamos las propiedades. 
		// De esta forma las propiedades que no estaban encriptadas, 
		// pasaran a disco encriptadas
		guardar();

	}

	private void encryptPropertyValue(String propertyKey, String isPropertyKeyEncrypted) {

		// Retrieve boolean properties value to see if password is already
		// encrypted or not
		String isEncrypted = properties.getProperty(isPropertyKeyEncrypted);

		// Check if password is encrypted?
		if (isEncrypted.equals("false")) {
			String tmpPwd = properties.getProperty(propertyKey);
			String encryptedPassword = encrypt(tmpPwd);

			// Overwrite password with encrypted password in the properties file
			// using Apache Commons Cinfiguration library
			properties.setProperty(propertyKey, encryptedPassword);
			// Set the boolean flag to true to indicate future encryption
			// operation that password is already encrypted
			properties.setProperty(isPropertyKeyEncrypted, "true");
			// Save the properties file
			guardar();
		}
	}
	
	public void guardar() {
		try (FileOutputStream fos = new FileOutputStream(new File(appFile))) {
			properties.store(fos, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String encrypt(String tmpPwd) {
		// Encrypt
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		String encryptedPassword = encryptor.encrypt(tmpPwd);
		return encryptedPassword;
	}

	private String decryptPropertyValue(String propertyKey) {

		String encryptedPropertyValue = properties.getProperty(propertyKey);

		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(key);
		String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);

		return decryptedPropertyValue;
	}

	public static MyConfig getInstancia() {
		return instancia;
	}

	public String getDriver() {
		return properties.getProperty("ORACLE_DB_DRIVER_CLASS");
	}
	public String getURL() {
		return properties.getProperty("ORACLE_DB_URL");
	}	
	public String getUsername() {
		return decryptPropertyValue("ORACLE_DB_USERNAME");
	}
	public String getPassword() {
		return decryptPropertyValue("ORACLE_DB_PASSWORD");
	}
	
	public void setDriver(String driver) {
		properties.put("ORACLE_DB_DRIVER_CLASS", driver);
		guardar();
	}
	public void setURL(String url) {
		properties.put("ORACLE_DB_URL", url);
		guardar();
	}
	public void setUsername(String username) {
		properties.put("ORACLE_DB_USERNAME", encrypt(username));
		guardar();
	}
	public void setPassword(String password) {
		properties.put("ORACLE_DB_PASSWORD", encrypt(password));
		guardar();
	}



}
