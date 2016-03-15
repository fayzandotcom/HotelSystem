<%@page import="com.apu.util.Config"%>
<%@page import="com.apu.obj.User"%>
<%@page import="com.apu.method.ActionMethod"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

<%

	Config appConfig = new Config();

	ActionMethod actionMethod = new ActionMethod();
	String error = "";
	
	if (request.getMethod().equalsIgnoreCase("POST") || request.getParameter("btnLogin")!=null) {
		User objUser = new User();
		objUser = null;
		objUser = actionMethod.fetchUserByUsernamePassword(request.getParameter("username"), request.getParameter("password"));
		if(objUser!=null) {
			session.setAttribute("objUser", objUser);
			System.out.println("Login success");
			if(objUser.getRole().equalsIgnoreCase("admin")) {
				System.out.println("going to admin main page");
				response.sendRedirect(appConfig.getAppContext() + "/jsp/admin/main.jsp");	
			}else {
				System.out.println("going to member main page");
				response.sendRedirect(appConfig.getAppContext() + "/jsp/main/index.jsp");
			}
		}else{
			error = "Login fail!";
		}
	}
%>

<div align="center">
	<h2>Hotel System</h2>
	<hr>
	<form method="post" action="/HotelSystem/jsp/auth/login.jsp">
		<table>
			<tr>
				<td>Username </td>
				<td><input type="text" name="username" id="username" /></td>
			</tr>
			<tr>
				<td>Password </td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="submit" name="btnLogin" value="Login" />
					<a href="signup.jsp"><b>SignUp</b></a>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><%= error %></td>
			</tr>
		</table>
	</form>
	<hr>
</div>

</body>
</html>