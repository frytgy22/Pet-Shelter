<%@ page import="org.lebedeva.Check" %>
<%@ page import="org.lebedeva.model.CdDisk" %>
<%@ page import="org.lebedeva.model.CdDisk" %>
<%@ page import="org.lebedeva.model.Archive" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    if (Check.checkParameter(request.getParameter("id")) && session.getAttribute("Archive") != null) {
        Archive archive = (Archive) session.getAttribute("Archive");
        try {
            Integer id = Integer.parseInt(request.getParameter("id").trim());
            CdDisk cdDisk = Check.getCdDisk(archive, id);

            if (cdDisk != null) {
                archive.getCdDiskList().remove(cdDisk);
            }

            response.sendRedirect(request.getContextPath() + "/index");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write("Wrong data.");
        }
    } else {
        response.getWriter().write("Nothing to delete.");
    }
%>

</body>
</html>
