<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<h2>Home page</h2>
		
		<!-- Username -->
		<p>Welcome <security:authentication property="principal.username"/>!</p>
		
		<!-- Roles -->
		<p>Role(s): <security:authentication property="principal.authorities"/></p>
		
		<!-- Links -->
		<security:authorize access="hasRole('MANAGER')">
			<p><a href="${pageContext.request.contextPath}/managerNotifications">Manager notifications</a></p>
		</security:authorize>
		<security:authorize access="hasRole('ADMIN')">
			<p><a href="${pageContext.request.contextPath}/systems">Admin meetings</a></p>
		</security:authorize>
		
		<!-- Logout button -->
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout"/>
		</form:form> 
	</body>
</html>