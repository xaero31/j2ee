package ru.gb.j2ee.servlet.security;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for login form
 */
@WebServlet(name = "login", urlPatterns = "/login_process")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        if (login.equals("admin") && !password.equals("admin_password")) {
            req.setAttribute("WrongPass", true);
            req.getRequestDispatcher("/views/jsp/main.jsp").forward(req, resp);
            return;
        }

        req.getSession().setAttribute("user", login);
        resp.sendRedirect(req.getContextPath());
    }
}
