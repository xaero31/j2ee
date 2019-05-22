package ru.gb.j2ee.servlet.page;

import lombok.Setter;
import ru.gb.j2ee.repository.ProductRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for catalog.jsp page
 */
@WebServlet(name = "catalog", urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    private static final String PRODUCTS_ATTRIBUTE = "products";

    private static final String CATALOG_JSP = "/views/jsp/catalog.jsp";

    @Setter
    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PRODUCTS_ATTRIBUTE, productRepository.getProducts());
        req.getRequestDispatcher(CATALOG_JSP).forward(req, resp);
    }
}
