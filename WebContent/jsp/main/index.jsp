<%@page import="java.util.ArrayList"%>
<%@page import="com.apu.method.ActionMethod"%>
<%@page import="com.apu.util.Config"%>
<%@page import="com.apu.obj.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Hotel System</title>
</head>
<body>

	<%
		Config appConfig = new Config();
		User objUser = (User) session.getAttribute("objUser");
	%>
	
	<div align="center">
		<h2>Welcome to System</h2>
		<%
			if(objUser!=null) {
				%>
					Hi, <%= objUser.getFname() %> <br>
					<a href="<%= appConfig.getAppContext() %>/jsp/admin/main.jsp">Admin</a> |  
					<a href="<%= appConfig.getAppContext() %>/jsp/auth/logout.jsp">Logout</a>
				<%
			}else {
				%> 
					<a href="<%= appConfig.getAppContext() %>/jsp/auth/login.jsp">Login</a>
				<%
			}
		%>
		
		<hr>
		<p>Available Hotels</p>
		<table border="1">
			<tr>
				<th>Name</th>
				<th>Location</th>
				<th>Post code</th>
				<th>Phone#</th>
				<th></th>
			</tr>
			<%
				ActionMethod actionMethod = new ActionMethod();
				ArrayList<Hotel> objHotelList = actionMethod.fetchAllHotel();
				for (Hotel objHotel: objHotelList) {
					%>
						<tr>
							<td><%= objHotel.getName() %></td>
							<td><%= objHotel.getStreet() +", "+ objHotel.getCity() +", "+ objHotel.getCountry() %></td>
							<td><%= objHotel.getPostCode() %></td>
							<td><%= objHotel.getPhone() %></td>
							<td>
								<a href="<%= appConfig.getAppContext() + "/jsp/main/hotelDetails.jsp?hotelId=" + objHotel.getId()%>">details</a>
								<a href="<%= appConfig.getAppContext() + "/jsp/main/hotelRooms.jsp?hotelId=" + objHotel.getId()%>">rooms</a>
							</td>
						</tr>
					<%
				}
			%>
		</table>
	</div>
	
</body>
</html>