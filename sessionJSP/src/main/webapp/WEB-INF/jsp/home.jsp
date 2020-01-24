<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="template/head.jsp" %>


<div id="content-wrapper" class="mui--text-center">
    <div class="mui--appbar-height"></div>
    <br>
    <br>
    <div class="mui--text-display3 row wow zoomInDown">CD archive</div>
    <br>
    <br>
    <a href="<%=request.getContextPath()%>/index" class="mui-btn mui-btn--raised">Get started</a>
    <br>
    <br>
    <i class="fa fa-linode fa-5x mui--color-blue mui--text-display3 row wow zoomIn" aria-hidden="true"></i>
</div>
<footer>
    <div class="mui-container mui--text-center mui--text-bottom">
        Made with ♥ by <a href="https://www.muicss.com">MUICSS</a>
    </div>
</footer>
<script src="//cdnjs.cloudflare.com/ajax/libs/wow/1.1.2/wow.min.js"></script>
<script>
    new WOW().init();
</script>
</body>
</html>