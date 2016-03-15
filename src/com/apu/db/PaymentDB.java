package com.apu.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.apu.obj.Payment;

public class PaymentDB {
	
	private SqlClient sqlClient;
	
	public PaymentDB() throws ClassNotFoundException, SQLException {
		this.sqlClient = new SqlClient();
	}
	
	public ArrayList<Payment> getAll() throws SQLException {
		ArrayList<Payment> paymentList = new ArrayList<Payment>();
		String query = "SELECT * FROM payment";
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			
			Payment payment = new Payment();
			payment.setId(rs.getString("pay_id"));
			payment.setModeId(rs.getString("pay_mode_id"));
			payment.setAmount(rs.getDouble("pay_amount"));
			
			paymentList.add(payment);
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return paymentList;
		}else {
			return null;
		}
	}
	
	public Payment getById(int id) throws SQLException {
		Payment payment = new Payment();
		String query = "SELECT * FROM payment WHERE pay_id="+id;
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			payment.setId(rs.getString("pay_id"));
			payment.setModeId(rs.getString("pay_mode_id"));
			payment.setAmount(rs.getDouble("pay_amount"));
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return payment;
		}else {
			return null;
		}
	}
	
	public int insert(Payment payment) throws SQLException {
		String query = "INSERT INTO payment(pay_mode_id, pay_amount) VALUES("+payment.getModeId()+","+payment.getAmount()+")";
		return sqlClient.executeGetKey(query);
	}
	
	public boolean update(Payment payment) throws SQLException {
		String query = "UPDATE payment SET pay_mode_id="+payment.getModeId()+" , pay_amount="+payment.getAmount()+" WHERE pay_id="+payment.getId();
		return sqlClient.executeUpdate(query);
	}

	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM payment WHERE pay_id="+id;
		return sqlClient.executeUpdate(query);
	}
	
}
