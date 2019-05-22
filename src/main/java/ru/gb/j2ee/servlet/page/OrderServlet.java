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
 * Servlet for order.jsp page
 */
@WebServlet(name = "order", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private static final String ORDER_JSP = "/views/jsp/order.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ORDER_JSP).forward(req, resp);
    }
}
