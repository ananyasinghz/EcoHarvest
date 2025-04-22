<!DOCTYPE html>
<html>
<head>
    <title>Food Waste Reduction Platform</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./style/style.css" type="text/css" rel="stylesheet">
</head>

<body class = "home-page">
<div class="logo-container">
    <img src="image/logo.png" alt="Logo">
</div>
<h1>EcoHarvest</h1>
<h2>Food Waste Reduction Platform</h2>
<div class="content-container">
    <p>Welcome to EcoHarvest, your sustainable solution to reducing food waste and promoting responsible consumption. Our platform connects retailers, charities, and consumers in a collaborative effort to minimize surplus food and ensure that it is redirected to those in need or utilized efficiently.</p>
</div>
<h3>Lets get started!</h3>
<br>
<br>

<form action="${pageContext.request.contextPath}/WelcomeServlet" method="POST">
    <input type="submit" name="action" value="Log In">
</form>
<br>
<form action="${pageContext.request.contextPath}/WelcomeServlet" method="POST">
    <input type="submit" name="action" value="Sign Up">
</form>

    <footer>
        <p>&copy; 2024 EcoHarvest. All rights reserved. Made by Ananya, Aditi and Palak</p>
    </footer>

</body>
</html>