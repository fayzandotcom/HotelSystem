<%@page import="com.apu.util.Config"%>
<%@ page import="com.apu.obj.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Member Area</title>
</head>
<body>

	<%
		Config appConfig = new Config();
		User objUser = (User) session.getAttribute("objUser");
	%>

	<div align="center">
		<h2>Welcome to member area</h2>
		Hi, <%= objUser.getFname() %> <br>
		<a href="<%= appConfig.getAppContext() %>/jsp/auth/logout.jsp">Logout</a>
		<hr>
		
	</div>

</body>
</html>