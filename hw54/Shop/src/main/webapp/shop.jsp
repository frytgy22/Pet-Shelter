<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: valeria
  Date: 20.12.2019
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cookie[] cookies = request.getCookies();
    PrintWriter printWriter = response.getWriter();

    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("login")) {
            String cookieValue = cookie.getValue();
            printWriter.println("<script>" +
                    "window.addEventListener('load', () => {" +
                    "document.getElementById(\"p\").appendChild(document.createTextNode(" + cookieValue + "));});" +
                    "</script>");
            break;
        }
    }
%>

<html>
<head>
    <title>Shop</title>
    <link rel="stylesheet" href="css/shop.css">
    <script src="js/index.js"></script>
</head>
<body>
<aside>
    <h4></h4>
    <h1>SUSHI SHOP</h1>
</aside>
<div id="icon">
    <img class="inBlock" src="images/4.png" alt="icon">
    <p id="p" class="inBlock"></p>
</div>
<div id="basket">
    <img class="inBlock" src="images/11.png" alt="icon">
    <div class="inBlock circle"><p class="inBlock" id="count">0</p></div>
</div>
<main>
    <ul>
        <li>
            <p>Зеленый дракон</p><span>Ролл-дракон с лососем и угрем панко, сливочным сыром, икрой тобико, авокадо, кунжутом и соусом унаги.</span>
        </li>
        <li>
            <p>Филадельфия макси</p><span>Ролл с лососем и сливочным сыром.</span></li>
        <li>
            <p>Калифорния сякэ</p><span>Ролл с лососем, авокадо, огурцом в икре тобико и с соусом спайси.</span></li>
        <li>
            <p>Красный дракон</p><span>Ролл-дракон с лососем, сурими кимчи, сливочным сыром, икрой тобико, манго и омлетом тамаго.</span>
        </li>
        <li>
            <p>Феликс ролл с лососем</p><span>Ролл с лососем, сливочным сыром, авокадо, огурцом с икрой тобико и соусом Spicy.</span>
        </li>
        <li>
            <p>Парадайз</p><span>Оригинальный ролл с креветкой панко, лососем кимчи, икрой тобико, авокадо, сладким перцем, огурцом, салат ромэн, соусом спайси и стружкой тунца.</span>
        </li>
        <li>
            <p>Филадельфия спайси-кунцей</p><span>Ролл с масляной, сливочным сыром, икрой тобико, сладким перцем, огурцом и соусом спайси.</span>
        </li>
        <li>
            <p>Токио</p><span>Оригинальный ролл с креветкой, лососем и угрем панко, икрой тобико, сливочным сыром, манго, авокадо и соусом манго-Чили.</span>
        </li>
        <li>
            <p>Унаги-кунцей филадельфия</p><span>Ролл с угрем, масляной, сливочным сыром, огурцом, салатом ромен в икре и кунжуте.</span>
        </li>
        <li>
            <p>Лосось тай</p><span>Оригинальный ролл с карамельным лососем, угрем панко, сурими кимчи, икрой тобико, сливочным сыром, манго и соусом манго-Чили.</span>
        </li>
    </ul>
</main>
<footer><p>Copyright &copy; SUSHI SHOP 2020</p></footer>
</body>
</html>
