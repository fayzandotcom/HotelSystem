package com.apu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	Properties prop = new Properties();
	InputStream input = null;
	
	public Config() {
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("core.properties"));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public String getXMLDataLocation(){
		return prop.getProperty("data.xml.location");
	}
	
	public String getAppContext(){
		return prop.getProperty("app.context");
	}
	
	public String getDBUrl(){
		return prop.getProperty("db.connection.connectionURL");
	}
	
	public String getDBUsername(){
		return prop.getProperty("db.connection.username");
	}
	
	public String getDBPassword(){
		return prop.getProperty("db.connection.password");
	}

}
