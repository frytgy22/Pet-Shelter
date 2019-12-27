<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>Registration</title>
    <link href="<%=request.getContextPath()%>/static/css/style.css" rel="stylesheet"/>
</head>
<body>
<div class="parent">
    <div class="block">
        <form action="<%=request.getContextPath()%>/" method="post">
            <h4>Sign up</h4>
            <hr>
            <div>
                <label for="login">Login</label>
                <input name="login" id="login" maxlength="20"
                       pattern="[^А-Яа-я\s]+" title="only latin letters, numbers, symbols" required autofocus/>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" name="password" id="password" maxlength="20" required
                       pattern="[^А-Яа-я\s]+" title="only latin letters, numbers, symbols"/>
            </div>
            <footer>
                <input type="submit" value="Sign up"/>
                <a href="http://localhost:8080<%=request.getContextPath()%>/authorization">Already have an account</a>
            </footer>
        </form>
    </div>
</div>
</body>
</html>


