<!DOCTYPE html>
<html>
<head>
    <title>Ending page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
        <style>
            .thankyou h3 {
                letter-spacing: 2px;
                font-size: 70px;
                font-weight: bold;
            }
            .endpage {
                text-align: center;
            }
        </style>
</head>

<body class = "home-page">
<div class="logo-container">
    <img src="${pageContext.request.contextPath}/image/logo.png" alt="Logo">
</div>
<h1>EcoHarvest</h1>

<div class = "thankyou">
<h3>Thank you !</h3>
</div>

<h2>Every action matters</h2>
<div class="content-container">
<div class="endpage">
    <p>Your shopping choices have made a positive impact on food resource utilization.
    <br>Lets continue working together to protect the environment!</p>
</div>
</div>
<br>
<br>

    <a href="${pageContext.request.contextPath}/views/CharityView.jsp">
        <input type="submit" name="action" value="Keep shopping"></a>

<br>

    <footer>
        <p>&copy; 2024 EcoHarvest. All rights reserved. Made by Mayank, Aaron, Andres, Jaya</p>
    </footer>

</body>
</html>