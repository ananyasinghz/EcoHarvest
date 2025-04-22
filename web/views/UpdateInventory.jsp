<%--
Author     : ananya
  Date: 2024-04-07
  Time: 9:41 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Inventory</title>
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
</head>
<body class = "registration">
    <div class="logoblack-container">
        <a href="${pageContext.request.contextPath}">
        <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
    </div>
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/LogoutServlet">Log out</a></li>
    </ul>
<div class="container">
<form action="${pageContext.request.contextPath}/UpdateInventoryServlet" method="post">
    <label for="productName" >Product Name: </label><br>
    <input type="text" id="productName" name="productName" required><br>
    <label for="batchNum" >Product Batch Number: </label><br>
    <input type="text" id="batchNum" name="batchNum" required><br>
    <label for="quantity" >New Quantity: </label><br>
    <input type="text" id="quantity" name="quantity" required><br>
    <div style="text-align: right; background-color: white;">
    <input type="submit" name="action" value="Update Item"><br><br>
    </div>
</form>

<%
    String message = (String) request.getAttribute("message");
    String messageColor = (String) request.getAttribute("messageColor");
    if (message != null && !message.isEmpty()) {
%>
<p style="color: <%= messageColor %>;"><%= message %></p>
<%
    }
%>

</body>
</html>
