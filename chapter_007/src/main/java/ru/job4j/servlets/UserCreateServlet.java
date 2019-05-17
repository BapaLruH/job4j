package ru.job4j.servlets;

import ru.job4j.model.Role;
import ru.job4j.service.Service;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сlass UserCreateServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class UserCreateServlet extends HttpServlet {
    private final Service service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        List<Role> roles = service.findAllRoles();
        if (!roles.isEmpty()) {
            req.setAttribute("roles", roles);
        }
        req.getRequestDispatcher("/WEB-INF/views/Create.jsp").forward(req, resp);
    }
}
