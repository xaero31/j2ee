package ru.gb.j2ee.servlet.page;

import ru.gb.j2ee.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for cart.jsp page
 */
@WebServlet(name = "cart", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {

    private static final String ORDER = "order";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final Order order = (Order) session.getAttribute(ORDER);

        if (order != null) {
            req.setAttribute("list", order.getProductMap());
            session.setAttribute(ORDER, order);
        }

        req.getRequestDispatcher("/views/jsp/cart.jsp").forward(req, resp);
    }
}
