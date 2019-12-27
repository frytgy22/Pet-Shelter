package org.itstep;

import org.itstep.dao.UserDao;
import org.itstep.domain.BuilderUser;
import org.itstep.domain.User;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class HomeServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String user = getServletContext().getInitParameter("db.username");
        String pass = getServletContext().getInitParameter("db.password");
        String url = getServletContext().getInitParameter("db.url");
        try {
            userDao = new UserDao(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override //TODO if I close this resource, I have an exception IllegalStateException: Illegal access
//    public void destroy() {
//        super.destroy();
//        try {
//            userDao.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); //удаляю предыдущую сессию
        if (!session.isNew()) {
            session.invalidate();
        }
        req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String LOGIN = req.getParameter("login");
        final String PASSWORD = req.getParameter("password");
        PrintWriter printWriter = resp.getWriter();

        User user = new BuilderUser().setLogin(LOGIN).setPassword(PASSWORD).build();
        try {
            if (userDao != null) {

                boolean userInBase = userDao.findAll().stream()   //проверяю, что пользователя нет в бд
                        .anyMatch(user1 -> LOGIN.equals(user1.getLogin()));

                if (!userInBase) {
                    userDao.save(user);  //save in db

                    printWriter.println(PageManipulation.redirect("Registration completed successfully",
                            req.getContextPath() + "/authorization")); //отправляю на авторизацию
                } else {
                    printWriter.println(PageManipulation.redirect("User already exists", req.getContextPath()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            printWriter.println(PageManipulation.redirect("Registration failed", req.getContextPath()));
        }
    }
}
