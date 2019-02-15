<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<title>List Customers</title>
		<link type="text/css" rel="stylesheet" 
				href="${pageContext.request.contextPath}/resources/css/style.css">
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>Customer Relationship Manager</h2>
			</div>
		</div>
		
		<div id="container">
			<div id="content">
			
			<!-- Add customer button -->
			<input type="button" value="Add customer" class="add-button"
					onclick="window.location.href='customerForm'; return false;"/>
			
			<!-- Customer table -->
				<table>
					<tr>
						<th>First name</th>
						<th>Last name</th>
						<th>Email</th>
					</tr>
					<c:forEach var="tempCustomer" items="${customers}">
						<tr>
							<td>${tempCustomer.firstname}</td>
							<td>${tempCustomer.lastname}</td>
							<td>${tempCustomer.email}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>