package ru.gb.j2ee.servlet.page;

import lombok.Setter;
import ru.gb.j2ee.repository.CategoryRepository;
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
 * Servlet for main.jsp page
 */
@WebServlet(name = "main", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    private static final String MAIN_JSP = "/views/jsp/main.jsp";

    @Setter
    @Inject
    private ProductRepository productRepository;

    @Setter
    @Inject
    private CategoryRepository categoryRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!productRepository.isReady() || !categoryRepository.isReady()) {
            System.out.println("Data initialized");
        }

        req.getRequestDispatcher(MAIN_JSP).forward(req, resp);
    }
}
