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

    @Setter
    @Inject
    private ProductRepository productRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productRepository.getProducts());
        req.getRequestDispatcher("/views/jsp/catalog.jsp").forward(req, resp);
    }
}
