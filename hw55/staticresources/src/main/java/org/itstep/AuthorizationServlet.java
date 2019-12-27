package org.itstep;

import org.itstep.dao.UserDao;
import org.itstep.domain.BuilderUser;
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
import java.util.Optional;

@WebServlet(name = "authorization", urlPatterns = "/authorization")
public class AuthorizationServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        PrintWriter printWriter = resp.getWriter();
        final String LOGIN = req.getParameter("login");
        final String PASSWORD = req.getParameter("password");

        try {
            if (userDao != null) {    //проверяю, что пользователь есть в базе

                Optional<User> user = userDao.findAll().stream()
                        .filter(user1 -> LOGIN.equals(user1.getLogin()) && PASSWORD.equals(user1.getPassword()))
                        .findFirst();

                if (user.isPresent()) {

                    HttpSession session = req.getSession();  //запоминаю пользователя
                    User userIn = (User) session.getAttribute("user");

                    if (userIn == null) {
                        userIn = new BuilderUser().setID(user.get().getId()).setLogin(user.get().getLogin())
                                .setCoins(user.get().getCoins()).build();

                        session.setAttribute("user", userIn);
                        session.setMaxInactiveInterval(-1);
                    }
                    resp.sendRedirect(req.getContextPath() + "/user");  //перенаправляю на сайт
                } else {
                    printWriter.println(PageManipulation.redirect("Wrong login or password", req.getContextPath()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            printWriter.println(PageManipulation.redirect("Authorization failed", req.getContextPath()));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/authorization.jsp").forward(req, resp);
    }
}

