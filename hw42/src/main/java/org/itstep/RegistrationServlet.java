package org.itstep;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

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
        RegisteredUsers registeredUsers = RegisteredUsers.getInstance();

        ServletContext servletContext = getServletContext();
        File file = new File(servletContext.getRealPath("/WEB-INF") + "/registration.txt");
        if (!file.exists()) {//
            file.createNewFile();
        }

        List<User> users = registeredUsers.list(file);
        registeredUsers.add(user);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(users);
        }

        PrintWriter writer = resp.getWriter();
        writer.println("Hello, " + user.getLogin());
    }
}
