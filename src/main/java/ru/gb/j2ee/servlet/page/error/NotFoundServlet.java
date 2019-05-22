package ru.gb.j2ee.servlet.page.error;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for 404 http error
 */
public class NotFoundServlet extends HttpServlet {

    private static final String MESSAGE = "Error 404. Page not found. Redirecting to main page";

    private static final String MAIN = "main";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(MESSAGE);
        req.getRequestDispatcher(MAIN).forward(req, resp);
    }
}
