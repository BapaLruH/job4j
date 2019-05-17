package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сlass AuthFilter.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String action = request.getParameter("action");
        if (request.getRequestURI().contains("/signIn") || request.getRequestURI().contains("/create") || (action != null && action.equals("add"))) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session != null && session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/signIn", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
