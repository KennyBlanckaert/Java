<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
			
				<!-- Customer button -->
				<input type="button" value="Add customer" class="add-button"
					onclick="window.location.href='customerForm'; return false;"/>
			
				<!--  Search box -->
	            <form:form action="searchCustomer" method="POST">
	                Search customer: <input type="text" name="keyword" />
	                
	                <input type="submit" value="Search" class="add-button" />
	            </form:form>
			
				<!-- Customer table -->
				<table>
					<tr>
						<th>First name</th>
						<th>Last name</th>
						<th>Email</th>
						<th>Action<th>
					</tr>
					<c:forEach var="customer" items="${customers}">
						<c:url var="updateLink" value="/customer/updateCustomerForm">
							<c:param name="id" value="${customer.id}"/>
						</c:url>
						<c:url var="deleteLink" value="/customer/deleteCustomer">
							<c:param name="id" value="${customer.id}"/>
						</c:url>
						
						<tr>
							<td>${customer.firstname}</td>
							<td>${customer.lastname}</td>
							<td>${customer.email}</td>
							<td>
								<a href="${updateLink}">
									Update
								</a>
								|
								<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this?'))) return false;">
									Delete
								</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>