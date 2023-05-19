<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>semicolon</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'>
    <link rel="stylesheet" href="../../css/style.css">

    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            max-width: 100%;
            white-space: nowrap;
            background-color: #f8f8f8;
            border-radius: 2% !important;
            overflow: hidden;
        }
        table th, table td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        table th {
            background-color: #42C3AA !important;
            color: black  !important;
            white-space: nowrap;
        }
        table tr:hover {
            background-color: #f5f5f5;
        }
        @media screen and (max-width: 1000px) {
            table {
                overflow-x: auto !important; /* Add horizontal scrollbar */
            }
            table tr, table td, table th {
                border: none;
            }
            table th {
                background-color: #42C3AA !important;
                color: white;
                border: none;
            }
            table td {
                border: none;
                text-align: right;
                padding-right: 30px;
                position: relative;
            }
            table td::before {
                content: attr(data-label);
                position: absolute;
                left: 0;
                width: 30%;
                padding-left: 8px;
                font-weight: bold;
                text-align: left;
            }
        }

        /*th {*/
        /*    background-color: #42C3AA;*/
        /*    border-radius:1px !important;*/
        /*    color: black;*/
        /*}*/

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>

    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<!-- display the message -->
<c:import url="include/show-message.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 sidebar-custom" >
            <%@include  file="../../pages/sidebar.html" %>
        </div>
        <div class="col-12 col-md-9">
            <c:if test='${not empty suppliers && !message.error}'>
                <div class="container">
                    <div class="table-responsive">
                        <table class="table table-hover " style="border-radius: 20px !important;">
                            <thead>
                            <tr class="table-success">
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
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
</body>
</html>

