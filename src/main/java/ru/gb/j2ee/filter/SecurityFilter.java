package ru.gb.j2ee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 */
@WebFilter(filterName = "security", urlPatterns = {"/security/*", "/cart"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("failed", true);
            request.getRequestDispatcher(request.getContextPath() + "/main").forward(request, response);
        } else {
            final String user = (String) session.getAttribute("user");

            response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
            response.getWriter().println("<p>User " + user + " is logged in. <a href=\"" + request.getContextPath()
                    + "/security/logout\">Logout</a></p>");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
