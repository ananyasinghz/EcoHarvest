<%--
    Document   : RetailerView.jsp
    Created on : April 5, 2025
    Author     : ananya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
    <title>Retailer Information Page</title>
</head>

<body class = "registration" onload="resetRequiredFields()">

    <div class="logoblack-container">
        <a href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
    </div>

    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/LogoutServlet">Log out</a></li>
    </ul>

<div class="container">
    <h1>Add Items</h1>
    <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null && !errorMessage.isEmpty()) {
%>
<div style="color: red;"><%= errorMessage %></div>
<%
    }
%>
<c:url var="retInvUrl" value="/RetailerInventoryServlet"/>
<form action="${retInvUrl}" method="post">
    <fieldset id="inventoryFields">
    <label for="productName" >Product Name :</label><br>
    <input type="text" id="productName" name="productName" required><br>
    <label for="category" >Product Category :</label><br>
    <select id="category" name="category">
        <option value="Fruit">Fruit</option>
        <option value="Vegetable">Vegetable</option>
        <option value="Meat">Meat</option>
        <option value="Seafood">Seafood</option>
        <option value="Dairy & Eggs">Dairy & Eggs</option>
    </select><br>
    <label for="batch" >Batch Number :</label><br>
    <input type="number" id="batch" name="batch" required><br></br>
    <label for="quantity" >Quantity :</label><br>
    <input type="number" id="quantity" name="quantity" min="0" required><br></br>
    <label for="expiryDate">Expiry Date :</label><br>
    <input type="datetime-local" id="expiryDate" name="expiryDate" onchange="validateExpiryDate()"><br>
    <span id="expiryDateError" style="color: red;"></span>
    <script>
        function validateExpiryDate() {
            var inputDate = new Date(document.getElementById("expiryDate").value);
            var currentDate = new Date();

            if (inputDate < currentDate) {
                document.getElementById("expiryDateError").innerHTML = "Expiry date must be present or future.";
                document.getElementById("expiryDate").value = ""; // Clear the input value
            } else {
                document.getElementById("expiryDateError").innerHTML = "";
            }
        }
    </script><br>
    <label for="price" >Unit Price: </label><br>
    <input type="number" id="price" name="price" step="0.01" min="0" required><br>
    </fieldset>
    <div style = "text-align: right;">
    <br>
    <input type="submit" name="action" value="Add Item">
    <br><br>
    <input type="submit" name="action" value="Update Item" onclick="removeRequired()"><br>
    <br>
    <input type="submit" name="action" value="View Inventory" onclick="removeRequired()">
    </div>
</form>
<script>
    function removeRequired() {
        var fields = document.getElementById("inventoryFields").getElementsByTagName('input');
        for (var i = 0; i < fields.length; i++) {
            fields[i].removeAttribute('required');
        }
    }

    function resetRequiredFields() {
        var fields = document.getElementById("inventoryFields").getElementsByTagName('input');
        for (var i = 0; i < fields.length; i++) {
            fields[i].setAttribute('required', 'true');
        }
    }
</script>
</div>
</body>
</html>