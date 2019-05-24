package ru.gb.j2ee.servlet.action;

import lombok.Setter;
import ru.gb.j2ee.model.Category;
import ru.gb.j2ee.model.Order;
import ru.gb.j2ee.model.Product;
import ru.gb.j2ee.repository.CategoryRepository;
import ru.gb.j2ee.repository.ProductRepository;

import javax.inject.Inject;
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
 * Servlet for 'buy' button at catalog view
 */
@WebServlet(name = "buyServlet", urlPatterns = "/catalog/buy")
public class BuyProductServlet extends HttpServlet {

    private static final String ORDER = "order";

    private static final int INIT_COUNT = 1;

    @Setter
    @Inject
    private CategoryRepository categoryRepository;

    @Setter
    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final HttpSession session = req.getSession(false);
        final String categoryName = req.getParameter("category");
        final Order order = getOrder(session);

        putProduct(req, order);
        directToView(req, resp, categoryName);
    }

    private Order getOrder(HttpSession session) {
        Order order = (Order) session.getAttribute(ORDER);

        if (session.getAttribute(ORDER) == null) {
            order = new Order();
            session.setAttribute(ORDER, order);
        }
        return order;
    }

    private void putProduct(HttpServletRequest req, Order order) {
        final int productId = Integer.valueOf(req.getParameter("productId"));
        final Product product = productRepository.getById(productId);

        if (order.getProductMap().containsKey(product)) {
            int currentCount = order.getProductMap().get(product);
            order.getProductMap().put(product, ++currentCount);
        } else {
            order.getProductMap().put(product, INIT_COUNT);
        }
    }

    private void directToView(HttpServletRequest req,
                              HttpServletResponse resp,
                              String categoryName) throws ServletException, IOException {
        if (categoryName != null && !categoryName.isEmpty()) {
            final Category category = categoryRepository.getByName(categoryName);

            req.setAttribute("products", categoryRepository.getFetchById(category.getId()).getProducts());
            req.setAttribute("category", category.getName());
            req.getRequestDispatcher("/views/jsp/catalog.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/catalog");
        }
    }
}
