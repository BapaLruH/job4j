package ru.job4j.servlets;

import ru.job4j.model.City;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.service.Service;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Сlass UserUpdateServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class UserUpdateServlet extends HttpServlet {
    private final Service service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        int userId = Integer.parseInt(req.getParameter("id"));
        User currentUser = service.findById(userId);
        List<Role> roles = service.findAllRoles();
        List<City> cities = service.findAllCities();
        if (currentUser != null && !roles.isEmpty() && !cities.isEmpty()) {
            req.setAttribute("user", currentUser);
            req.setAttribute("roles", roles);
            req.setAttribute("cities", cities);
        }
        req.getRequestDispatcher("/WEB-INF/views/Update.jsp").forward(req, resp);
    }
}
