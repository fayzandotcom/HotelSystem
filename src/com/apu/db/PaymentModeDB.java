package com.apu.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.apu.obj.PaymentMode;

public class PaymentModeDB {
	
	private SqlClient sqlClient;
	
	public PaymentModeDB() throws ClassNotFoundException, SQLException {
		this.sqlClient = new SqlClient();
	}
	
	public ArrayList<PaymentMode> getAll() throws SQLException {
		ArrayList<PaymentMode> paymentModeList = new ArrayList<PaymentMode>();
		String query = "SELECT * FROM payment_mode";
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			
			PaymentMode paymentMode = new PaymentMode();
			paymentMode.setId(rs.getString("paym_id"));
			paymentMode.setName(rs.getString("paym_name"));
			paymentMode.setDesc(rs.getString("paym_desc"));
			
			paymentModeList.add(paymentMode);
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return paymentModeList;
		}else {
			return null;
		}
	}
	
	public PaymentMode getById(int id) throws SQLException {
		PaymentMode paymentMode = new PaymentMode();
		String query = "SELECT * FROM payment_mode WHERE paym_id="+id;
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			paymentMode.setId(rs.getString("paym_id"));
			paymentMode.setName(rs.getString("paym_name"));
			paymentMode.setDesc(rs.getString("paym_desc"));
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return paymentMode;
		}else {
			return null;
		}
	}
	
	public boolean insert(PaymentMode paymentMode) throws SQLException {
		String query = "INSERT INTO payment_mode(paym_name, paym_desc) VALUES('"+paymentMode.getName()+"','"+paymentMode.getDesc()+"')";
		return sqlClient.executeUpdate(query);
	}
	
	public boolean update(PaymentMode paymentMode) throws SQLException {
		String query = "UPDATE payment_mode SET paym_name='"+paymentMode.getName()+"' , paym_desc='"+paymentMode.getDesc()+"' WHERE paym_id="+paymentMode.getId();
		return sqlClient.executeUpdate(query);
	}

	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM payment_mode WHERE paym_id="+id;
		return sqlClient.executeUpdate(query);
	}
	
}
