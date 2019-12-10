package org.itstep;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;


public class Authorization extends HttpServlet {
    public static final String LOGIN = "admin";
    public static final String PASSWORD = "qwerty";

    //    http://localhost:8080/user?login=admin&password=qwerty
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        ServletContext servletContext = getServletContext();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String connString = "jdbc:mysql://localhost/userBase?serverTimezone=Europe/Kiev&characterEncoding=utf8";
        String user1 = "root";
        String password1 = "";
        try (Connection connection = DriverManager.getConnection(connString, user1, password1)) {
            Statement stmt = connection.createStatement();

            PrintWriter writer = resp.getWriter();
            if (validData(login, password)) {
                writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Admin</title>\n" +
                        "<link rel=\"stylesheet\" href=\"../css/index.css\">" +
                        "</head>\n" +
                        "<body>");
                writer.println("<h2>Welcome, " + login + "!</h2>");

                ResultSet resultSet = stmt.executeQuery("SELECT * from users");
                    writer.println("<table>" +
                            " <colgroup>\n" +
                            "  <col style=\"width: 50px;\"/>\n" +
                            "    </colgroup>" +
                            "<thead >\n" +
                            "        <tr>\n" +
                            "            <th>&#x2116</th>\n" +
                            "            <th>Login</th>\n" +
                            "            <th>Password</th>\n" +
                            "            <th>Gender</th>\n" +
                            "            <th>Phone</th>\n" +
                            "            <th>Email</th>\n" +
                            "            <th>Subscribe</th>\n" +
                            "        </tr>\n" +
                            "    </thead>");

                    while (resultSet.next()) {
                        String sub = resultSet.getString(7);
                        if (sub == null) {
                            sub = "-";
                        }
                        writer.println("<tbody>\n" +
                                "<tr>\n");
                        writer.println("<td>" + resultSet.getString(1) + "</td>\n" +
                                "<td>" + resultSet.getString("login") + "</td>\n" +
                                "<td>" + resultSet.getString("password") + "</td>\n" +
                                "<td>" + resultSet.getString("gender") + "</td>\n" +
                                "<td >" + resultSet.getString("phone") + "</td>\n" +
                                "<td>" + resultSet.getString("email") + "</td>\n" +
                                "<td>" + sub + "</td>\n" +
                                "</tr>\n");
                    }
                    writer.println("</tbody>" +
                            "</table>" +
                            "</body>\n" +
                            "</html>");
            } else {
                resp.setStatus(401);
                StringBuilder webXmlBuilder = new StringBuilder();
                try (InputStream stream = servletContext.getResourceAsStream("/WEB-INF/page401.html");
                     InputStreamReader streamReader = new InputStreamReader(stream);
                     BufferedReader reader = new BufferedReader(streamReader)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        webXmlBuilder.append(line);
                    }
                    writer.println(webXmlBuilder.toString());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private boolean validData(String login, String password) {
        return LOGIN.equals(login) && PASSWORD.equals(password);
    }
}
