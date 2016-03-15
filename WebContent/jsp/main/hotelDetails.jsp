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
<title>Hotel Details</title>
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
			
			<h3>Food Details</h3>
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Description</th>
				</tr>
				<%
					
					ArrayList<HotelFood> objHotelFoodList = actionMethod.fetchHotelFoodByHotelId(hotelId);
					for(HotelFood objHotelFood: objHotelFoodList) {
						Food objFood = actionMethod.fetchFood(Integer.parseInt(objHotelFood.getFoodId()));
						%>
							<tr>
								<td><%= objFood.getName() %></td>
								<td><%= objFood.getDesc() %></td>
							</tr>
						<%
					}
					
				%>
			</table>
			
			<h3>Facility Details</h3>
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Description</th>
				</tr>
				<%
					
					ArrayList<HotelFacility> objHotelFacilitiesList = actionMethod.fetchHotelFacilityByHotelId(hotelId);
					for(HotelFacility objHotelFacility: objHotelFacilitiesList) {
						Facility objFacility = actionMethod.fetchFacility(Integer.parseInt(objHotelFacility.getFacilityId()));
						%>
							<tr>
								<td><%= objFacility.getName() %></td>
								<td><%= objFacility.getDesc() %></td>
							</tr>
						<%
					}
					
				%>
			</table>
			
			<h3>Policy Details</h3>
			<table border="1">
				<tr>
					<th>Name</th>
					<th>Description</th>
				</tr>
				<%
					
					ArrayList<HotelPolicy> objHotelPolicyList = actionMethod.fetchHotelPoliciesByHotelId(hotelId);
					for(HotelPolicy objHotelPolicy: objHotelPolicyList) {
						Policy objPolicy = actionMethod.fetchPolicy(Integer.parseInt(objHotelPolicy.getPolicyId()));
						%>
							<tr>
								<td><%= objPolicy.getName() %></td>
								<td><%= objPolicy.getDesc() %></td>
							</tr>
						<%
					}
					
				%>
			</table>
	</div>
	
</body>
</html>