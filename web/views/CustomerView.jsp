<%-- 
    User: ananya
    Date: 2025-04-07
    Time: 2:11 p.m.
    To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Add this at the top of your JSP for debugging -->
<%
    Double userBalance = (Double) session.getAttribute("userBalance");
    if (userBalance == null) userBalance = 0.0;
    System.out.println("DEBUG - JSP: userBalance from session = " + userBalance);
%>

<html>
<head>
    <title>Customer View</title>
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
</head>
<body class="registration">
    <div class="logoblack-container">
        <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo">
        </a>
    </div>
    <ul class="menu">
        <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
    </ul>

    <p class="balance">Balance : <span class="span">$${sessionScope.userBalance}</span></p>
    <c:url var="addMoneyUrl" value="/views/AddMoney.jsp"/>
    <form action="${addMoneyUrl}" method="POST">
        <input type="hidden" name="userId" value="${sessionScope.userId}">
        <input type="hidden" name="currentBal" value="${sessionScope.userBalance}">
        <button id="add-money-btn" type="submit">Add money</button>
    </form>

    <div class="container">
        <h1>Food items</h1>
        <c:url var="fetchUrl" value="/FetchRetailersServlet"/>
        <form action="${fetchUrl}" method="post">
            <label for="location">Select a location:</label><br>
            <select id="location" name="location">
                <option value="Nepean">Nepean</option>
                <option value="Kanata">Kanata</option>
                <option value="Barrhaven">Barrhaven</option>
                <option value="Downtown">Downtown</option>
            </select>
            <div style="text-align: right;">
                <button type="submit">OK</button>
            </div>
        </form>
    </div>
</body>
</html>
