package ru.gb.j2ee.servlet.page;

import lombok.Setter;
import ru.gb.j2ee.model.Order;
import ru.gb.j2ee.repository.OrderRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Nikita Ermakov
 *
 * Servlet for order.jsp page
 */
@WebServlet(name = "order", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

    private static final String ORDER_JSP = "/views/jsp/order.jsp";

    @Setter
    @Inject
    private OrderRepository orderRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final HttpSession session = req.getSession(false);
        final String user = (String) session.getAttribute("user");
        final List<Order> orders = orderRepository.getByUser(user);

        req.setAttribute("orders", orders);
        req.getRequestDispatcher(ORDER_JSP).forward(req, resp);
    }
}
