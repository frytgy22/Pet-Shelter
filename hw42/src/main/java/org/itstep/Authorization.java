package org.itstep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class Authorization extends HttpServlet {
    public static final String LOGIN = "admin";
    public static final String PASSWORD = "qwerty";

    //    http://localhost:8080/user?login=admin&password=qwerty
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RegisteredUsers registeredUsers = RegisteredUsers.getInstance();
        List<User> users = registeredUsers.list(new File("/home/valeria/Desktop/registration.txt"));

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
            if (users != null && !users.isEmpty()) {
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
                int numberUser = 1;
                for (User user : users) {
                    if (user.getSubscribe() == null) {
                        user.setSubscribe("-");
                    }
                    writer.println("<tbody>\n" +
                            "<tr>\n");
                    writer.println("<td>" + (numberUser++) + "</td>\n" +
                            "<td>" + user.getLogin() + "</td>\n" +
                            "<td>" + user.getPassword() + "</td>\n" +
                            "<td>" + user.getGender() + "</td>\n" +
                            "<td >" + user.getPhone() + "</td>\n" +
                            "<td>" + user.getEmail() + "</td>\n" +
                            "<td>" + user.getSubscribe() + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>" +
                        "</body>\n" +
                        "</html>");
            } else {
                writer.println("<h2>No registered users yet!</h2>");
            }
        } else {
            resp.setStatus(401);
            writer.println("<h1 style=\"color: red\">Wrong login or password!</h1>" +
                    "<img  src=\"./images/1.png\" alt=\"logotype java\"/>\n");
        }
    }

    private boolean validData(String login, String password) {
        return LOGIN.equals(login) && PASSWORD.equals(password);
    }
}
