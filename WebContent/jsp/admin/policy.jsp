<%@page import="com.apu.util.Config"%>
<%@ page import="java.util.*" %>
<%@ page import="com.apu.obj.*" %>
<%@ page import="com.apu.method.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin - Policy</title>
</head>
<body>

<script type="text/javascript">

	function validateForm() {
		if (document.getElementById("name").value==""){
			alert("Please enter name");
			return false;
		}
		if (document.getElementById("desc").value==""){
			alert("Please enter desc");
			return false;
		}
		return true;
	}
	
	function clearForm(){
		document.getElementById("name").value="";
		document.getElementById("desc").value="";
	}
	
</script>

<%

	Config appConfig = new Config();
	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String name = "";
	String desc = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			Policy policy = new Policy();
			policy.setName(request.getParameter("name"));
			policy.setDesc(request.getParameter("desc"));
			
			if(actionMethod.savePolicy(policy)){
				reqStatus = "Added successfully!";
			}else{
				reqStatus = "Fail to add!";
			}
		}
	
		if (request.getParameter("btnEdit") !=null ) {	// if edit request
			Policy policy = new Policy();
			policy.setId(request.getParameter("id"));
			policy.setName(request.getParameter("name"));
			policy.setDesc(request.getParameter("desc"));
			
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
					name = objPolicy.getName();
					desc = objPolicy.getDesc();
				}		
			}
		}
	}
%>

<div align="center">
<h2>Policy - Add/Edit/View</h2>
Hi, admin <br>
<a href="<%= appConfig.getAppContext() %>/jsp/admin/main.jsp">Home</a>	| 
<a href="<%= appConfig.getAppContext() %>/jsp/auth/logout.jsp">Logout</a>
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
		ArrayList<Policy> objPolicyList = actionMethod.fetchAllPolicy();
		if (objPolicyList!=null) {
			for (Policy objPolicy : objPolicyList) {
				%>
					<tr>
						<td><%= objPolicy.getId() %></td>
						<td><%= objPolicy.getName() %></td>
						<td><%= objPolicy.getDesc() %></td>
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
</div>

</body>
</html>