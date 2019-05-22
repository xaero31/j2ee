package ru.gb.j2ee.servlet.page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for product.jsp page
 */
@WebServlet(name = "product", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    private static final String PRODUCT_JSP = "/views/jsp/product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(PRODUCT_JSP).forward(req, resp);
    }
}
