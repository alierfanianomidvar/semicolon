<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title>Create Employee Form</title>
</head>

<body>
<h1>Create Employee Form</h1>

<form method="POST"  action="supplier">
	<label for="name">name:</label>
	<input id="name" name="supplierName" type="text"/><br/>

	<label for="address">address:</label>
	<input id="address" name="supplierAddress" type="text"/><br/>

	<label for="email">email:</label>
	<input id="email" name="supplierEmail" type="text"/><br/>

	<label for="telephoneNumber">telephone Number:</label>
	<input id="telephoneNumber" name="supplierTelephoneNumber" type="text"/><br/><br/>

	<button type="submit">Submit</button><br/>
	<button type="reset">Reset the form</button>
</form>
</body>
</html>