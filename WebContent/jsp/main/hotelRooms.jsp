<%@page import="java.util.ArrayList"%>
<%@page import="com.apu.method.ActionMethod"%>
<%@page import="com.apu.util.Config"%>
<%@page import="com.apu.obj.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Rooms</title>
</head>
<body>

	<%
		Config appConfig = new Config();
		User objUser = (User) session.getAttribute("objUser");
	%>
	
	<div align="center">
		<h2>Hotel Details</h2>
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
		
			String hotelId = "";
			if(request.getParameter("hotelId")!=null) {
				hotelId = request.getParameter("hotelId");
			}
			if(hotelId.equals("")) {
				return;
			}
		
			ActionMethod actionMethod = new ActionMethod();
			Hotel objHotel = actionMethod.fetchHotel(Integer.parseInt(hotelId));
			
		%>
			<p>
				Hotel Name: <%= objHotel.getName() %> <br>
				Location: <%= objHotel.getStreet() %>, <%= objHotel.getCity() %>, <%= objHotel.getCountry() %> <br>
				Post code: <%= objHotel.getPostCode() %> <br>
				Phone #: <%= objHotel.getPhone() %> <br>
			</p>
			
			<h3>Hotel Rooms</h3>
			<table border="1">
				<tr>
					<th>Room no.</th>
					<th>Category</th>
					<th>Size (sq ft)</th>
					<th>Persons</th>
					<th>Floor</th>
					<th>Price</th>
					<td></td>
				</tr>
				<%
					
					ArrayList<Room> objRoomList = actionMethod.fetchRoomByHotelId(Integer.parseInt(hotelId));
					for(Room objRoom: objRoomList) {
						%>
							<tr>
								<td><%= objRoom.getRoomNo() %></td>
								<td><%= objRoom.getCategory() %></td>
								<td><%= objRoom.getSize() %></td>
								<td><%= objRoom.getPersons() %></td>
								<td><%= objRoom.getFloor() %></td>
								<td><%= objRoom.getPrice() %></td>
								<td>
									<a href="<%= appConfig.getAppContext() + "/jsp/member/reservation.jsp?roomId=" + objRoom.getId()%>">reserve</a>
								</td>
							</tr>
						<%
					}
					
				%>
			</table>
			
	</div>
	
</body>
</html>