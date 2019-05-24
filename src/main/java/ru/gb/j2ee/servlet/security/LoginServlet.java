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

    private static final String LOGIN = "login";

    private static final String PASSWORD = "password";

    private static final String ADMIN = "admin";

    private static final String USER = "user";

    private static final String MAIN = "/views/jsp/main.jsp";

    private static final String WRONG_PASSWORD = "WrongPass";

    private static final String ADMIN_PASSWORD = "admin_password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final String login = req.getParameter(LOGIN);
        final String password = req.getParameter(PASSWORD);

        if (login.equals(ADMIN) && !password.equals(ADMIN_PASSWORD)) {
            req.setAttribute(WRONG_PASSWORD, true);
            req.getRequestDispatcher(MAIN).forward(req, resp);
            return;
        }

        req.getSession().setAttribute(USER, login);
        resp.sendRedirect(req.getContextPath());
    }
}
