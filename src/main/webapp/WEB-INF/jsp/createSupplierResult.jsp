

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Create Employee</title>
</head>

<body>
<h1>Create Supplier</h1>
<hr/>


<!-- display the just created employee, if any and no errors -->
<c:if test="${not empty supplier}">
	<ul>
		<li>id: <c:out value="${supplier.id}"/></li>
		<li>name: <c:out value="${supplier.name}"/></li>
		<li>address: <c:out value="${supplier.address}"/></li>
		<li>email: <c:out value="${supplier.email}"/></li>
		<li>telephoneNumber: <c:out value="${supplier.telephoneNumber}"/></li>

	</ul>
</c:if>
</body>
</html>
