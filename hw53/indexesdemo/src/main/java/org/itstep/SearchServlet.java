package org.itstep;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name = "search",
        urlPatterns = "/search"
)
public class SearchServlet extends HttpServlet {
    String url;
    String user;
    String password;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        url = getServletContext().getInitParameter("db:url");
        user = getServletContext().getInitParameter("db:user");
        password = getServletContext().getInitParameter("db:pass");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String FIRST_NAME = req.getParameter("firstName");
        final String LAST_NAME = req.getParameter("lastName");
        final String SELECT = "SELECT COUNT(*) FROM person WHERE first_name = ? AND last_name = ?";
        int count = 0;
        PrintWriter printWriter = resp.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                Statement statement = connection.createStatement();
               // statement.executeUpdate("CREATE INDEX person_first_name_idx ON person (first_name)");

                PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
                preparedStatement.setString(1, FIRST_NAME);
                preparedStatement.setString(2, LAST_NAME);

                long start = System.currentTimeMillis();
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    count = resultSet.getInt("COUNT(*)");
                }
                long end = System.currentTimeMillis() - start;

                printWriter.println(FIRST_NAME + " " + LAST_NAME + " = " + count);
                printWriter.println("Time: " + end + "ms");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
