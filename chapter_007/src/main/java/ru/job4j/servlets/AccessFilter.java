package ru.job4j.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Сlass AccessFilter.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        String id = String.valueOf(session.getAttribute("id"));
        if (isAdmin != null && isAdmin || request.getRequestURI().contains("/create")) {
            chain.doFilter(req, resp);
        } else if (request.getRequestURI().contains("/update") && !request.getParameter("id").equals(id) || request.getRequestURI().contains("/roles")) {
            ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
            return;
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {
    }
}
