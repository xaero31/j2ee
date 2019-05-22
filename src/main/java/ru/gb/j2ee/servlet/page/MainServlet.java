package ru.gb.j2ee.servlet.page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for main.jsp page
 */
@WebServlet(name = "main", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    private static final String MAIN_JSP = "/views/jsp/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MAIN_JSP).forward(req, resp);
    }
}
