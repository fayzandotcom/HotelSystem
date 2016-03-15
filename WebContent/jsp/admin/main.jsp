<%@page import="com.apu.util.Config"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
</head>
<body>

	<%
		Config appConfig = new Config();
	%>

<div align="center">
	<h2>Welcome to admin panel</h2>
	Hi, admin <br>
	<a href="<%= appConfig.getAppContext() %>">Frontend</a> | 
	<a href="<%= appConfig.getAppContext() %>/jsp/auth/logout.jsp">Logout</a>
	<hr>
	<table>
		<tr>
			<td><a href="hotel.jsp">Hotel</a> | </td>
			<td><a href="policy.jsp">Policy</a> | </td>
			<td><a href="food.jsp">Food</a> | </td>
			<td><a href="facility.jsp">Facility</a> | </td>
			<td><a href="room.jsp">Room</a> | </td>
			<td><a href="employee.jsp">Employee</a> | </td>
			<td><a href="paymentmode.jsp">Payment Mode</a></td>
		</tr>
	</table>
	<hr>
	<table>
		<tr>
			<td><a href="hotelpolicy.jsp">Hotel Policy</a> | </td>
			<td><a href="hotelfacility.jsp">Hotel Facility</a> | </td>
			<td><a href="hotelfood.jsp">Hotel Food</a></td>
		</tr>
	</table>
	<hr>
</div>

</body>
</html>