<%--
  User: ananya
  Date: 2025-04-09
  Time: 4:56 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Surplus Item Choice</title>
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
<c:url var="surplusUrl" value="/SurplusChoiceServlet"/>
<div class="container">

<!-- Sale -->
  <form action="${surplusUrl}" method="post">
    <input type="hidden" name="itemId" value="${sessionScope.itemId}"/>
    <button type="submit" name="action" value="Sale">Sale</button>
  </form>
  <br>
  <!-- Donation -->
  <form action="${surplusUrl}" method="post">
    <input type="hidden" name="itemId" value="${sessionScope.itemId}"/>
    <button type="submit" name="action" value="Donation">Donation</button>
  </form>
  <br>
  <!-- Both -->
  <form action="${surplusUrl}" method="post">
    <input type="hidden" name="itemId" value="${sessionScope.itemId}"/>
    <button type="submit" name="action" value="Both">Both</button>
  </form>
</div>
</body>
</html>
