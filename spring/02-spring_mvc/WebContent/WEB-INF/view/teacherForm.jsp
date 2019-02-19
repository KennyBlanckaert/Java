<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Teacher Form</title>
		<style>
			.error {
				color: red;
			}
		</style>
	</head>
	<body>
		<h2>Teacher registration page</h2>
		<form:form action="processForm" modelAttribute="teacher">
		
			Firstname: <form:input path="firstName"/>
			<br></br>
			
			Lastname*: <form:input path="lastName"/>
			<form:errors path="lastName" cssClass="error"/>
			<br></br>
			
			teacherID*: <form:input path="teacherID"/>
			<form:errors path="teacherID" cssClass="error"/>
			<br></br>
			
			course code*: <form:input path="courseCode"/>
			<form:errors path="courseCode" cssClass="error"/>
			<br></br>
			
			Family Members*: <form:input path="familyMembers"/>
			<form:errors path="familyMembers" cssClass="error"/>
			<br></br>
			
			<input type="submit" value="submit"/>
		</form:form>
		
	</body>
</html>