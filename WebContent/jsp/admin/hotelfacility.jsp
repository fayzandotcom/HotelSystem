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
<title>Admin - Hotel Facility</title>
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
	String foodName = "";
	String foodDesc = "";

	String reqStatus = "";
	String action = "";

	if("POST".equalsIgnoreCase(request.getMethod())) {	// if POST request
		
		if (request.getParameter("btnAdd") !=null ) {	// if add request
			HotelFacility objHotelFacility = new HotelFacility();
			objHotelFacility.setHotelId(request.getParameter("hotelid"));
			objHotelFacility.setFacilityId(request.getParameter("foodid"));
			
			if(actionMethod.saveHotelFacility(objHotelFacility)){
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
					if (actionMethod.removeHotelFacility(Integer.parseInt(request.getParameter("id")))){
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
<h2>Hotel Facility - Assign/Remove</h2>
Hi, admin <br>
<a href="<%= appConfig.getAppContext() %>/jsp/admin/main.jsp">Home</a>	| 
<a href="<%= appConfig.getAppContext() %>/jsp/auth/logout.jsp">Logout</a>
<hr>

<form method="post" action="" id="frmAddHotelFacility" onsubmit="return validateForm();">
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
			<td>Facility</td>
			<td>
				<select	name="foodid" id="foodid">
					<%
						ArrayList<Facility> objFacilityList = actionMethod.fetchAllFacility();
						for (Facility objFacility: objFacilityList) {
							%>
								<option value="<%= objFacility.getId() %>"><%= objFacility.getName() %> - <%= objFacility.getDesc() %></option>
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
		<th>Facility Name</th>
		<th>Facility Description</th>
		<th></th>
	</tr>
	<%
		ArrayList<HotelFacility> objHotelFacilityList = actionMethod.fetchAllHotelFacility();
		if (objHotelFacilityList!=null) {
			for (HotelFacility objHotelFacility : objHotelFacilityList) {
				%>
					<tr>
						<td><%= objHotelFacility.getId() %></td>
						<td>
							<% 
								String strHotel = "";
								if(objHotelList!=null){
									for (Hotel objHotel: objHotelList) {
										if(objHotel.getId().equals(objHotelFacility.getHotelId())){
												strHotel = objHotel.getName();
										}
									}
								}
							%>
							<%= strHotel %>
						</td>
						<td>
							<% 
								String strFacilityName = "";
								String strFacilityDesc = "";
								if(objFacilityList!=null){
									for (Facility objFacility: objFacilityList) {
										if(objFacility.getId().equals(objHotelFacility.getFacilityId())){
												strFacilityName = objFacility.getName();
												strFacilityDesc = objFacility.getDesc();
										}
									}
								}
							%>
							<%= strFacilityName %>
						</td>
						<td><%= strFacilityDesc %></td>
						<td>
							<a href="?action=delete&id=<%= objHotelFacility.getId() %>">delete</a>
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