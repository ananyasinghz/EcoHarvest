<%--
  User: ananya
  Date: 2025-04-09
  Time: 3:15 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Checkout Summary</title>
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
</head>
<body class = "registration">

    <div class="logoblack-container">
        <a href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
    </div>

    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/LogoutServlet">Log out</a></li>
    </ul>
<div class="inventory">
<h1>Checkout Summary</h1>
<c:url var="paymentUrl" value="/PaymentServlet"/>
<form action="${paymentUrl}" method="post">
    <table border="1">
        <thead>
        <tr>
            <th>Item Name</th>
            <th>Expiry Date</th>
            <th>Item Price</th>
            <th>On Sale</th>
            <th>Quantity Purchased</th>
            <th>Total Cost (Per Item)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.itemName}</td>
                <td>${item.expiryDate}</td>
                <td>$${item.unitPrice}</td>
                <td>${item.onSale ? 'Yes' : 'No'}</td>
                <td>${item.quantityPurchased}</td>
                <td>$${item.totalCost}</td>
            </tr>
            <input type="hidden" name="retailerInventoryIDs[]" value="${item.retailerInventoryID}" />
            <input type="hidden" name="quantities[]" value="${item.quantityPurchased}" />
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <th colspan="5" style="background-color: #b0b0b0;">Grand Total Cost</th>
            <th style="background-color: #b0b0b0;">$${totalCost}</th>
        </tr>
        </tfoot>
    </table>
    <input type="hidden" name="totalCost" value="${totalCost}" />
    <div class="button-container">
    <button type="submit" class="pay-now-button">Pay now</button>
    </div>
</form>
</div>
</body>
</html>
