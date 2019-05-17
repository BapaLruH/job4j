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
import java.util.Map;

/**
 * Сlass RoleServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class RoleServlet extends HttpServlet implements ServletUtils {
    private final Service service = ValidateService.getInstance();

    public RoleServlet() {
        ServletUtils.ACTIONS.put("add", this::add);
        ServletUtils.ACTIONS.put("update", this::update);
        ServletUtils.ACTIONS.put("delete", this::delete);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("id") == null) {
            req.setAttribute("roles", service.findAllRoles());
        } else {
            Role role = service.findRoleById(Integer.parseInt(req.getParameter("id")));
            if (role != null) {
                req.setAttribute("roles", List.of(role));
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/RoleView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> result = ServletUtils.execute(req);
        String opComplete = result.get("Complete");
        if (opComplete != null && !Boolean.valueOf(opComplete)) {
            req.setAttribute("result", result.get("Result"));
            doGet(req, resp);
        }
        resp.sendRedirect(String.format("%s/roles", req.getContextPath()));
    }

    @Override
    public Map<String, String> add(HttpServletRequest req) {
        return service.addRole(
                new Role(
                        req.getParameter("name"),
                        req.getParameter("defaultRole") != null
                )
        );
    }

    @Override
    public Map<String, String> update(HttpServletRequest req) {
        return service.updateRole(
                new Role(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("defaultRole") != null
                )
        );
    }

    @Override
    public Map<String, String> delete(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        return service.deleteRole(id);
    }
}
