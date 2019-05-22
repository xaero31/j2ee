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
 * Servlet for cart.jsp page
 */
@WebServlet(name = "cart", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    private static final String CART_JSP = "/views/jsp/cart.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CART_JSP).include(req, resp);
    }
}
