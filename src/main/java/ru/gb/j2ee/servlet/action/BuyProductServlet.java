package ru.gb.j2ee.servlet.action;

import lombok.Setter;
import ru.gb.j2ee.model.Order;
import ru.gb.j2ee.repository.ProductRepository;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 */
@WebServlet(name = "buyServlet", urlPatterns = "/catalog/buy")
public class BuyProductServlet extends HttpServlet {

    private static final String ORDER = "order";

    @Setter
    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final HttpSession session = req.getSession(false);
        Order order = (Order) session.getAttribute(ORDER);

        if (session.getAttribute(ORDER) == null) {
            order = new Order();
            session.setAttribute(ORDER, order);
        }

        final int productId = Integer.valueOf(req.getParameter("productId"));
        order.getProducts().add(productRepository.getById(productId));

        resp.sendRedirect(req.getContextPath() + "/catalog");
    }
}
