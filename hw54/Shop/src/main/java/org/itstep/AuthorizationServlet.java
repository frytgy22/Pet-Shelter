package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name = "authorisation",
        urlPatterns = "/authorization",
        initParams = {
                @WebInitParam(name = "db:url", value = "jdbc:mysql://localhost/shop?serverTimezone=Europe/Kiev&characterEncoding=utf8"),
                @WebInitParam(name = "db:user", value = "root"),
                @WebInitParam(name = "db:pass", value = "")
        }
)
public class AuthorizationServlet extends HttpServlet {
    JDBCConnectionPool jdbcConnectionPool;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jdbcConnectionPool = new JDBCConnectionPool(
                "com.mysql.cj.jdbc.Driver", config.getInitParameter("db:url"),
                config.getInitParameter("db:user"), getInitParameter("db:pass"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String LOGIN = req.getParameter("login");
        final String PASSWORD = req.getParameter("password");

        PrintWriter printWriter = resp.getWriter();
        Connection conn = jdbcConnectionPool.checkOut();

        boolean customerInBase = false; //проверяю по email, password что пользователь зарегистрирован
        try {
            customerInBase = SqlQuery.existInBaseAND(conn, "password", LOGIN, PASSWORD);
            if (customerInBase) { //если есть в базе, перенаправляю в магазин

                Cookie cookie = new Cookie("login", LOGIN);//запоминаю логин
                cookie.setMaxAge(24 * 60 * 60);//длина сессии
                resp.addCookie(cookie);

                resp.sendRedirect("/shop");
            } else {
                printWriter.println("<img  src=\"../images/4.jpg\"/>" + "Wrong login or password!" +
                        "<script>" +
                        "setTimeout(() => window.location = 'http://localhost:8080', 3000);" +
                        "</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            printWriter.println("<img src=\"../images/4.jpg\"/>");
        }
        jdbcConnectionPool.checkIn(conn);
    }
}
