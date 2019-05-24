package ru.gb.j2ee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 *
 * Filter for secured servlets which requires to be logged in
 */
@WebFilter(filterName = "security", urlPatterns = {"/security/*", "/cart/*", "/catalog/*", "/order/*", "/categories/*"})
public class SecurityFilter implements Filter {

    private static final String USER = "user";

    private static final String FAILED_ATTRIBUTE = "failed";

    private static final String MAIN = "/main";

    private static final String CACHE_TITLE = "Cache-Control";

    private static final String CACHE_PARAMS = "private, no-store, no-cache, must-revalidate";

    private static final String LOGOUT_POSTFIX = "/security/logout\">Logout</a></p>";

    private static final String LOGOUT_MID_PART = " is logged in. <a href=\"";

    private static final String LOGOUT_PREFIX = "<p>User ";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(USER) == null) {
            request.setAttribute(FAILED_ATTRIBUTE, true);
            request.getRequestDispatcher(request.getContextPath() + MAIN).forward(request, response);
        } else {
            final String user = (String) session.getAttribute(USER);

            response.setHeader(CACHE_TITLE, CACHE_PARAMS);
            response.getWriter().println(LOGOUT_PREFIX + user + LOGOUT_MID_PART
                    + request.getContextPath() + LOGOUT_POSTFIX);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
