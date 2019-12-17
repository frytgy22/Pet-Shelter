package org.itstep;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(
        name = "registration",
        urlPatterns = "/registration"
)
@WebFilter(filterName = "FilterCharset", urlPatterns = {"/*"})
public class QueryServlet extends HttpServlet implements Filter {

    //длинный, но единственный способ который нашла, чтоб вывести русский текст
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String QUERY = req.getParameter("query").trim();
        PrintWriter writer = resp.getWriter();

        String[] movies = {"Movies.MovieId", " Movies.DirectorId", "Movies.Title", "Movies.ReleaseYear", "Movies.Rating", "Movies.Plot", "Movies.MovieLength"};
        String[] directors = {"Directors.DirectorId", "Directors.FirstName", "Directors.LastName", "Directors.Nationality", "Directors.Birth"};
        String[] actors = {"Actors.DirectorId", "Actors.FirstName", "Actors.LastName", "Actors.Nationality", "Actors.Birth"};
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connString = "jdbc:mysql://localhost/MovieBase?serverTimezone=Europe/Kiev&characterEncoding=utf8";
            String user = "root";
            String password = "";

            try (Connection connection = DriverManager.getConnection(connString, user, password)) {
                Statement statement = connection.createStatement();

                writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>MySql</title>\n" +
                        "<link rel=\"stylesheet\" href=\"../css/index.css\">" +
                        "</head>\n" +
                        "<body>");

                if (QUERY.length() > 12 && "select".equalsIgnoreCase(QUERY.substring(0, 6)) && QUERY.toUpperCase().contains(" FROM ")) {
                    ResultSet resultSet = statement.executeQuery(QUERY);
                    writer.println("<table><thead><tr>\n");

                    String getNameQuery = QUERY.substring(6, QUERY.toUpperCase().indexOf("FROM"));//получаю строку после SELECT до FROM
                    String[] createTable = getNameQuery.trim().split(",");

                    if (getNameQuery.contains("*")) {
                        if (QUERY.contains(" Directors ")) {
                            createTable = directors;
                        } else if (QUERY.contains(" Actors ")) {
                            createTable = actors;
                        } else if (QUERY.contains(" Movies ")) {
                            createTable = movies;
                        }
                    }
                    for (String s : createTable) {
                        writer.println("<th>" + s.trim() + "</th>\n");
                    }

                    writer.println("</tr>" +
                            "</thead><tbody>\n" +
                            "<tr>\n");

                    while (resultSet.next()) {
                        for (String s : createTable) {
                            writer.println("<td>" + resultSet.getString(s.trim()) + "</td>\n");
                        }
                        writer.println("</tr>");
                    }
                    writer.println("</tbody></table>");
                } else {
                    if (!statement.execute(QUERY)) {
                        writer.println("Done.");
                    }
                }
                writer.println("</body></html>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
