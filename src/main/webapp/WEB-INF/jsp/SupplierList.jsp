<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Suppliers</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #1549b8;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Telephone Number</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${suppliers}" var="supplier">
        <tr>
            <td>${supplier.name}</td>
            <td>${supplier.email}</td>
            <td>${supplier.address}</td>
            <td>${supplier.telephoneNumber}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>