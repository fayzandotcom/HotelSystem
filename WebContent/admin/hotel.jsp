<%@ page import="java.util.*" %>
<%@ page import="com.apu.obj.*" %>
<%@ page import="com.apu.method.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Hotel</title>
</head>
<body>

<script type="text/javascript">

	function validateForm() {
		if (document.getElementById("name").value==""){
			alert("Please enter name");
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
		if (document.getElementById("status").value==""){
			alert("Please enter status");
			return false;
		}
		if (document.getElementById("phone").value==""){
			alert("Please enter phone");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("name").value="";
		document.getElementById("street").value="";
		document.getElementById("city").value="";
		document.getElementById("country").value="";
		document.getElementById("postcode").value="";
		document.getElementById("status").value="";
		document.getElementById("phone").value="";
	}
	
</script>

<%

	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String name = "";
	String street = "";
	String country = "";
	String city = "";
	String postcode = "";
	String status = "";
	String phone = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			Hotel hotel = new Hotel();
			hotel.setName(request.getParameter("name"));
			hotel.setStreet(request.getParameter("street"));
			hotel.setCountry(request.getParameter("country"));
			hotel.setCity(request.getParameter("city"));
			hotel.setPostCode(request.getParameter("postcode"));
			hotel.setStatus(Integer.parseInt(request.getParameter("status")));
			hotel.setPhone(request.getParameter("phone"));
			
			if(actionMethod.saveHotel(hotel)){
				reqStatus = "Added successfully!";
			}else{
				reqStatus = "Fail to add!";
			}
		}
	
		if (request.getParameter("btnEdit") !=null ) {	// if edit request
			Hotel hotel = new Hotel();
			hotel.setId(request.getParameter("id"));
			hotel.setName(request.getParameter("name"));
			hotel.setStreet(request.getParameter("street"));
			hotel.setCountry(request.getParameter("country"));
			hotel.setCity(request.getParameter("city"));
			hotel.setPostCode(request.getParameter("postcode"));
			hotel.setStatus(Integer.parseInt(request.getParameter("status")));
			hotel.setPhone(request.getParameter("phone"));
			
			if(actionMethod.updateHotel(hotel)){
				reqStatus = "Edit successfully!";
			}else{
				reqStatus = "Fail to edit!";
			}
		}
	}else { //GET request
	
		if(request.getParameter("action") != null) {
			
			action = request.getParameter("action");
			
			//delete
			if(action.equalsIgnoreCase("delete")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					if (actionMethod.removeHotel(Integer.parseInt(request.getParameter("id")))){
						reqStatus = "Removed successfully!";
					}else{
						reqStatus = "Unable to remove!";
					}
				}
			}
			
			//edit
			if(action.equalsIgnoreCase("edit")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					Hotel objHotel = actionMethod.fetchHotel(Integer.parseInt(request.getParameter("id")));
					id = request.getParameter("id");
					name = objHotel.getName();
					street = objHotel.getStreet();
					city = objHotel.getCity();
					country = objHotel.getCountry();
					postcode = objHotel.getPostCode();
					status = String.valueOf(objHotel.getStatus());
					phone = objHotel.getPhone();
				}		
			}
		}
	}
%>

<h2>Hotel - Add/Edit/View</h2>
<hr>

<form method="post" action="" id="frmAddHotel" onsubmit="return validateForm();">
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
			<td>Name</td>
			<td><input type="text" name="name" id="name" value="<%= name %>" /></td>
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
			<td>Star</td>
			<td><select name="status" id="status" >
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select></td>
		</tr>
		<tr>
			<td>Phone</td>
			<td><input type="text" name="phone" id="phone" value="<%= phone %>" /></td>
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
		<th>Name</th>
		<th>Street</th>
		<th>City</th>
		<th>Country</th>
		<th>Post code</th>
		<th>Star</th>
		<th>Phone</th>
		<th></th>
	</tr>
	<%
		ArrayList<Hotel> objHotelList = actionMethod.fetchAllHotel();
		if (objHotelList!=null) {
			for (Hotel objHotel : objHotelList) {
				%>
					<tr>
						<td><%= objHotel.getId() %></td>
						<td><%= objHotel.getName() %></td>
						<td><%= objHotel.getStreet() %></td>
						<td><%= objHotel.getCity() %></td>
						<td><%= objHotel.getCountry() %></td>
						<td><%= objHotel.getPostCode() %></td>
						<td><%= objHotel.getStatus() %></td>
						<td><%= objHotel.getPhone() %></td>
						<td>
							<a href="?action=delete&id=<%= objHotel.getId() %>">delete</a>
							<a href="?action=edit&id=<%= objHotel.getId() %>">edit</a>
						</td>
					</tr>
				<%
			}	
		}
	%>
</table>

</body>
</html>