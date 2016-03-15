<%@ page import="java.util.*" %>
<%@ page import="com.apu.obj.*" %>
<%@ page import="com.apu.method.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Employee</title>
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
		if (document.getElementById("street").value==""){
			alert("Please enter street");
			return false;
		}
		if (document.getElementById("city").value==""){
			alert("Please enter city");
			return false;
		}
		if (document.getElementById("country").value==""){
			alert("Please enter country");
			return false;
		}
		if (document.getElementById("postcode").value==""){
			alert("Please enter post code");
			return false;
		}
		if (document.getElementById("phone").value==""){
			alert("Please enter phone");
			return false;
		}
		if (document.getElementById("email").value==""){
			alert("Please enter email");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("fname").value="";
		document.getElementById("lname").value="";
		document.getElementById("street").value="";
		document.getElementById("city").value="";
		document.getElementById("country").value="";
		document.getElementById("postcode").value="";
		document.getElementById("phone").value="";
		document.getElementById("email").value="";
	}
	
</script>

<%

	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String fname = "";
	String lname = "";
	String street = "";
	String country = "";
	String city = "";
	String postcode = "";
	String phone = "";
	String email = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			Employee employee = new Employee();
			employee.setFname(request.getParameter("fname"));
			employee.setLname(request.getParameter("lname"));
			employee.setStreet(request.getParameter("street"));
			employee.setCountry(request.getParameter("country"));
			employee.setCity(request.getParameter("city"));
			employee.setPostCode(request.getParameter("postcode"));
			employee.setPhone(request.getParameter("phone"));
			employee.setEmail(request.getParameter("email"));
			
			if(actionMethod.saveEmployee(employee)){
				reqStatus = "Added successfully!";
			}else{
				reqStatus = "Fail to add!";
			}
		}
	
		if (request.getParameter("btnEdit") !=null ) {	// if edit request
			Employee employee = new Employee();
			employee.setId(request.getParameter("id"));
			employee.setFname(request.getParameter("fname"));
			employee.setLname(request.getParameter("lname"));
			employee.setStreet(request.getParameter("street"));
			employee.setCountry(request.getParameter("country"));
			employee.setCity(request.getParameter("city"));
			employee.setPostCode(request.getParameter("postcode"));
			employee.setPhone(request.getParameter("phone"));
			employee.setEmail(request.getParameter("email"));
			
			if(actionMethod.updateEmployee(employee)){
				reqStatus = "Edit successfully!";
			}else{
				reqStatus = "Fail to edit!";
			}
		}
		
	}else {	//GET request
	
		if(request.getParameter("action") != null) {
			
			action = request.getParameter("action");
			
			//delete
			if(action.equalsIgnoreCase("delete")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					if (actionMethod.removeEmployee(Integer.parseInt(request.getParameter("id")))){
						reqStatus = "Removed successfully!";
					}else{
						reqStatus = "Unable to remove!";
					}
				}
			}
			
			//edit
			if(action.equalsIgnoreCase("edit")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					Employee objEmployee = actionMethod.fetchEmployee(Integer.parseInt(request.getParameter("id")));
					id = request.getParameter("id");
					fname = objEmployee.getFname();
					lname = objEmployee.getLname();
					street = objEmployee.getStreet();
					city = objEmployee.getCity();
					country = objEmployee.getCountry();
					postcode = objEmployee.getPostCode();
					phone = objEmployee.getPhone();
					email = objEmployee.getEmail();
				}		
			}
		}
	}
%>

<h2>Employee - Add/Edit/View</h2>
<hr>

<form method="post" action="" id="frmAddEmployee" onsubmit="return validateForm();">
	<table>
		<%
		if(action.equalsIgnoreCase("edit")) {	//if edit then display id
			%>
			<tr>
				<td>Id</td>
				<td><input type="text" name="id" id="id" value="<%= id %>" disabled="disabled" /></td>
			</tr>		
			<%
		}else{	// if nor edit (add) then hide id
			%>
			<tr>
				<td></td>
				<td><input type="hidden" name="id" id="id" value="<%= id %>" /></td>
			</tr>		
			<%
		}
		%>
		<tr>
			<td>First Name</td>
			<td><input type="text" name="fname" id="fname" value="<%= fname %>" /></td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><input type="text" name="lname" id="lname" value="<%= lname %>" /></td>
		</tr>
		<tr>
			<td>Street</td>
			<td><input type="text" name="street" id="street" value="<%= street %>" /></td>
		</tr>
		<tr>
			<td>City</td>
			<td><input type="text" name="city" id="city" value="<%= city %>" /></td>
		</tr>
		<tr>
			<td>Country</td>
			<td><input type="text" name="country" id="country" value="<%= country %>" /></td>
		</tr>
		<tr>
			<td>Post Code</td>
			<td><input type="text" name="postcode" id="postcode" value="<%= postcode %>" /></td>
		</tr>
		<tr>
			<td>Phone</td>
			<td><input type="text" name="phone" id="phone" value="<%= phone %>" /></td>
		</tr>
		<tr>
			<td>Email</td>
			<td><input type="text" name="email" id="email" value="<%= email %>" /></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input type="button" value="Clear" name="btnClear" onclick="clearForm()"/>
				<%
					if(action.equalsIgnoreCase("edit")) {
						%>
						<input type="submit" value="Edit" name="btnEdit" />
						<a href="?action=new">New</a>
						<%
					}else {
						%>
						<input type="submit" value="Add" name="btnAdd" />		
						<%
					}
				%>
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

<table border="1">
	<tr>
		<th>Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Street</th>
		<th>City</th>
		<th>Country</th>
		<th>Post code</th>
		<th>Phone</th>
		<th>Email</th>
		<th></th>
	</tr>
	<%
		ArrayList<Employee> objEmployeeList = actionMethod.fetchAllEmployee();
		if (objEmployeeList!=null) {
			for (Employee objEmployee : objEmployeeList) {
				%>
					<tr>
						<td><%= objEmployee.getId() %></td>
						<td><%= objEmployee.getFname() %></td>
						<td><%= objEmployee.getLname() %></td>
						<td><%= objEmployee.getStreet() %></td>
						<td><%= objEmployee.getCity() %></td>
						<td><%= objEmployee.getCountry() %></td>
						<td><%= objEmployee.getPostCode() %></td>
						<td><%= objEmployee.getPhone() %></td>
						<td><%= objEmployee.getEmail() %></td>
						<td>
							<a href="?action=delete&id=<%= objEmployee.getId() %>">delete</a>
							<a href="?action=edit&id=<%= objEmployee.getId() %>">edit</a>
						</td>
					</tr>
				<%
			}	
		}
	%>
</table>

</body>
</html>