<%-- 
    Document   : Charity_registration
    Created on : Mar 27, 2025, 8:11:30 PM
    Author     : ananya
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="AddMoney" value="/ProcessAddMoneyServlet"/>
<html>
<head>
    <title>Add Money</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            width: 300px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ccc;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        input[type='number'], button {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
        }
        button {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 style = "font-size: 25px; letter-spacing: 0px;">Add Money to Account</h1><br>
    <p style = "text-align: center;">Your current balance is: $<%= session.getAttribute("currentBal") %></p><br>
    
    <form action="${AddMoney}" method="POST">
        <input type="hidden" name="userId" value="<%= session.getAttribute("userId") %>">
        <label for="amount">Amount to Add:</label><br></br>
        <input type="number" id="amount" name="amount" min="1" step="0.01" required><br></br>
        <button type="submit">Add Balance</button>
    </form>
</div>
</body>
</html>

