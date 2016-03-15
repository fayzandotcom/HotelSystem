package com.apu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.apu.util.Config;

public class SqlClient {
	
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private String dbUrl;
	private String username;
	private String password;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public SqlClient() throws ClassNotFoundException, SQLException {
		Config dbConfig = new Config();
		this.dbUrl = dbConfig.getDBUrl();
		this.username = dbConfig.getDBUsername();
		this.password = dbConfig.getDBPassword();
		
		Class.forName(this.JDBC_DRIVER);
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		this.connect();
		rs = stmt.executeQuery(query);
		return rs;
	}
	
	public boolean executeUpdate(String query) throws SQLException {
		this.connect();
		if(stmt.executeUpdate(query)>0) {
			return true;
		}else{
			return false;
		}
	}
	
	public int executeGetKey(String query) throws SQLException {
		this.connect();
		PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);  
		pstmt.executeUpdate();  
		ResultSet keys = pstmt.getGeneratedKeys();    
		keys.next();  
		return keys.getInt(1);
	}
	
	private void connect() throws SQLException {
		conn = DriverManager.getConnection(dbUrl, username, password);
		stmt = conn.createStatement();
	}
	
	public void close() throws SQLException {
		conn.close();
		stmt.close();
		rs.close();
	}

}
