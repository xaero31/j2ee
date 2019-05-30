package ru.gb.j2ee.servlet.action;

import lombok.Setter;
import ru.gb.j2ee.model.Order;
import ru.gb.j2ee.model.meta.State;
import ru.gb.j2ee.repository.OrderRepository;

import javax.inject.Inject;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
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
@ServletSecurity(@HttpConstraint(rolesAllowed = {"user", "admin"}))
public class CancelOrderServlet extends HttpServlet {

    @Setter
    @Inject
    private OrderRepository orderRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final int orderId = Integer.valueOf(req.getParameter("orderId"));
        final Order order = orderRepository.getById(orderId);

        order.setState(State.CANCELED);
        orderRepository.add(order);
        resp.sendRedirect(req.getContextPath() + "/order");
    }
}
