<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Customer</title>
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/style.css">
	
	<link type="text/css"
			rel="stylesheet"
			href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
</head>

<body>
		
		<div id="wrapper">
			<div id="header">
				<h2>CRM - Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
			<h3>Save Customer</h3>
			
			<!-- action sends to Spring MVC mapping**  modelAttribute binds to string customer in showFormForAdd-->
			<form:form action="saveCustomer" modelAttribute="customer" method="POST">
			<!-- Form Load: Calls getters  on load we have customer.getId(id)  and places it in the form:hidden-->
			<!-- Form Submit: Calls setters and on submission we have customer.setId(id) loads from form:hidden-->
				<!-- need to associate this data with customer id -->
				<form:hidden path="id"/> <!-- VERY IMPORTANT else u loose customer ID-->
				<table>
					<tbody>
						<tr>
							<td><label>First Name:</label></td>
							<td><form:input path= "firstName" /></td>
						</tr>
						
						<tr>
							<td><label>Last Name:</label></td>
							<td><form:input path= "lastName" /></td>
						</tr>
						
						<tr>
							<td><label>Email:</label></td>
							<td><form:input path= "email" /></td>
						</tr>
						
						<tr>
							<td><label></label></td>
							<td><input type="submit"  value="save" class="save"/></td>
						</tr>
						
					</tbody>
					
				</table>
			
			</form:form>
			
			<!--<div style="clear; both;"></div> -->
			
			<p>
				<a href="${pageContext.request.contextPath}/customer/list"><strong>Back to List</strong></a>
			</p>
		</div>
		
</body>
</html>