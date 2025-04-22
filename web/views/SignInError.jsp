<%--
Author     : ananya
  Date: 2024-04-03
  Time: 8:52 p.m.
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Sign In</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet">
    <style>
        .error {
            color: red;
        }
    </style>

</head>
<body class = "signin">
        <div class="logoblack-container">
            <a href="${pageContext.request.contextPath}">
            <img src="${pageContext.request.contextPath}/image/logo_black.png" alt="Logo"></a>
        </div>
<div class="container">
    <div class="content">
        <h1>Sign In</h1>
        <h4>Please enter your credentials</h4>
        <form action="${pageContext.request.contextPath}/LoginServlet" method="POST" class="signin-form">
            <p>User :</p>
            <input type="text" name="name" required><br>
            <p>Password :</p>
            <input type="password" name="password" required><br>
            <input type="submit" name="action" value="Sign in">
            <input type="submit" name="action" value="Sign up">
        </form>


    </div>
        <p class="error">The information entered was not correct. If you are not register yet, please sign up.</p>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="POST"></form>
</div>
</body>
</html>