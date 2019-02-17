<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Home</title>
	</head>
	<body>
		<h2>Home page</h2>
		
		<p>Welcome!</p>
		
		<form:form action="${pageContext.request.contextPath}/logout" method="POST">
			<input type="submit" value="Logout"/>
		</form:form> 
	</body>
</html>