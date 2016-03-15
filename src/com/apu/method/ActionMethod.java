package com.apu.method;

import java.util.ArrayList;

import com.apu.db.PaymentDB;
import com.apu.db.PaymentModeDB;
import com.apu.db.ReservationDB;
import com.apu.db.UserDB;
import com.apu.obj.*;
import com.apu.xml.*;

public class ActionMethod {

	// Hotel Methods
	
	public boolean saveHotel(Hotel hotel){
		try{
			HotelXML persistXml = new HotelXML();
			return persistXml.add(hotel);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Hotel> fetchAllHotel() {
		try{
			HotelXML persistXml = new HotelXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Hotel fetchHotel(int id) {
		try{
			HotelXML persistXml = new HotelXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateHotel(Hotel hotel) {
		try{
			HotelXML persistXml = new HotelXML();
			return persistXml.edit(hotel);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeHotel(int id) {
		try{
			HotelXML persistXML = new HotelXML();
			return persistXML.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	// End Hotel Methods
	
	// Employee Methods
	
	public boolean saveEmployee(Employee employee){
		try{
			EmployeeXML persistXml = new EmployeeXML();
			return persistXml.add(employee);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Employee> fetchAllEmployee() {
		try{
			EmployeeXML persistXml = new EmployeeXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Employee fetchEmployee(int id) {
		try{
			EmployeeXML persistXml = new EmployeeXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateEmployee(Employee employee) {
		try{
			EmployeeXML persistXml = new EmployeeXML();
			return persistXml.edit(employee);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeEmployee(int id) {
		try{
			EmployeeXML persistXml = new EmployeeXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End Employee Methods
	
	// Room Methods
	
	public boolean saveRoom(Room room){
		try{
			RoomXML persistXml = new RoomXML();
			return persistXml.add(room);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Room> fetchAllRoom() {
		try{
			RoomXML persistXml = new RoomXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Room fetchRoom(int id) {
		try{
			RoomXML persistXml = new RoomXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Room> fetchRoomByHotelId(int hotelId) {
		try{
			RoomXML persistXml = new RoomXML();
			return persistXml.getByHotelId(hotelId);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateRoom(Room room) {
		try{
			RoomXML persistXml = new RoomXML();
			return persistXml.edit(room);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeRoom(int id) {
		try{
			RoomXML persistXml = new RoomXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End Room Methods
	
	// Policy Methods
	
	public boolean savePolicy(Policy policy){
		try{
			PolicyXML persistXml = new PolicyXML();
			return persistXml.add(policy);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Policy> fetchAllPolicy() {
		try{
			PolicyXML persistXml = new PolicyXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Policy fetchPolicy(int id) {
		try{
			PolicyXML persistXml = new PolicyXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updatePolicy(Policy policy) {
		try{
			PolicyXML persistXml = new PolicyXML();
			return persistXml.edit(policy);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removePolicy(int id) {
		try{
			PolicyXML persistXml = new PolicyXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End Policy Methods
	
	// Facility Methods
	
	public boolean saveFacility(Facility facility){
		try{
			FacilityXML persistXml = new FacilityXML();
			return persistXml.add(facility);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Facility> fetchAllFacility() {
		try{
			FacilityXML persistXml = new FacilityXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Facility fetchFacility(int id) {
		try{
			FacilityXML persistXml = new FacilityXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateFacility(Facility facility) {
		try{
			FacilityXML persistXml = new FacilityXML();
			return persistXml.edit(facility);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeFacility(int id) {
		try{
			FacilityXML persistXml = new FacilityXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End Facility Methods
	
	// Food Methods
	
	public boolean saveFood(Food food){
		try{
			FoodXML persistXml = new FoodXML();
			return persistXml.add(food);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Food> fetchAllFood() {
		try{
			FoodXML persistXml = new FoodXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Food fetchFood(int id) {
		try{
			FoodXML persistXml = new FoodXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateFood(Food food) {
		try{
			FoodXML persistXml = new FoodXML();
			return persistXml.edit(food);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeFood(int id) {
		try{
			FoodXML persistXml = new FoodXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End Food Methods
	
	// HotelPolicy Methods
	
	public boolean saveHotelPolicy(HotelPolicy hotelPolicy){
		try{
			HotelPolicyXML persistXml = new HotelPolicyXML();
			return persistXml.add(hotelPolicy);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<HotelPolicy> fetchAllHotelPolicies() {
		try{
			HotelPolicyXML persistXml = new HotelPolicyXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<HotelPolicy> fetchHotelPoliciesByHotelId(String hotelId) {
		try{
			HotelPolicyXML persistXml = new HotelPolicyXML();
			return persistXml.getByHotelId(hotelId);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public HotelPolicy fetchHotelPolicy(int id) {
		try{
			HotelPolicyXML persistXml = new HotelPolicyXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateHotelPolicy(HotelPolicy hotelPolicy) {
		try{
			HotelPolicyXML persistXml = new HotelPolicyXML();
			return persistXml.edit(hotelPolicy);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeHotelPolicy(int id) {
		try{
			HotelPolicyXML persistXml = new HotelPolicyXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End HotelPolicy Methods
	
	// HotelFood Methods
	
	public boolean saveHotelFood(HotelFood hotelFood){
		try{
			HotelFoodXML persistXml = new HotelFoodXML();
			return persistXml.add(hotelFood);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<HotelFood> fetchAllHotelFood() {
		try{
			HotelFoodXML persistXml = new HotelFoodXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<HotelFood> fetchHotelFoodByHotelId(String hotelId) {
		try{
			HotelFoodXML persistXml = new HotelFoodXML();
			return persistXml.getByHotelId(hotelId);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public HotelFood fetchHotelFood(int id) {
		try{
			HotelFoodXML persistXml = new HotelFoodXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateHotelFood(HotelFood hotelFood) {
		try{
			HotelFoodXML persistXml = new HotelFoodXML();
			return persistXml.edit(hotelFood);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeHotelFood(int id) {
		try{
			HotelFoodXML persistXml = new HotelFoodXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End HotelFood Methods
	
	// HotelFacility Methods
	
	public boolean saveHotelFacility(HotelFacility hotelFacility){
		try{
			HotelFacilityXML persistXml = new HotelFacilityXML();
			return persistXml.add(hotelFacility);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<HotelFacility> fetchAllHotelFacility() {
		try{
			HotelFacilityXML persistXml = new HotelFacilityXML();
			return persistXml.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<HotelFacility> fetchHotelFacilityByHotelId(String hotelId) {
		try{
			HotelFacilityXML persistXml = new HotelFacilityXML();
			return persistXml.getByHotelId(hotelId);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public HotelFacility fetchHotelFacility(int id) {
		try{
			HotelFacilityXML persistXml = new HotelFacilityXML();
			return persistXml.get(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateHotelFacility(HotelFacility hotelFacility) {
		try{
			HotelFacilityXML persistXml = new HotelFacilityXML();
			return persistXml.edit(hotelFacility);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeHotelFacility(int id) {
		try{
			HotelFacilityXML persistXml = new HotelFacilityXML();
			return persistXml.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End HotelFacility Methods
	
	// User Methods
	
	public boolean saveUser(User user){
		try{
			UserDB userDB = new UserDB();
			return userDB.insert(user);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<User> fetchAllUser() {
		try{
			UserDB userDB = new UserDB();
			return userDB.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public User fetchUser(int id) {
		try{
			UserDB userDB = new UserDB();
			return userDB.getById(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public User fetchUserByUsernamePassword(String username, String password) {
		try{
			UserDB userDB = new UserDB();
			return userDB.getByUsernamePassword(username, password);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateUser(User payment) {
		try{
			UserDB paymentDB = new UserDB();
			return paymentDB.update(payment);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeUser(int id) {
		try{
			UserDB userDB = new UserDB();
			return userDB.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End User Methods
	
	// Payment Methods
	
	public int savePayment(Payment payment){
		try{
			PaymentDB paymentDB = new PaymentDB();
			return paymentDB.insert(payment);
		}catch(Exception ex){
			ex.printStackTrace();
			return 0;
		}
	}
	
	public ArrayList<Payment> fetchAllPayment() {
		try{
			PaymentDB paymentDB = new PaymentDB();
			return paymentDB.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Payment fetchPayment(int id) {
		try{
			PaymentDB paymentDB = new PaymentDB();
			return paymentDB.getById(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updatePayment(Payment payment) {
		try{
			PaymentDB paymentDB = new PaymentDB();
			return paymentDB.update(payment);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removePayment(int id) {
		try{
			PaymentDB paymentDB = new PaymentDB();
			return paymentDB.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End Payment Methods
	
	// PaymentMode Methods
	
	public boolean savePaymentMode(PaymentMode paymentMode){
		try{
			PaymentModeDB paymentModeDB = new PaymentModeDB();
			return paymentModeDB.insert(paymentMode);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<PaymentMode> fetchAllPaymentMode() {
		try{
			PaymentModeDB paymentModeDB = new PaymentModeDB();
			return paymentModeDB.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public PaymentMode fetchPaymentMode(int id) {
		try{
			PaymentModeDB paymentModeDB = new PaymentModeDB();
			return paymentModeDB.getById(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updatePaymentMode(PaymentMode paymentMode) {
		try{
			PaymentModeDB paymentModeDB = new PaymentModeDB();
			return paymentModeDB.update(paymentMode);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removePaymentMode(int id) {
		try{
			PaymentModeDB paymentModeDB = new PaymentModeDB();
			return paymentModeDB.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End PaymentMode Methods
	
	// Reservation Methods
	
	public boolean saveReservation(Reservation reservation){
		try{
			ReservationDB reservationDB = new ReservationDB();
			return reservationDB.insert(reservation);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Reservation> fetchAllReservation() {
		try{
			ReservationDB reservationDB = new ReservationDB();
			return reservationDB.getAll();
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Reservation fetchReservation(int id) {
		try{
			ReservationDB reservationDB = new ReservationDB();
			return reservationDB.getById(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Reservation> fetchReservationByUserId(int userId) {
		try{
			ReservationDB reservationDB = new ReservationDB();
			return reservationDB.getByUserId(userId);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Reservation> fetchReservationFutureByUserId(int userId) {
		try{
			ReservationDB reservationDB = new ReservationDB();
			return reservationDB.getAllFutureByUserId(userId);
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean updateReservation(Reservation reservation) {
		try{
			ReservationDB reservationDB = new ReservationDB();
			return reservationDB.update(reservation);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
		
	public boolean removeReservation(int id) {
		try{
			ReservationDB reservationDB = new ReservationDB();
			return reservationDB.delete(id);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}
	
	// End Reservation Methods
	
}
