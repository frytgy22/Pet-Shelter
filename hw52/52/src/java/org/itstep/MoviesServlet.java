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
        name = "movie",
        urlPatterns = "/movie"
)
@WebFilter(filterName = "FilterCharset", urlPatterns = {"/*"})
public class MoviesServlet extends HttpServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {//длинный, но единственный способ который нашла, чтоб вывести русский текст
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connString = "jdbc:mysql://localhost/MovieBase?serverTimezone=Europe/Kiev&characterEncoding=utf8";
            String user = "root";
            String password = "";

            try (Connection connection = DriverManager.getConnection(connString, user, password)) {
                Statement statementInsert = connection.createStatement();
                statementInsert.executeUpdate("INSERT INTO Directors ( FirstName, LastName, Nationality, Birth) VALUES\n" +
                        "('Дэвид', 'Литч', 'EU', '1986-06-02')");

                statementInsert.executeUpdate("INSERT INTO Movies (DirectorId, Title, ReleaseYear, Rating, Plot, MovieLength) VALUES\n" +
                        "(6, 'Дэдпул 2', 2018, 4, 'Продолжение невероятных приключений болтливого наёмника, который после" +
                        " расправы над своим врагом решает заняться истреблением плохишей по всему миру.', 170)");

                statementInsert.executeUpdate("INSERT INTO Actors ( FirstName, LastName, Nationality, Birth) VALUES\n" +
                        "('Райан', 'Рейнольдс', 'EU', '1988-11-01')");

                statementInsert.executeUpdate("INSERT INTO MovieActor (MovieId, ActorId) VALUES (11, 9)");

                statementInsert.executeUpdate("INSERT INTO MovieGenres (MovieId, GenreId) VALUES(11, 3),(11, 1)");

                //1. Выбрать все фильмы и отсортировать по названию

                PrintWriter writer = resp.getWriter();
                writer.println("<!DOCTYPE html>\n" +
                        "<html lang=\"ru\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"utf-8\">\n" +
                        "    <title>Admin</title>\n" +
                        "<style>\n" +
                        "        table {\n" +
                        "            border: 1px solid;\n" +
                        "            border-collapse: collapse;\n" +
                        "        }\n" +
                        "        th {\n" +
                        "            border: 1px solid;\n" +
                        "        }\n" +
                        "        td {\n" +
                        "            border: 1px solid;\n" +
                        "        }\n" +
                        "    </style>" +
                        "</head>\n" +
                        "<body>");

                Statement statement1 = connection.createStatement();
                ResultSet resultSet1 = statement1.executeQuery("SELECT Movies.Title FROM Movies ORDER BY Movies.Title;");
                writer.println("<p></p><table>" +
                        "<thead >\n" +
                        "        <tr>\n" +
                        "            <th>Title</th>\n" +
                        "         </tr></thead>");
                writer.println("<tbody>\n" + "<tr>\n");
                while (resultSet1.next()) {
                    writer.println("<td>" + resultSet1.getString("Movies.Title") + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>");

                // 2. Выбрать все фильмы и отсортировать по году выпуска

                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery("SELECT Movies.Title, Movies.ReleaseYear FROM Movies ORDER BY Movies.ReleaseYear");
                writer.println("<p></p><table>" +
                        "<thead >\n" +
                        "        <tr>\n" +
                        "            <th>Title</th>\n" +
                        "            <th>Year</th>\n" +
                        "         </tr></thead>");
                writer.println("<tbody>\n" + "<tr>\n");
                while (resultSet2.next()) {
                    writer.println("<td>" + resultSet2.getString("Movies.Title") + "</td>\n" +
                            "<td>" + resultSet2.getInt("Movies.ReleaseYear") + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>");

                // 3. Выбрать все фильмы и отсортировать по рейтингу

                Statement statement3 = connection.createStatement();
                ResultSet resultSet3 = statement3.executeQuery("SELECT Movies.Title, Movies.Rating FROM Movies ORDER BY Movies.Rating DESC");
                writer.println("<p> </p><table>" +
                        "<thead >\n" +
                        "        <tr>\n" +
                        "            <th>Title</th>\n" +
                        "            <th>Rating</th>\n" +
                        "         </tr></thead>");
                writer.println("<tbody>\n" + "<tr>\n");

                while (resultSet3.next()) {
                    writer.println("<td>" + resultSet3.getString("Movies.Title") + "</td>\n" +
                            "<td>" + resultSet3.getInt("Movies.Rating") + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>");

                // 4. Выбрать все фильмы, выпущенные в прошлом году

                Statement statement4 = connection.createStatement();
                ResultSet resultSet4 = statement4.executeQuery("SELECT Movies.Title, Movies.ReleaseYear FROM Movies WHERE Movies.ReleaseYear = YEAR(CURDATE()) - 1");
                writer.println("<p> </p><table>" +
                        "<thead >\n" +
                        "        <tr>\n" +
                        "            <th>Title</th>\n" +
                        "            <th>Year</th>\n" +
                        "         </tr></thead>");
                writer.println("<tbody>\n" + "<tr>\n");
                while (resultSet4.next()) {
                    writer.println("<td>" + resultSet4.getString("Movies.Title") + "</td>\n" +
                            "<td>" + resultSet4.getInt("Movies.ReleaseYear") + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>");

                // 5. Выбрать все фильмы по заданному жанру

                PreparedStatement preparedStatement5 = connection.prepareStatement("SELECT Movies.Title, Genres.GenreName FROM MovieGenres\n" +
                        "NATURAL JOIN Movies\n" + "NATURAL JOIN Genres\n" + "WHERE Genres.GenreName = ? ");
                preparedStatement5.setString(1, "Comedy");
                ResultSet resultSet5 = preparedStatement5.executeQuery();
                writer.println("<p> </p><table>" +
                        "<thead >\n" +
                        "        <tr>\n" +
                        "            <th>Title</th>\n" +
                        "            <th>Genre</th>\n" +
                        "         </tr></thead>");
                writer.println("<tbody>\n" + "<tr>\n");

                while (resultSet5.next()) {
                    writer.println("<td>" + resultSet5.getString("Movies.Title") + "</td>\n" +
                            "<td>" + resultSet5.getString("Genres.GenreName") + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>");

                // 6. Выбрать все фильмы по заданному актеру

                PreparedStatement preparedStatement6 = connection.prepareStatement("SELECT Movies.Title, Actors.FirstName, Actors.LastName FROM MovieActor \n" +
                        "NATURAL JOIN Movies \n" + "NATURAL JOIN Actors \n" + "WHERE Actors.LastName = ? ");
                preparedStatement6.setString(1, "Калкин");
                ResultSet resultSet6 = preparedStatement6.executeQuery();
                writer.println("<p> </p><table>" +
                        "<thead >\n" +
                        "        <tr>\n" +
                        "            <th>Title</th>\n" +
                        "            <th>Actor first name</th>\n" +
                        "            <th>/last name</th>\n" +
                        "         </tr></thead>");
                writer.println("<tbody>\n" + "<tr>\n");

                while (resultSet6.next()) {
                    writer.println("<td>" + resultSet6.getString("Movies.Title") + "</td>\n" +
                            "<td>" + resultSet6.getString("Actors.FirstName") + "</td>\n" +
                            "<td>" + resultSet6.getString("Actors.LastName") + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>");

                // 7. Выбрать все фильмы по заданному продюсеру

                PreparedStatement preparedStatement7 = connection.prepareStatement("SELECT Movies.Title" +
                        ",Directors.FirstName, Directors.LastName FROM Movies NATURAL JOIN Directors \n" +
                        "WHERE Directors.FirstName = ? ");
                preparedStatement7.setString(1, "Шон");
                ResultSet resultSet7 = preparedStatement7.executeQuery();
                writer.println("<p> </p><table>" +
                        "<thead >\n" +
                        "        <tr>\n" +
                        "            <th>Title</th>\n" +
                        "            <th>Director first name</th>\n" +
                        "            <th>/last name</th>\n" +
                        "         </tr></thead>");
                writer.println("<tbody>\n" + "<tr>\n");

                while (resultSet7.next()) {
                    writer.println("<td>" + resultSet7.getString("Movies.Title") + "</td>\n" +
                            "<td>" + resultSet7.getString("Directors.FirstName") + "</td>\n" +
                            "<td>" + resultSet7.getString("Directors.LastName") + "</td>\n" +
                            "</tr>\n");
                }
                writer.println("</tbody>" +
                        "</table>" +
                        "</body>\n" +
                        "</html>");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}