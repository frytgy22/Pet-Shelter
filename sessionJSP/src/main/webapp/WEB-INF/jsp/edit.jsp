<%@ page import="org.lebedeva.Check" %>
<%@ page import="org.lebedeva.model.CdDisk" %>
<%@ page import="org.lebedeva.model.Archive" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="template/head.jsp" %>

<%
    if (session.getAttribute("Archive") != null && Check.checkParameter(request.getParameter("id")) &&
            Check.checkParameter(request.getParameter("name")) && Check.checkParameter(request.getParameter("singer"))) {

        Archive archive = (Archive) session.getAttribute("Archive");

        try {
            Integer id = Integer.parseInt(request.getParameter("id").trim());
            CdDisk cdDisk = Check.getCdDisk(archive, id);

            if (cdDisk != null) {
                archive.getCdDiskList().remove(cdDisk);
                archive.getCdDiskList().add(new CdDisk(id, request.getParameter("name").trim(), request.getParameter("singer").trim()));
            }

            response.sendRedirect(request.getContextPath() + "/index");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write("Wrong data.");
        }
    } else {
        response.getWriter().write("Nothing to edit.");
    }
%>

</body>
</html>
