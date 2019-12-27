package org.itstep;

import org.itstep.dao.UserDao;
import org.itstep.domain.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "user", urlPatterns = "/user")

public class UserServlet extends HttpServlet {
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter printWriter = resp.getWriter();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && userDao != null) {
            try {
                userDao.delete(user);    // delete user
                printWriter.println(PageManipulation.redirect("User deleted", req.getContextPath()));
            } catch (SQLException e) {
                e.printStackTrace();
                printWriter.println(PageManipulation.redirect("Delete failed", req.getContextPath()));
            }
        } else {
            printWriter.println(PageManipulation.redirect("Session error", req.getContextPath()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter printWriter = resp.getWriter();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && userDao != null) {
            try {
                userDao.update(user, user.getCoins() + 1);    // добавляю монетку и передаю в бд

            } catch (SQLException e) {
                e.printStackTrace();
            }

            req.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(req, resp);
        } else {
            printWriter.println(PageManipulation.redirect("Session error", req.getContextPath()));
        }
    }
}
