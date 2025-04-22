<%-- 
    Document   : Charity_registration
    Created on : Mar 27, 2025, 8:11:30 PM
    Author     : ananya
--%>

<%@ page import="dataaccesslayer.DataSource" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.ServletContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // Instantiate DataSource
    ServletContext context = pageContext.getServletContext();
    DataSource dataSource = new DataSource(context);

// Establish database connection
    Connection connection = null;
    try {
        connection = dataSource.getConnection();

        // Query database
        String query = "SELECT location FROM users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Registration Form</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
</head>
<body class = "registration">

<div class="logoblack-container">
    <a href="${pageContext.request.contextPath}">
    <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
</div>

<div class="container">
<h1>Customer Registration Form</h1>
<form action="${pageContext.request.contextPath}/ValidateRegistrationServlet" method="post">
<div class="hidden">
    <label for="role" hidden>Role :</label>
    <input type="text" id="role" value="Customer" hidden>
</div>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    <br>
    <label for="name">*Name :</label><br>
    <input type="text" id="name" name="name" required><br>
    <label for="email">*Email :</label><br>
    <input type="email" id="email" name="email" required><br>
    <label for="password">*Password :</label><br>
    <input type="password" id="password" name="password" required><br>
    <label for="phone">*Phone :</label><br>
    <input type="text" id="phone" name="phone" required><br>
    <label for="dropdown">Select your nearest store:</label>
    <br>
    <select id="dropdown" name="selectedValue">
        <%
            // Populate the dropdown
            while (resultSet.next()) {
                String value = resultSet.getString("location");
        %>
        <option value="<%= value %>"><%= value %></option>
        <%
            }
        %>
    </select><br></br>
    <input type="checkbox" id="subscribeToPhone" name="subscribeToPhone">
    <label for="subscribeToPhone">Get notifications by Phone</label><br>
    <input type="checkbox" id="subscribeToMail" name="subscribeToMail">
    <label for="subscribeToMail">Get notifications by Mail</label><br>
    <br>
    <div style="text-align: right; background-color: white;">
    <input type="submit" value="Register">
    </div>
</form>
</div>
</body>
</html>
<%
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close database connection
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
%>