<%@page import="java.text.SimpleDateFormat"%>
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

<script type="text/javascript">

	function validateForm() {
		if (document.getElementById("fname").value==""){
			alert("Please enter first name");
			return false;
		}
		if (document.getElementById("lname").value==""){
			alert("Please enter last name");
			return false;
		}
		if (document.getElementById("username").value==""){
			alert("Please enter username");
			return false;
		}
		if (document.getElementById("password").value==""){
			alert("Please enter password");
			return false;
		}
		if (document.getElementById("repassword").value==""){
			alert("Please enter repassword");
			return false;
		}
		if (document.getElementById("email").value==""){
			alert("Please enter email");
			return false;
		}
		if (document.getElementById("dob").value==""){
			alert("Please enter date of birth");
			return false;
		}
		if (document.getElementById("repassword").value!=document.getElementById("password").value){
			alert("Password doesn't match!");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("fname").value="";
		document.getElementById("lname").value="";
		document.getElementById("username").value="";
		document.getElementById("password").value="";
		document.getElementById("repassword").value="";
		document.getElementById("email").value="";
		document.getElementById("dob").value="";
	}
</script>

<%

	Config appConfig = new Config();
	SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

	ActionMethod actionMethod = new ActionMethod();
	String error = "";
	
	if (request.getMethod().equalsIgnoreCase("POST") || request.getParameter("btnSignup")!=null) {
		User objUser = new User();
		objUser.setFname(request.getParameter("fname"));
		objUser.setLname(request.getParameter("lname"));
		objUser.setUsername(request.getParameter("username"));
		objUser.setPassword(request.getParameter("password"));
		objUser.setRole("member");
		objUser.setEmail(request.getParameter("email"));
		objUser.setDob(myFormat.parse(request.getParameter("dob")));
		objUser.setGender(request.getParameter("gender"));
		
		if(actionMethod.saveUser(objUser)) {
			error = "SignUp success!  <a href='login.jsp'><b>Login</b></a>";	
		}else{
			error = "Login fail!";
		}
	}
%>

<div align="center">
	<h2>Hotel System</h2>
	<hr>
	<form method="post" action="/HotelSystem/jsp/auth/signup.jsp" onsubmit="return validateForm();">
		<table>
			<tr>
				<td>First Name </td>
				<td><input type="text" name="fname" id="fname" /></td>
			</tr>
			<tr>
				<td>Last Name </td>
				<td><input type="text" name="lname" id="lname" /></td>
			</tr>
			<tr>
				<td>Username </td>
				<td><input type="text" name="username" id="username" /></td>
			</tr>
			<tr>
				<td>Password </td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td>Re-Password </td>
				<td><input type="password" name="repassword" id="repassword" /></td>
			</tr>
			<tr>
				<td>Email </td>
				<td><input type="email" name="email" id="email" /></td>
			</tr>
			<tr>
				<td>Date of birth </td>
				<td><input type="date" name="dob" id="dob" /></td>
			</tr>
			<tr>
				<td>Gender </td>
				<td>
					<select name="gender" id="gender">
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td>
					<input type="button" value="Clear" name="btnClear" onclick="clearForm()"/>
					<input type="submit" name="btnSignup" value="Signup" />
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