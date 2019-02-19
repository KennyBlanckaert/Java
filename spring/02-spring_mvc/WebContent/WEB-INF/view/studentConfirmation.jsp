<!DOCTYPE html>
<html>
	<head>
		<title>Student confirmation</title>
	</head>
	<body>
		<h2>Student registration complete</h2>
		<p>Student "${student.firstName} ${student.lastName}" registered successfully for the ${student.session} session</p>
		<p>(${student.country})</p>
		<img src="${pageContext.request.contextPath}/resources/images/complete.jpg" alt="complete"/>
	</body>
</html>