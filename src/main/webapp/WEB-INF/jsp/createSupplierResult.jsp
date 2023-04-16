<!--
Copyright 2023 University of Padua, Italy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Author: Nicola Ferro (ferro@dei.unipd.it)
Version: 1.0
Since: 1.0
-->

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

<!-- display the message -->
<c:import url="include/show-message.jsp"/>

<!-- display the just created employee, if any and no errors -->
<c:if test="${not empty supplier && !message.error}">
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
