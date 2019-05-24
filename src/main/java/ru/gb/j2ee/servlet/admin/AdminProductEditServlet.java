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
 * Servlet for admin edit product view
 */
@WebServlet(name = "editProductAdmin", urlPatterns = "admin/edit")
public class AdminProductEditServlet extends HttpServlet {

    private static final String EDIT_XHTML = "/views/jsf/edit.xhtml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(EDIT_XHTML).forward(req, resp);
    }
}
