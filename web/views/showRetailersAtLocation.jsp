<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Retailers at Location</title>
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
        <li><a href="${pageContext.request.contextPath}/views/CustomerView.jsp">Back to Profile</a></li>
    </ul>

    <!-- Balance line uses currentBal from request scope -->
    <p class="balance">
      Balance : <span class="span">$${currentBal}</span>
    </p>
    
    <div class="container">
        <h1>Retailers at ${param.location}</h1>
        
        <c:if test="${empty retailerNames}">
            <p>No retailers found at this location.</p>
        </c:if>
        
        <c:if test="${not empty retailerNames}">
            <table border="1" width="100%">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="retailer" items="${retailerNames}">
                        <tr>
                            <td>${retailer.name}</td>
                            <td>${retailer.location}</td>
                            <td>
                                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                                <c:url var="invUrl" value="/FetchInventoryServlet"/>
                                <form action="${invUrl}" method="post">
                                    <input type="hidden" name="retailerId" value="${retailer.id}"/>
                                    <input type="hidden" name="currentBal" value="${currentBal}"/>
                                    <button type="submit">View Items</button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
