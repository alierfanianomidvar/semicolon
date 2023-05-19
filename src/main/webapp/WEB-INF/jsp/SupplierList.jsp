<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
            background-color: whitesmoke !important;
            color: black !important;
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
                background-color: whitesmoke;
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

        .search-box {
            margin-bottom: 20px;
        }

        .add-btn {
            margin-bottom: 20px;
        }

        @media (max-width: 576px) {
            .search-box {
                margin-bottom: 10px;
            }

            .add-btn {
                margin-bottom: 10px;
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
    <script>
        $(document).ready(function() {
            $('.search-box input[type="text"]').on('keyup', function() {
                var searchText = $(this).val().toLowerCase();
                $('table tbody tr').each(function() {
                    var rowText = $(this).text().toLowerCase();
                    $(this).toggle(rowText.indexOf(searchText) > -1);
                });
            });
        });
    </script>

    <link rel="stylesheet" href="../css/style.css">
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 sidebar-custom">
            <%@include file="../../pages/sidebar.html" %>
            <!-- display the message -->
            <c:import url="include/show-message.jsp"/>
        </div>
        <div class="col-12 col-md-9">
            <div class="row" style="margin-top: 2%">
                <div class="col-lg-12">
                    <h4 style="margin-bottom: 1%; font-weight: bold;">Supplier</h4>
                </div>
                <div class="col-lg-6">
                    <div class="search-box">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search..."
                                   id="search-input">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="add-btn text-lg-right">
                        <button class="btn btn-custom">Add supplier</button>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div test='${not empty suppliers && !message.error}'>
                        <div class="table-responsive">
                            <table class="table table-hover " style="border-radius: 10px !important;">
                                <thead>
                                <tr class="">
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
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.min.js"></script>
</body>
</html>

