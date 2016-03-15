<%@ page import="java.util.*" %>
<%@ page import="com.apu.obj.*" %>
<%@ page import="com.apu.method.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Room</title>
</head>
<body>

<script type="text/javascript">

	function validateForm() {
		if (document.getElementById("floor").value==""){
			alert("Please enter floor");
			return false;
		}
		if (document.getElementById("size").value==""){
			alert("Please enter size");
			return false;
		}
		if (isNaN(document.getElementById("size").value)) {
			alert("Size value should be a number in square feet");
			return false;
		}
		if (document.getElementById("persons").value==""){
			alert("Please enter persons");
			return false;
		}
		if (isNaN(document.getElementById("persons").value)) {
			alert("Person value should be a number");
			return false;
		}
		if (document.getElementById("price").value==""){
			alert("Please enter price");
			return false;
		}
		if (isNaN(document.getElementById("price").value)) {
			alert("Price value should be a number in USD");
			return false;
		}
		if (document.getElementById("category").value==""){
			alert("Please enter category");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("floor").value="";
		document.getElementById("size").value="";
		document.getElementById("persons").value="";
		document.getElementById("price").value="";
		document.getElementById("category").value="";
	}
	
</script>

<%

	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String floor = "";
	String size = "";
	String persons = "";
	String price = "";
	String category = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			Room room = new Room();
			room.setFloor(request.getParameter("floor"));
			room.setSize(Double.valueOf(request.getParameter("size")));
			room.setPersons(Integer.parseInt(request.getParameter("persons")));
			room.setPrice(Double.valueOf(request.getParameter("price")));
			room.setCategory(request.getParameter("category"));
			
			if(actionMethod.saveRoom(room)){
				reqStatus = "Added successfully!";
			}else{
				reqStatus = "Fail to add!";
			}
		}
	
		if (request.getParameter("btnEdit") !=null ) {	// if edit request
			Room room = new Room();
			room.setId(request.getParameter("id"));
			room.setFloor(request.getParameter("floor"));
			room.setSize(Double.valueOf(request.getParameter("size")));
			room.setPersons(Integer.parseInt(request.getParameter("persons")));
			room.setPrice(Double.valueOf(request.getParameter("price")));
			room.setCategory(request.getParameter("category"));
			
			if(actionMethod.updateRoom(room)){
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
					if (actionMethod.removeRoom(Integer.parseInt(request.getParameter("id")))){
						reqStatus = "Removed successfully!";
					}else{
						reqStatus = "Unable to remove!";
					}
				}
			}
			
			//edit
			if(action.equalsIgnoreCase("edit")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					Room objRoom = actionMethod.fetchRoom(Integer.parseInt(request.getParameter("id")));
					id = request.getParameter("id");
					floor = objRoom.getFloor();
					size = String.valueOf(objRoom.getSize());
					persons = String.valueOf(objRoom.getPersons());
					price = String.valueOf(objRoom.getPrice());
					category = objRoom.getCategory();
				}		
			}
		}
	}
%>

<h2>Room - Add/Edit/View</h2>
<hr>

<form method="post" action="" id="frmAddRoom" onsubmit="return validateForm();">
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
			<td>Floor</td>
			<td><input type="text" name="floor" id="floor" value="<%= floor %>" /></td>
		</tr>
		<tr>
			<td>Size</td>
			<td><input type="text" name="size" id="size" value="<%= size %>" /></td>
		</tr>
		<tr>
			<td>Persons</td>
			<td><input type="text" name="persons" id="persons" value="<%= persons %>" /></td>
		</tr>
		<tr>
			<td>Price</td>
			<td><input type="text" name="price" id="price" value="<%= price %>" /></td>
		</tr>
		<tr>
			<td>Category</td>
			<td>
				<select name="category" id="category">
					<option value="Budget">Budget</option>
					<option value="Deluxe">Deluxe</option>
					<option value="Luxury">Luxury</option>
				</select>
			</td>
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
		<th>Floor</th>
		<th>Size</th>
		<th>Persons</th>
		<th>Price</th>
		<th>Category</th>
		<th></th>
	</tr>
	<%
		ArrayList<Room> objRoomList = actionMethod.fetchAllRoom();
		if (objRoomList!=null) {
			for (Room objRoom : objRoomList) {
				%>
					<tr>
						<td><%= objRoom.getId() %></td>
						<td><%= objRoom.getFloor() %></td>
						<td><%= objRoom.getSize() %></td>
						<td><%= objRoom.getPersons() %></td>
						<td><%= objRoom.getPrice() %></td>
						<td><%= objRoom.getCategory() %></td>
						<td>
							<a href="?action=delete&id=<%= objRoom.getId() %>">delete</a>
							<a href="?action=edit&id=<%= objRoom.getId() %>">edit</a>
						</td>
					</tr>
				<%
			}	
		}
	%>
</table>

</body>
</html>