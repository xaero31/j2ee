package ru.gb.j2ee.servlet.error;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 */
public class ForbiddenServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Error 403. Forbidden. Redirecting to main page");
        req.getRequestDispatcher("main").forward(req, resp);
    }
}
