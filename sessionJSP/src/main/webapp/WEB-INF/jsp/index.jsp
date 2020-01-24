<%@ page import="org.lebedeva.model.CdDisk" %>
<%@ page import="org.lebedeva.model.Archive" %>
<%@ page import="java.util.Collections" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="template/head.jsp" %>
<script src="<%=request.getContextPath()%>/static/js/index.js"></script>

<button id="button" class="mui-btn mui-btn--raised mui-btn--primary mui--pull-right"
        onclick="location.href='<%=request.getContextPath()%>/add'">add new cd
</button>

<table class="mui-table mui-table--bordered padding" id="table">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Singer</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <%
        if (session.getAttribute("Archive") != null) {
            Archive archive = (Archive) session.getAttribute("Archive");
            Collections.sort(archive.getCdDiskList());

            for (CdDisk cdDisk : archive.getCdDiskList()) { %>
    <tr>
        <td><%=cdDisk.getId()%>
        </td>
        <td><%=cdDisk.getName()%>
        </td>
        <td><%=cdDisk.getSinger()%>
        </td>
        <td>
            <button name="edit" class="mui-btn mui-btn--small mui-btn--primary">edit</button>
            <button name="delete" class="mui-btn mui-btn--small mui-btn--danger">delete</button>
        </td>
    </tr>
    <% }
    }%>
    </tbody>
</table>

<form class="mui-form padding" id="edit" action="<%=request.getContextPath()%>/edit" method="post">
    <legend id="info"></legend>
    <input type="hidden" name="id">
    <%@include file="template/form.jsp" %>
    <button type="button" class="mui-btn mui-btn--flat mui-btn--danger"
            onclick="location.href='<%=request.getContextPath()%>/index'">cancel
    </button>
</form>

<form id="delete" action="<%=request.getContextPath()%>/delete" method="post">
    <input type="hidden" name="id">
</form>

</body>
</html>

