package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


@WebServlet(
        name = "registration",
        urlPatterns = "/registration",
        initParams = {
                @WebInitParam(name = "db:url", value = "jdbc:mysql://localhost/shop?serverTimezone=Europe/Kiev&characterEncoding=utf8"),
                @WebInitParam(name = "db:user", value = "root"),
                @WebInitParam(name = "db:pass", value = "")
        }
)
public class RegistrationServlet extends HttpServlet {
    JDBCConnectionPool jdbcConnectionPool; //class extends ObjectPool

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        jdbcConnectionPool = new JDBCConnectionPool(
                "com.mysql.cj.jdbc.Driver", config.getInitParameter("db:url"),
                config.getInitParameter("db:user"), getInitParameter("db:pass"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        final String EMAIL = req.getParameter("email");
        final String LOGIN = req.getParameter("login");
        final String PASSWORD = req.getParameter("password");
        final String PHONE = req.getParameter("phone");
        final String DATE_OF_BIRTH = req.getParameter("date");
        final int GENDER = ("male".equalsIgnoreCase(req.getParameter("gender"))) ? 1 : 2;
        final String INSERT = "INSERT INTO customers(email,login,password,phone,dateOfBirth,genderID) VALUES (?,?,?,?,?,?)";

        String error = PageError.showPage404(servletContext, "page404.html");
        PrintWriter printWriter = resp.getWriter();
        List<Object> customer = Arrays.asList(EMAIL, LOGIN, PASSWORD, PHONE, DATE_OF_BIRTH, GENDER);

        Connection conn = jdbcConnectionPool.checkOut();
        try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT)) {

            boolean customerInBase = SqlQuery.existInBaseOR(conn, "email", LOGIN, EMAIL); //проверяю по email, login что пользователь не зарегистрирован

            if (customerInBase) {//если есть в базе, перенаправляю на авторизацию
                printWriter.println(error + "Customer already  exists!" +
                        "<script>" +
                        "setTimeout(() => window.location = 'http://localhost:8080/signIn', 3000);" +
                        "</script>");
            } else {
                int countValues = preparedStatement.getParameterMetaData().getParameterCount();
                for (int i = 0; i < countValues; i++) { // если нет, запись в базу и перенаправляю на авторизацию
                    preparedStatement.setObject(i + 1, customer.get(i));
                }
                preparedStatement.execute();
                resp.sendRedirect("/signIn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            printWriter.println(error);
        }
        jdbcConnectionPool.checkIn(conn);
    }
}



