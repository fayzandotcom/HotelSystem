package com.apu.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.apu.obj.User;

public class UserDB {
	
	private SqlClient sqlClient;
	private SimpleDateFormat myFormat;
	
	public UserDB() throws ClassNotFoundException, SQLException {
		this.sqlClient = new SqlClient();
		myFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public ArrayList<User> getAll() throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		String query = "SELECT * FROM user";
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			
			User user = new User();
			user.setId(rs.getString("u_id"));
			user.setFname(rs.getString("u_fname"));
			user.setLname(rs.getString("u_lname"));
			user.setUsername(rs.getString("u_username"));
			user.setPassword(rs.getString("u_password"));
			user.setRole(rs.getString("u_role"));
			user.setEmail(rs.getString("u_email"));
			user.setDob(rs.getDate("u_dob"));
			user.setGender(rs.getString("u_gender"));
			
			userList.add(user);
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return userList;
		}else {
			return null;
		}
	}
	
	public User getById(int id) throws SQLException {
		User user = new User();
		String query = "SELECT * FROM user WHERE u_id="+id;
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			user.setId(rs.getString("u_id"));
			user.setFname(rs.getString("u_fname"));
			user.setLname(rs.getString("u_lname"));
			user.setUsername(rs.getString("u_username"));
			user.setPassword(rs.getString("u_password"));
			user.setRole(rs.getString("u_role"));
			user.setEmail(rs.getString("u_email"));
			user.setDob(rs.getDate("u_dob"));
			user.setGender(rs.getString("u_gender"));
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return user;
		}else {
			return null;
		}
	}
	
	public User getByUsernamePassword(String username, String password) throws SQLException {
		User user = new User();
		String query = "SELECT * FROM user WHERE u_username='"+username+"' AND u_password='"+password+"'";
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			user.setId(rs.getString("u_id"));
			user.setFname(rs.getString("u_fname"));
			user.setLname(rs.getString("u_lname"));
			user.setUsername(rs.getString("u_username"));
			user.setPassword(rs.getString("u_password"));
			user.setRole(rs.getString("u_role"));
			user.setEmail(rs.getString("u_email"));
			user.setDob(rs.getDate("u_dob"));
			user.setGender(rs.getString("u_gender"));
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return user;
		}else {
			return null;
		}
	}
	
	public boolean insert(User user) throws SQLException {
		String query = "INSERT INTO user(u_fname, u_lname, u_username, u_password, u_role, u_email, u_dob, u_gender) "
				+ "VALUES('"+user.getFname()+"','"+user.getLname()+"','"+user.getUsername()+"','"+user.getPassword()+"'"
						+ ",'"+user.getRole()+"','"+user.getEmail()+"','"+myFormat.format(user.getDob())+"','"+user.getGender()+"')";
		return sqlClient.executeUpdate(query);
	}
	
	public boolean update(User user) throws SQLException {
		String query = "UPDATE user SET u_fname='"+user.getFname()+"', u_lname='"+user.getLname()+"', "
						+ "u_username='"+user.getUsername()+"', u_password='"+user.getPassword()+"', u_role='"+user.getRole()+"', "
						+ "u_email='"+user.getEmail()+"', u_dob='"+myFormat.format(user.getDob())+"', u_gender='"+user.getGender()+"' "
						+ "WHERE u_id="+user.getId();
		return sqlClient.executeUpdate(query);
	}

	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM user WHERE u_id="+id;
		return sqlClient.executeUpdate(query);
	}

}
