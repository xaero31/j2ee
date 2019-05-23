package ru.gb.j2ee.servlet.action;

import lombok.Setter;
import ru.gb.j2ee.model.meta.State;
import ru.gb.j2ee.repository.OrderRepository;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for cancelling one of orders
 */
@WebServlet(name = "cancelOrder", urlPatterns = "/order/cancel")
public class CancelOrderServlet extends HttpServlet {

    @Setter
    @Inject
    private OrderRepository orderRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String user = (String) req.getSession(false).getAttribute("user");
        final int orderId = Integer.valueOf(req.getParameter("orderId"));

        orderRepository.getByUserAndId(user, orderId).setState(State.CANCELED);
        resp.sendRedirect(req.getContextPath() + "/order");
    }
}
