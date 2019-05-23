package ru.gb.j2ee.servlet.action;

import lombok.Setter;
import ru.gb.j2ee.model.Order;
import ru.gb.j2ee.model.meta.State;
import ru.gb.j2ee.repository.OrderRepository;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author Nikita Ermakov
 *
 * Servlet for approving user's order
 */
@WebServlet(name = "submitOrder", urlPatterns = "/cart/submit")
public class SubmitOrderServlet extends HttpServlet {

    private static final String ORDER = "order";

    @Setter
    @Inject
    private OrderRepository orderRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final HttpSession session = req.getSession(false);
        final Order order = (Order) session.getAttribute(ORDER);
        final String user = (String) session.getAttribute("user");

        if (order != null) {
            order.setDate(new Date());
            order.setState(State.IN_PROGRESS);
            orderRepository.addOrder(user, order);
            session.removeAttribute(ORDER);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
