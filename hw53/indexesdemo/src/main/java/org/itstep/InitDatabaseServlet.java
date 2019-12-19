package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class InitDatabaseServlet extends HttpServlet {

    String url;
    String user;
    String password;

    static Random rnd = new Random();
    static String[] firstNames = {"Vasja", "Masha", "Petja", "Roma", "Sima", "Jack", "Mila", "Fred", "Nicola"};

    static String[] lastNames = {"Smith", "Staunton", "Anderson"};

    static String[] genders = {"male", "female", "unknown"};

    public static final String INSERT = "INSERT INTO person(first_name, last_name, gender) VALUES(?, ?, ?)";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        url = getServletContext().getInitParameter("db:url");
        user = getServletContext().getInitParameter("db:user");
        password = getServletContext().getInitParameter("db:pass");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String size = req.getParameter("size");
        int count = 100_000;
        try {
            count = Integer.parseInt(size);
        } catch (Throwable ex) {

        }
        iniDb(count);

        writer.println("Success");
    }

    String generateFirstName() {
        return firstNames[rnd.nextInt(firstNames.length)];
    }

    String generateLastName() {
        return lastNames[rnd.nextInt(lastNames.length)];
    }

    String generateGender() {
        return genders[rnd.nextInt(genders.length)];
    }

    private void iniDb(int count) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT);
            for (int i = 0; i < count; i++) {
                preparedStatement.setString(1, generateFirstName());
                preparedStatement.setString(2, generateLastName());
                preparedStatement.setString(3, generateGender());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
