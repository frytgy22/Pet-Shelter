package org.itstep;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String gender = req.getParameter("gender");
        final String phone = req.getParameter("phone");
        final String email = req.getParameter("email");
        final String sub = req.getParameter("sub");

        User user = new User(login, password, gender, phone, email, sub);

        PrintWriter writer = resp.getWriter();
        writer.println("Hello, " + user);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String connectionString = "jdbc:mysql://localhost/userBase?serverTimezone=Europe/Kiev&characterEncoding=utf8";
        String rootUser = "root";
        String rootPassword = "";

        try (Connection connection = DriverManager.getConnection(connectionString, rootUser, rootPassword)) {
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(id int auto_increment primary key," +
                    "login varchar(30) not null," +
                    "password varchar(16) not null," +
                    "gender varchar(6) not null," +
                    "phone varchar(16) not null," +
                    "email varchar(30) not null," +
                    "subscribe varchar(2) )";
            statement.executeUpdate(sql);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(login, password, gender, phone, email, subscribe) values(?, ?, ?, ?, ?, ?)")) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, gender);
                preparedStatement.setString(4, phone);
                preparedStatement.setString(5, email);
                preparedStatement.setString(6, sub);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

