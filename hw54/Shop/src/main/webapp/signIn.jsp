<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>SignIn</title>
    <link rel="stylesheet" href="css/index.css">
</head>

<body>
    <div class="parent">
        <div class="block">
            <form action="authorization" method="post">
                <div id="line" class="box effect">
                    <img src="images/4.png" alt="icon">
                    <h3>Authorization</h3>
                </div>
                <label><input name="login" required autofocus placeholder="Login" /></label><br>
                <label><input type="password" required name="password" placeholder="Password" /></label><br>
                <label class="flex"><input id="submit" type="submit" value="SIGN IN" /></label>
                <a href="http://localhost:8080">Forgot the password?</a>
            </form>
        </div>
    </div>
</body>

</html>