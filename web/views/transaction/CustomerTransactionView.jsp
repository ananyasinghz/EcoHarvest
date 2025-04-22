<%-- 
    Document   : Charity_registration
    Created on : Mar 27, 2025, 8:11:30 PM
    Author     : ananya
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Transaction View</title>
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
</head>
<body>
        <div class="logoblack-container">
            <a href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
        </div>
<ul class="menu">
    <li><a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></li>
    <!-- menu icon -->
    <li class="top-menu">
        <div class="notification-icon">
            <img src="${pageContext.request.contextPath}/image/notification-icon.png" alt="Alerts">
        </div>
        <div class="money-icon">
            <img src="${pageContext.request.contextPath}/image/money-icon.png" alt="Money">
            <span class="user-balance">$100</span>
        </div>
    </li>
</ul>

<h1>Transaction</h1>

<!--
<label for="location">Select a location:</label>
<select id="location">
    <option value="Nepean">Nepean</option>
    <option value="Kanata">Kanata</option>
    <option value="Barrhaven">Barrhaven</option>
    <option value="Downtown">Downtown</option>
</select>
-->

<table>
    <thead>
        <tr>
            <th>Item</th>
            <th>Batch</th>
            <th>Expiry Date</th>
            <th>Quantity</th>
            <th>Unit Price</th>
            <th>Final Price</th>
            <th>Donation</th>
            <th></th> <!-- Empty header for Buy button -->
        </tr>
    </thead>
    <tbody>

    <!-- The temporary data, have to link from database -->
        <tr>
            <td>Apple</td>
            <td>2022-01</td>
            <td>2024-04-30</td>
            <td>10</td>
            <td>$0.50</td>
            <td>$5.00</td>
            <td>No</td>
            <td><button class="remove-button">Remove</button></td>
        </tr>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/views/transaction/CustomerTransactionConfirmView.jsp">
    <button class="confirm-button">Confirm</button></a>

</body>
</html>
