<%@ page import="java.util.ArrayList" %>
<%@ page import="org.lebedeva.model.CdDisk" %>
<%@ page import="org.lebedeva.Check" %>
<%@ page import="org.lebedeva.model.Archive" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="template/head.jsp" %>

<form class="mui-form padding" method="post">
    <legend>Add CD</legend>
    <%@include file="template/form.jsp" %>
</form>

<%
    if (session.isNew() || session.getAttribute("Archive") == null) {
        session.setAttribute("Archive", new Archive(new ArrayList<CdDisk>(), 0));
    }
    if ("POST".equals(request.getMethod())) {
        Archive archive = (Archive) session.getAttribute("Archive");

        if (Check.checkParameter(request.getParameter("name")) && Check.checkParameter(request.getParameter("singer"))) {
            archive.setLastId(archive.getLastId() + 1);

            CdDisk cdDisk = new CdDisk(archive.getLastId(), request.getParameter("name").trim(), request.getParameter("singer").trim());
            archive.getCdDiskList().add(cdDisk);
        }
    }
%>

</body>
</html>
