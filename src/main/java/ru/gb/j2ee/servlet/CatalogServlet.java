package ru.gb.j2ee.servlet;

import ru.gb.j2ee.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita Ermakov
 */
@WebServlet(name = "catalog", urlPatterns = "/catalog")
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Product> products = new ArrayList<>();

        products.add(new Product("Leg", 500, "Human's leg"));
        products.add(new Product("Hand", 375, "Human's hand"));
        products.add(new Product("Head", 2000, "Separated human's head"));

        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/views/catalog.jsp").forward(req, resp);
    }
}
