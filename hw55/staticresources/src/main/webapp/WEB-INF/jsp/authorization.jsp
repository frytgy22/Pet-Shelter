<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Authorization</title>
    <link href="<%=request.getContextPath()%>/static/css/style.css" rel="stylesheet"/>
</head>
<body>
<div class="parent">
    <div class="block">
        <form action="<%=request.getContextPath()%>/authorization" method="post">
            <h4>Sign in</h4>
            <hr>
            <div>
                <label for="login">Login</label>
                <input name="login" id="login" maxlength="20" required autofocus/>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" maxlength="20" required/>
            </div>
            <footer>
                <input type="submit" value="Sign in"/>
                <a href="http://localhost:8080<%=request.getContextPath()%>">Forgot the password</a>
            </footer>
        </form>
    </div>
</div>
</body>
</html>


