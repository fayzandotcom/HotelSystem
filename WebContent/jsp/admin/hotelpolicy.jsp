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
<title>Admin - Hotel Policy</title>
</head>
<body>

<script type="text/javascript">

	/*
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
	*/
	
</script>

<%

	Config appConfig = new Config();
	ActionMethod actionMethod = new ActionMethod();

	String id = "";
	String hotelName = "";
	String policyName = "";
	String policyDesc = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			HotelPolicy objHotelPolicy = new HotelPolicy();
			objHotelPolicy.setHotelId(request.getParameter("hotelid"));
			objHotelPolicy.setPolicyId(request.getParameter("policyid"));
			
			if(actionMethod.saveHotelPolicy(objHotelPolicy)){
				reqStatus = "Added successfully!";
			}else{
				reqStatus = "Fail to add!";
			}
		}
	
	}else {	//GET request
	
		if(request.getParameter("action") != null) {
			
			action = request.getParameter("action");
			
			//delete
			if(action.equalsIgnoreCase("delete")) {
				if(request.getParameter("id") != null || request.getParameter("id") != "") {
					if (actionMethod.removeHotelPolicy(Integer.parseInt(request.getParameter("id")))){
						reqStatus = "Removed successfully!";
					}else{
						reqStatus = "Unable to remove!";
					}
				}
			}
			
		}
	}
%>

<div align="center">
<h2>Hotel Policy - Assign/Remove</h2>
Hi, admin <br>
<a href="<%= appConfig.getAppContext() %>/jsp/admin/main.jsp">Home</a>	| 
<a href="<%= appConfig.getAppContext() %>/jsp/auth/logout.jsp">Logout</a>
<hr>

<form method="post" action="" id="frmAddHotelPolicy" onsubmit="return validateForm();">
	<table>
		<tr>
			<td>Hotel</td>
			<td>
				<select	name="hotelid" id="hotelid">
					<%
						ArrayList<Hotel> objHotelList = actionMethod.fetchAllHotel();
						for (Hotel objHotel: objHotelList) {
							%>
								<option value="<%= objHotel.getId() %>"><%= objHotel.getName() %></option>
							<%
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>Policy</td>
			<td>
				<select	name="policyid" id="policyid">
					<%
						ArrayList<Policy> objPolicyList = actionMethod.fetchAllPolicy();
						for (Policy objPolicy: objPolicyList) {
							%>
								<option value="<%= objPolicy.getId() %>"><%= objPolicy.getName() %> - <%= objPolicy.getDesc() %></option>
							<%
						}
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<!-- <input type="button" value="Clear" name="btnClear" onclick="clearForm()"/>  -->
				<input type="submit" value="Assign" name="btnAdd" />
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
		<th>Hotel Name</th>
		<th>Policy Name</th>
		<th>Policy Description</th>
		<th></th>
	</tr>
	<%
		ArrayList<HotelPolicy> objHotelPolicyList = actionMethod.fetchAllHotelPolicies();
		if (objHotelPolicyList!=null) {
			for (HotelPolicy objHotelPolicy : objHotelPolicyList) {
				%>
					<tr>
						<td><%= objHotelPolicy.getId() %></td>
						<td>
							<% 
								String strHotel = "";
								if(objHotelList!=null){
									for (Hotel objHotel: objHotelList) {
										if(objHotel.getId().equals(objHotelPolicy.getHotelId())){
												strHotel = objHotel.getName();
										}
									}
								}
							%>
							<%= strHotel %>
						</td>
						<td>
							<% 
								String strPolicyName = "";
								String strPolicyDesc = "";
								if(objPolicyList!=null){
									for (Policy objPolicy: objPolicyList) {
										if(objPolicy.getId().equals(objHotelPolicy.getPolicyId())){
												strPolicyName = objPolicy.getName();
												strPolicyDesc = objPolicy.getDesc();
										}
									}
								}
							%>
							<%= strPolicyName %>
						</td>
						<td><%= strPolicyDesc %></td>
						<td>
							<a href="?action=delete&id=<%= objHotelPolicy.getId() %>">delete</a>
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