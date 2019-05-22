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
 * Filter for secured servlets which requires to be logged in as admin
 */
@WebFilter(filterName = "admin", urlPatterns = "/admin/*")
public class AdminFilter implements Filter {

    private static final String ADMIN = "admin";

    private static final String USER = "user";

    private static final String LOGOUT_PREFIX = "<p>Admin is logged in. <a href=\"";

    private static final String LOGOUT_POSTFIX = "/security/logout\">Logout from admin</a></p>";

    private static final String CACHE_PARAMS = "private, no-store, no-cache, must-revalidate";

    private static final String CACHE_TITLE = "Cache-Control";

    private static final String MAIN = "/main";

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final HttpSession session = httpRequest.getSession(false);

        if (session == null || !session.getAttribute(USER).equals(ADMIN)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + MAIN);
        } else {
            httpResponse.setHeader(CACHE_TITLE, CACHE_PARAMS);
            httpResponse.getWriter().println(LOGOUT_PREFIX + httpRequest.getContextPath() + LOGOUT_POSTFIX);
        }

        chain.doFilter(request, response);
    }
}
