<%@ page import="java.util.*" %>
<%@ page import="com.apu.obj.*" %>
<%@ page import="com.apu.method.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@page import="com.apu.util.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Hotel</title>
</head>
<body>

<script type="text/javascript">

	function validateForm() {
		if (document.getElementById("startdate").value==""){
			alert("Please enter from date");
			return false;
		}
		if (document.getElementById("enddate").value==""){
			alert("Please enter to date");
			return false;
		}
		if (document.getElementById("amount").value==""){
			alert("Please enter amount");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("startdate").value="";
		document.getElementById("enddate").value="";
		document.getElementById("amount").value="";
	}
	
	function calculate(amount) {
		var startDate = new Date(document.getElementById("startdate").value);
		var endDate = new Date(document.getElementById("enddate").value);
		var days = ((endDate-startDate)/(1000*60*60*24))+1;
		amount = amount * days;
		
		document.getElementById("days").value=days;
		document.getElementById("amount").value=amount;
	}
	
</script>

<%

	Config appConfig = new Config();
	User objUser = (User) session.getAttribute("objUser");

	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String u_id = "";
	String room_id = "";
	String startdate = "";
	String enddate = "";
	String amount = "";

	String reqStatus = "";
	String action = "";
	
	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnRes") !=null ) {	// if add request
			
			int payid = 0;
			
			Payment payment = new Payment();
			payment.setModeId(request.getParameter("payid"));
			payment.setAmount(Double.valueOf(request.getParameter("amount")));
			payid = actionMethod.savePayment(payment);
			
			if(payid>0) {	// successfully added payment
				
				Reservation res = new Reservation();
				res.setRoomId(request.getParameter("roomId"));
				res.setStartDate(myFormat.parse(request.getParameter("startdate")));
				res.setEndDate(myFormat.parse(request.getParameter("enddate")));
				res.setPayId(String.valueOf(payid));
				res.setUserId(objUser.getId());
				
				if(actionMethod.saveReservation(res)){
					reqStatus = "Reserve successfully!";
				}else{
					reqStatus = "Unable to reserve! <br>Room is unavaliable in these dates";
				}
				
			}else{
				reqStatus = "Unable to reserve! <br>Room is unavaliable in these dates";
			}
			
		}
	
	}else { //GET request
	
		if(request.getParameter("action") != null) {
			
			action = request.getParameter("action");
			
			//delete
			if(action.equalsIgnoreCase("delete")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					Reservation res = new Reservation();
					res = actionMethod.fetchReservation(Integer.parseInt(request.getParameter("id")));
					if(actionMethod.removePayment(Integer.parseInt(res.getPayId()))){
						if (actionMethod.removeReservation(Integer.parseInt(request.getParameter("id")))){
							reqStatus = "Removed successfully!";
						}else{
							reqStatus = "Unable to remove!";
						}
					}else{
						reqStatus = "Unable to remove!";
					}
				}
			}
		}
	}
%>

<div align="center">
<h2>Room Reserve</h2>
		<%
			if(objUser!=null) {
				%>
					Hi, <%= objUser.getFname() %> <br>
					<a href="<%= appConfig.getAppContext() %>">Home</a> | 
					<a href="<%= appConfig.getAppContext() %>/jsp/auth/logout.jsp">Logout</a>
				<%
			}else {
				%>
					<a href="<%= appConfig.getAppContext() %>">Home</a> | 
					<a href="<%= appConfig.getAppContext() %>/jsp/auth/login.jsp">Login</a>
				<%
			}
		%>
		
<hr>

		<%
		
			String roomId = "";
			if(request.getParameter("roomId")!=null) {
				roomId = request.getParameter("roomId");
			}
			if(roomId.equals("")) {
				return;
			}
		
			Room objRoom = actionMethod.fetchRoom(Integer.parseInt(roomId));
			
		%>
			<p>
				Room#: <%= objRoom.getRoomNo() %> ,
				Floor: <%= objRoom.getFloor() %> ,
				Category: <%= objRoom.getCategory() %> ,
				Size: <%= objRoom.getSize() %> sq ft ,
				Persons: <%= objRoom.getPersons() %> ,
				Price: <%= objRoom.getPrice() %> ,
			</p>

<form method="post" action="" id="frmResRoom" onsubmit="return validateForm();">
	<table>
		<tr>
			<td>From</td>
			<td><input type="date" name="startdate" id="startdate" value="<%= myFormat.format(new Date()) %>" /></td>
		</tr>
		<tr>
			<td>To</td>
			<td><input type="date" name="enddate" id="enddate" value="<%= myFormat.format(new Date()) %>" /></td>
		</tr>
		<tr>
			<td>Payment mode</td>
			<td>
				<select name="payid" id="payid">
					<%
						ArrayList<PaymentMode> objPaymentModeList = actionMethod.fetchAllPaymentMode();
						for(PaymentMode objPaymentMode: objPaymentModeList) {
							%>
								<option value="<%= objPaymentMode.getId() %>"><%= objPaymentMode.getName() %></option>
							<%
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				Days
			</td>
			<td>
				<input type="text" name="days" id="days" value="1" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<td>
				Amount
			</td>
			<td>
				<input type="text" name="amount" id="amount" value="<%= objRoom.getPrice() %>" readonly="readonly" />
				<button type="button" onclick="calculate(<%= objRoom.getPrice() %>)">calculate</button>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="button" value="Clear" name="btnClear" onclick="clearForm()"/>
				<input type="submit" value="Reserve" name="btnRes" />
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<%= reqStatus %>
			</td>
		</tr>
	</table>
</form>
<br>
<hr>
	<h4>My Reserved Rooms</h4>
	<table border="1">
		<tr>
			<th>Hotel Name</th>
			<th>Room no.</th>
			<th>From</th>
			<th>To</th>
			<th></th>
		</tr>
		<tr>
			<%
				// get all reservation list
				ArrayList<Reservation> objResList = actionMethod.fetchReservationFutureByUserId(Integer.parseInt(objUser.getId()));
				for(Reservation objRes: objResList) {
					String strHotelName = "";
					String strRoomNo = "";
					//get room
					objRoom = actionMethod.fetchRoom(Integer.parseInt(objRes.getRoomId()));
					strRoomNo = objRoom.getRoomNo();
					//get hotel
					Hotel objHotel = actionMethod.fetchHotel(Integer.parseInt(objRoom.getHotelId()));
					strHotelName = objHotel.getName();
					
					%>
						<tr>
							<td><%= strHotelName %></td>
							<td><%= strRoomNo %></td>
							<td><%= objRes.getStartDate() %></td>
							<td><%= objRes.getEndDate() %></td>
							<td>
								<a href="?roomId=<%= roomId %>&action=delete&id=<%= objRes.getId() %>">delete</a>
							</td>
						</tr>
					<%
				}
			%>
		</tr>
	</table>
</div>

</body>
</html>
