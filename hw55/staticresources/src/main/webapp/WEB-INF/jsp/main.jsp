<%@ page import="org.itstep.domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String login = "";
    int coins = 0;
    User user = (User) session.getAttribute("user");
    if (user != null) {
        login = user.getLogin();
        coins = user.getCoins() + 1;
    }
%>

<html lang="en">
<head>
    <title>Main</title>
    <link href="<%=request.getContextPath()%>/static/css/style.css" rel="stylesheet"/>
</head>
<body>
<h2>Hello, <%=login%>!</h2>
<aside>
    <span><%=coins%></span>
    <img src="<%=request.getContextPath()%>/static/images/coins.png" alt="coins">
</aside>
<form id="mainForm" action="<%=request.getContextPath()%>/user" method="post">
    <input id="subMain" class="border" type="submit" value="Delete account">
</form>
<a id="exit" class="border" href="http://localhost:8080<%=request.getContextPath()%>">EXIT</a>
</body>
</html>
