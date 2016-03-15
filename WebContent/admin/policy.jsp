<%@ page import="java.util.*" %>
<%@ page import="com.apu.obj.*" %>
<%@ page import="com.apu.method.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Policy</title>
</head>
<body>

<script type="text/javascript">

	function validateForm() {
		if (document.getElementById("checkInTime").value==""){
			alert("Please enter check-in time");
			return false;
		}
		if (document.getElementById("checkOutTime").value==""){
			alert("Please enter check-out time");
			return false;
		}
		if (document.getElementById("cancelPeriod").value==""){
			alert("Please enter cancel period");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("checkInTime").value="";
		document.getElementById("checkOutTime").value="";
		document.getElementById("cancelPeriod").value="";
	}
	
</script>

<%

	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String checkInTime = "";
	String checkOutTime = "";
	String cancelPeriod = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			Policy policy = new Policy();
			policy.setCheckInTime(request.getParameter("checkInTime"));
			policy.setCheckOutTime(request.getParameter("checkOutTime"));
			policy.setCancelPeriod(request.getParameter("cancelPeriod"));
			
			if(actionMethod.savePolicy(policy)){
				reqStatus = "Added successfully!";
			}else{
				reqStatus = "Fail to add!";
			}
		}
	
		if (request.getParameter("btnEdit") !=null ) {	// if edit request
			Policy policy = new Policy();
			policy.setId(request.getParameter("id"));
			policy.setCheckInTime(request.getParameter("checkInTime"));
			policy.setCheckOutTime(request.getParameter("checkOutTime"));
			policy.setCancelPeriod(request.getParameter("cancelPeriod"));
			
			if(actionMethod.updatePolicy(policy)){
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
					if (actionMethod.removePolicy(Integer.parseInt(request.getParameter("id")))){
						reqStatus = "Removed successfully!";
					}else{
						reqStatus = "Unable to remove!";
					}
				}
			}
			
			//edit
			if(action.equalsIgnoreCase("edit")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					Policy objPolicy = actionMethod.fetchPolicy(Integer.parseInt(request.getParameter("id")));
					id = request.getParameter("id");
					checkInTime = objPolicy.getCheckInTime();
					checkOutTime = String.valueOf(objPolicy.getCheckOutTime());
					cancelPeriod = String.valueOf(objPolicy.getCancelPeriod());
				}		
			}
		}
	}
%>

<h2>Policy - Add/Edit/View</h2>
<hr>

<form method="post" action="" id="frmAddPolicy" onsubmit="return validateForm();">
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
			<td>Check-In Time</td>
			<td><input type="text" name="checkInTime" id="checkInTime" value="<%= checkInTime %>" /></td>
		</tr>
		<tr>
			<td>Check-out Time</td>
			<td><input type="text" name="checkOutTime" id="checkOutTime" value="<%= checkOutTime %>" /></td>
		</tr>
		<tr>
			<td>Cancel Period</td>
			<td><input type="text" name="cancelPeriod" id="cancelPeriod" value="<%= cancelPeriod %>" /></td>
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
		<th>Check-in Time</th>
		<th>Check-out Time</th>
		<th>Cancel Period</th>
		<th></th>
	</tr>
	<%
		ArrayList<Policy> objPolicyList = actionMethod.fetchAllPolicy();
		if (objPolicyList!=null) {
			for (Policy objPolicy : objPolicyList) {
				%>
					<tr>
						<td><%= objPolicy.getId() %></td>
						<td><%= objPolicy.getCheckInTime() %></td>
						<td><%= objPolicy.getCheckOutTime() %></td>
						<td><%= objPolicy.getCancelPeriod() %></td>
						<td>
							<a href="?action=delete&id=<%= objPolicy.getId() %>">delete</a>
							<a href="?action=edit&id=<%= objPolicy.getId() %>">edit</a>
						</td>
					</tr>
				<%
			}	
		}
	%>
</table>

</body>
</html>