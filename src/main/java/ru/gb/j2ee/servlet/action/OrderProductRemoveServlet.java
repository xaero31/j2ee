package ru.gb.j2ee.servlet.action;

import ru.gb.j2ee.model.Order;
import ru.gb.j2ee.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for removing product from user's product cart
 */
@WebServlet(name = "orderProductRemove", urlPatterns = "/cart/remove")
public class OrderProductRemoveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final HttpSession session = req.getSession(false);
        final Order order = (Order) session.getAttribute("order");
        final int id = Integer.valueOf(req.getParameter("productId"));

        final Product product = order.getProductMap().keySet().stream()
                .filter(key -> key.getId() == id)
                .findFirst()
                .orElse(new Product());
        int currentCount = order.getProductMap().get(product);

        if (currentCount == 1) {
            order.getProductMap().remove(product);
        } else {
            order.getProductMap().put(product, --currentCount);
        }

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
