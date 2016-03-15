package com.apu.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.apu.obj.Reservation;

public class ReservationDB {
	
	private SqlClient sqlClient;
	private SimpleDateFormat myFormat;
	
	public ReservationDB() throws ClassNotFoundException, SQLException {
		this.sqlClient = new SqlClient();
		 myFormat = new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public ArrayList<Reservation> getAll() throws SQLException {
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		String query = "SELECT * FROM reservation";
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			
			Reservation reservation = new Reservation();
			reservation.setId(rs.getString("res_id"));
			reservation.setUserId(rs.getString("res_u_id"));
			reservation.setRoomId(rs.getString("res_room_id"));
			reservation.setStartDate(rs.getDate("res_start_date"));
			reservation.setEndDate(rs.getDate("res_end_date"));
			reservation.setPayId(rs.getString("res_pay_id"));
			
			reservationList.add(reservation);
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return reservationList;
		}else {
			return null;
		}
	}
	
	public Reservation getById(int id) throws SQLException {
		Reservation reservation = new Reservation();
		String query = "SELECT * FROM reservation WHERE res_id="+id;
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			reservation.setId(rs.getString("res_id"));
			reservation.setUserId(rs.getString("res_u_id"));
			reservation.setRoomId(rs.getString("res_room_id"));
			reservation.setStartDate(rs.getDate("res_start_date"));
			reservation.setEndDate(rs.getDate("res_end_date"));
			reservation.setPayId(rs.getString("res_pay_id"));
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return reservation;
		}else {
			return null;
		}
	}
	
	public ArrayList<Reservation> getByUserId(int userId) throws SQLException {
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		String query = "SELECT * FROM reservation WHERE res_u_id="+userId;
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			
			Reservation reservation = new Reservation();
			reservation.setId(rs.getString("res_id"));
			reservation.setUserId(rs.getString("res_u_id"));
			reservation.setRoomId(rs.getString("res_room_id"));
			reservation.setStartDate(rs.getDate("res_start_date"));
			reservation.setEndDate(rs.getDate("res_end_date"));
			reservation.setPayId(rs.getString("res_pay_id"));
			
			reservationList.add(reservation);
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return reservationList;
		}else {
			return null;
		}
	}
	
	public ArrayList<Reservation> getAllFutureByUserId(int userId) throws SQLException {
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String query = "SELECT * FROM reservation WHERE res_start_date>='"+myFormat.format(new Date())+"' "
				+ "AND res_u_id="+userId;
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
			
			Reservation reservation = new Reservation();
			reservation.setId(rs.getString("res_id"));
			reservation.setUserId(rs.getString("res_u_id"));
			reservation.setRoomId(rs.getString("res_room_id"));
			reservation.setStartDate(rs.getDate("res_start_date"));
			reservation.setEndDate(rs.getDate("res_end_date"));
			reservation.setPayId(rs.getString("res_pay_id"));
			
			reservationList.add(reservation);
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return reservationList;
		}else {
			return null;
		}
	}
	
	public boolean insert(Reservation reservation) throws SQLException {
		if(isReserved(Integer.parseInt(reservation.getRoomId()), reservation.getStartDate(), reservation.getEndDate())) {
			return false;
		}
		String startDate = myFormat.format(reservation.getStartDate());
		String endDate = myFormat.format(reservation.getEndDate());
		
		String query = "INSERT INTO reservation(res_u_id, res_room_id, res_start_date, res_end_date, res_pay_id) "
				+ "VALUES("+reservation.getUserId()+","+reservation.getRoomId()+",'"+startDate+"',"
						+ "'"+endDate+"',"+reservation.getPayId()+")";
		return sqlClient.executeUpdate(query);
	}
	
	public boolean update(Reservation reservation) throws SQLException {
		String query = "UPDATE reservation SET res_u_id="+reservation.getUserId()+", res_room_id="+reservation.getRoomId()+", "
						+ "res_start_date='"+myFormat.format(reservation.getStartDate())+"', res_end_date='"+myFormat.format(reservation.getEndDate())+"', "
						+ "res_pay_id="+reservation.getPayId()+" "
						+ "WHERE res_id="+reservation.getId();
		return sqlClient.executeUpdate(query);
	}

	public boolean delete(int id) throws SQLException {
		String query = "DELETE FROM reservation WHERE res_id="+id;
		return sqlClient.executeUpdate(query);
	}
	
	public boolean isReserved(int roomId, Date startDate, Date endDate) throws SQLException {
		String query = "SELECT * FROM reservation WHERE "
				+ "(res_start_date < '"+myFormat.format(startDate)+"' AND res_end_date > '"+myFormat.format(endDate)+"') OR "
				+ "(res_start_date >= '"+myFormat.format(startDate)+"' AND res_start_date <= '"+myFormat.format(endDate)+"') OR "
				+ "(res_end_date >= '"+myFormat.format(startDate)+"' AND res_end_date <= '"+myFormat.format(endDate)+"')";
		ResultSet rs = sqlClient.executeQuery(query);
		int rowNum = 0;
		while(rs.next()) {
			rowNum++;
		}
		rs.close();
		sqlClient.close();
		if(rowNum>0) {
			return true;
		}else {
			return false;
		}
	}	
	
}
