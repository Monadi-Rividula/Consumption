<%@page import="com.Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Consumption Management</title>
 		<link rel="stylesheet" href="Views/bootstrap.min.css">
 		<script src="Components/jquery-3.2.1.min.js"></script>
 		<script src="Components/consume.js"></script>
	</head>
	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
		<h1>Consumption Management</h1>
		
		<form id="formItem" name="formItem" method="post" action="consume.jsp">
		
			 Enter Month: 
	 		<input id="AccountNo" name="month-text" type="text" 
			 class="form-control form-control-sm">
	 		<br>Enter Past Unit Count: 
	 		<input id="Address" name="past-unit" type="text" 
	 		class="form-control form-control-sm">
	 		<br>Enter Current Unit Count: 
	 		<input id="Inquiry" name="current-unit" type="text" 
	 		class="form-control form-control-sm">
	 		<br>Enter Consumed Unit Count: 
	 		<input id="Status" name="consumed-unit" type="text" 
	 		class="form-control form-control-sm">
	 		
	 		<br>
	 		<input id="btnSave" name="btnSave" type="button" value="Save" 
			 class="btn btn-primary">
	 		<input type="hidden" id="hidItemIDSave" 
	 		name="hidItemIDSave" value="">
	 		
		</form>
		
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divItemsGrid">
 		<%
 		Model modelObj = new Model(); 
	 		out.print(modelObj.readService()); 
		 %>
		</div>
		</div> </div> </div> 
		</body>
		</html>