package ru.gb.j2ee.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Nikita Ermakov
 */
@WebServlet(name = "product", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try (PrintWriter writer = resp.getWriter()) {
            writer.println("<h2>Products</h2>");
            writer.println("<ul>");
            writer.println("<li><a href=\"/lesson2/main\">main page</a></li>");
            writer.println("<li><a href=\"/lesson2/catalog\">catalog</a></li>");
            writer.println("<li><a href=\"/lesson2/cart\">product cart</a></li>");
            writer.println("<li><a href=\"/lesson2/order\">order</a></li>");
            writer.println("</ul>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
