package ru.gb.j2ee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Nikita Ermakov
 */
@WebFilter(filterName = "encoder", urlPatterns = "/*")
public class EncodingFilter implements Filter {

    private static final String ENCODING = "text/html;charset=UTF-8";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType(ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
