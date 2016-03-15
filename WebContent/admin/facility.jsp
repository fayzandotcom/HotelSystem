<%@ page import="java.util.*" %>
<%@ page import="com.apu.obj.*" %>
<%@ page import="com.apu.method.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Facility</title>
</head>
<body>

<script type="text/javascript">

	function validateForm() {
		if (document.getElementById("name").value==""){
			alert("Please enter name");
			return false;
		}
		if (document.getElementById("desc").value==""){
			alert("Please enter description");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("name").value="";
		document.getElementById("des").value="";
	}
	
</script>

<%

	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String name = "";
	String desc = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			Facility facility = new Facility();
			facility.setName(request.getParameter("name"));
			facility.setDesc(request.getParameter("desc"));
			
			if(actionMethod.saveFacility(facility)){
				reqStatus = "Added successfully!";
			}else{
				reqStatus = "Fail to add!";
			}
		}
	
		if (request.getParameter("btnEdit") !=null ) {	// if edit request
			Facility facility = new Facility();
			facility.setId(request.getParameter("id"));
			facility.setName(request.getParameter("name"));
			facility.setDesc(request.getParameter("desc"));
			
			if(actionMethod.updateFacility(facility)){
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
					if (actionMethod.removeFacility(Integer.parseInt(request.getParameter("id")))){
						reqStatus = "Removed successfully!";
					}else{
						reqStatus = "Unable to remove!";
					}
				}
			}
			
			//edit
			if(action.equalsIgnoreCase("edit")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					Facility objFacility = actionMethod.fetchFacility(Integer.parseInt(request.getParameter("id")));
					id = request.getParameter("id");
					name = objFacility.getName();
					desc = String.valueOf(objFacility.getDesc());
				}		
			}
		}
	}
%>

<h2>Facility - Add/Edit/View</h2>
<hr>

<form method="post" action="" id="frmAddFacility" onsubmit="return validateForm();">
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
			<td>Description</td>
			<td><input type="text" name="desc" id="desc" value="<%= desc %>" /></td>
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
		<th>Description</th>
		<th></th>
	</tr>
	<%
		ArrayList<Facility> objFacilityList = actionMethod.fetchAllFacility();
		if (objFacilityList!=null) {
			for (Facility objFacility : objFacilityList) {
				%>
					<tr>
						<td><%= objFacility.getId() %></td>
						<td><%= objFacility.getName() %></td>
						<td><%= objFacility.getDesc() %></td>
						<td>
							<a href="?action=delete&id=<%= objFacility.getId() %>">delete</a>
							<a href="?action=edit&id=<%= objFacility.getId() %>">edit</a>
						</td>
					</tr>
				<%
			}	
		}
	%>
</table>

</body>
</html>