package ru.gb.j2ee.servlet.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Servlet for admin catalog page
 */
@WebServlet(name = "catalogAdmin", urlPatterns = "admin/catalog")
public class AdminCatalogServlet extends HttpServlet {

    private static final String CATALOG_XHTML = "/views/jsf/catalog.xhtml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher(CATALOG_XHTML).forward(req, resp);
    }
}
