<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- above is support for JSTL Core tags -->
<!DOCTYPE html>
<html>
<head>

	<title>List Customers</title>
	
	<!-- reference our style sheet  ${pageContext.request.contextPath}
	this gets the proper name of the application
	-->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

</head>

<body>

	<div id="wrapper"> 
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	<div id="container">
		
		<div id="content">
		
		<!-- put new button: Add customer -->
		
		<input type="button" value="Add Customer" 
				onclick="window.location.href='showFormForAdd'; return false;"
				class="add-button"/>
			<!--  add our html table here -->
			
		<!-- add a search box -->	
		<form:form action="search" method="GET">
			Search Customer(s):<input type="text"
			name="theSearchName"/>
			<input type="submit" value="Search" class="add-Button"/>
		</form:form>
			<table>
		
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<!-- loop over and pint our customer  remember customers in curly braces is our model attribute-->
				<c:forEach var="tempCustomer" items="${customers}">
				
				<!-- construct an "update" link with customer id -->
				<c:url var="updateLink" value = "/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}" /> <!-- where tempCustomer.id refers to the ID of tempCustomer, note customerId matches param in showFormForpdate-->
				</c:url>
				
				
				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink" value = "/customer/delete">
					<c:param name="customerId" value="${tempCustomer.id}" /> <!-- where tempCustomer.id refers to the ID of tempCustomer, note customerId matches param in showFormForpdate-->
				</c:url>
					<tr>
						<td> ${tempCustomer.firstName} </td>
						<td> ${tempCustomer.lastName} </td>
						<td> ${tempCustomer.email} </td>
						
						<td> 
							<!-- display the update link -->
							<a href="${updateLink}">Update</a>
							|
							<a href="${deleteLink}"
							onclick="if(!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>	
						</td>
					</tr>
				
				</c:forEach>
		
			</table>
			
		</div>
	</div>
</body>

</html>