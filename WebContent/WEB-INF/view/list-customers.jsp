<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Reference css file -->
<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" >
<title>List Customers</title>
</head>
<body>
		<div id="wrapper">
			<div id="header">
			<h2> CRM - Customer Relationship Manager </h2>
			</div>
		</div>
		
		<div id="container" >
		<div id="content">
		<!-- put a new button : Add customer -->
		<input type="button" value="Add customer"
				onclick="window.location.href='showFormForAdd';return false"
				class="add-button"
				/>
		
		<!--  adding the html table -->
		<table>
			<tr>
				<th> First Name </th>
				<th> Last Name </th>
				<th> Email </th>
				<th> Action </th>
			</tr>
			<!-- loop over and print our customers -->
			
			<c:forEach var="tempCustomer" items="${customers}">
			<!-- construct and update link with customer id -->
			<c:url var="updateLink" value="/Customer/showFormForUpdate" >
					<c:param name="customerId" value="${tempCustomer.id}"/>
					
			</c:url>
			<!-- construct and delete link -->
			<c:url var="deleteLink" value="/Customer/delete" >
					<c:param name="customerId" value="${tempCustomer.id}"/>
					
			</c:url>
			<tr>
				<td> ${tempCustomer.firstName}</td>
				<td> ${tempCustomer.lastName}</td>
				<td> ${tempCustomer.email}</td>
				<!-- display the update link -->
				<td> 
						<a href="${updateLink}">Update</a>
						|
					<a href="${deleteLink}" onClick="if(!(confirm('Are you sure you want to delete this customer?'))) return false " >
					 Delete</a>
				</td>
			</tr>
			
			</c:forEach>
			
		</table>
		</div>
		</div>

</body>
</html>