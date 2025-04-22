<%--
Author     : ananya
  Date: 2024-04-07
  Time: 2:11 p.m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Charity View</title>
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
</head>
<body class = "registration">
        <div class="logoblack-container">
            <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
        </div>
<ul class="menu">
    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
</ul>
<div class="inventory">
<h1>Transaction</h1>
        <c:if test="${not empty sessionScope.message}">
            <div class="success" style="text-align: center;">
                <p>${sessionScope.message}</p>
            </div>
            <c:set var="message" scope="session" value=""/>
        </c:if>

        <c:if test="${not empty sessionScope.error}">
            <div class="error">
                <p>${sessionScope.error}</p>
            </div>
            <c:set var="error" scope="session" value=""/>
        </c:if>
<table>
    <thead>
        <tr>
            <th>Product Name</th>
            <th>Category</th>
            <th>Expiry Date</th>
            <th>Quantity</th>
            <th>Claim Quantity</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.category}</td>
            <td>${item.expiryDate}</td>
            <td>${item.quantity}</td>
            <td class ="claim-form">
                <form class = "form-group" action="${pageContext.request.contextPath}/CharityClaimsServlet" method="post">

                    <input type="hidden" name="itemId" value="${item.id}">
                    <label for="claimedQuantity${item.id}"></label>
                    <select class = "claimedSelect" name="claimedQuantity" id="claimedQuantity${item.id}">
                        <c:forEach begin="1" end="${item.quantity}" var="num">
                            <option value="${num}">${num}</option>
                        </c:forEach>
                    </select>

                    <input type="submit" value="Claim">
                </form>
             </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
<!--
    <br>
    <a href="${pageContext.request.contextPath}/transaction/CharityTransactionConfirmView.jsp">
        <button type="submit">Confirm</button></a>-->
</div>
</body>
</html>




