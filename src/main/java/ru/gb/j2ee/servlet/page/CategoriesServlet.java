package ru.gb.j2ee.servlet.page;

import lombok.Setter;
import ru.gb.j2ee.model.Category;
import ru.gb.j2ee.repository.CategoryRepository;

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
 * Servlet for categories.jsp
 */
@WebServlet(name = "categories", urlPatterns = "/categories")
public class CategoriesServlet extends HttpServlet {

    @Setter
    @Inject
    private CategoryRepository categoryRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryRepository.getAllCategories());
        req.getRequestDispatcher("/views/jsp/categories.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final int categoryId = Integer.valueOf(req.getParameter("categoryId"));
        final Category category = categoryRepository.getFetchById(categoryId);

        req.setAttribute("products", category.getProducts());
        req.setAttribute("category", category.getName());
        req.getRequestDispatcher("/views/jsp/catalog.jsp").forward(req, resp);
    }
}
