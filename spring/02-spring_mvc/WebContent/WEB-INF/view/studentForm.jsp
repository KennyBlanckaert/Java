<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Student form</title>
	</head>
	<body>
		<h2>Student registration page</h2>
		<form:form action="processForm" modelAttribute="student">
			
			Firstname: <form:input path="firstName"/>
			<br></br>
			
			Lastname: <form:input path="lastName"/>
			<br></br>
			
			Country:
			<form:select path="country">
				<form:options items="${countryOptions}"/>
			</form:select>
			<br></br>
			
			Session preference: 
			<form:radiobutton path="session" value="morning"/> Morning
			<form:radiobutton path="session" value="afternoon"/> Afternoon
			<br></br>
			
			<!--  form:checkbox => Java class field should be a collection -->
			
			<input type="submit" value="submit"/>
			
		</form:form>
	</body>
</html>