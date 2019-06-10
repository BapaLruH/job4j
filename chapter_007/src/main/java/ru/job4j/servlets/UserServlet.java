package ru.job4j.servlets;

import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.service.Service;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сlass UserServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public class UserServlet extends HttpServlet implements ServletUtils {
    private final Service service = ValidateService.getInstance();
    private final HashMap<String, String> requestPath = new HashMap<>();

    public UserServlet() {
        ServletUtils.ACTIONS.put("add", this::add);
        ServletUtils.ACTIONS.put("update", this::update);
        ServletUtils.ACTIONS.put("delete", this::delete);
        requestPath.put("add", "/WEB-INF/views/Create.jsp");
        requestPath.put("update", "/WEB-INF/views/Update.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("access") != null) {
            req.setAttribute("access", session.getAttribute("access"));
            session.removeAttribute("access");
        }
        if (req.getParameter("id") == null) {
            req.setAttribute("users", service.findAll());
        } else {
            User user = service.findById(Integer.parseInt(req.getParameter("id")));
            if (user != null) {
                req.setAttribute("users", List.of(user));
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/Index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> result = ServletUtils.execute(req);
        String opComplete = result.get("Complete");
        if (opComplete != null && !Boolean.valueOf(opComplete)) {
            List<Role> roles = service.findAllRoles();
            if (!roles.isEmpty()) {
                req.setAttribute("roles", roles);
            }
            req.setAttribute("result", result.get("Result"));

            Integer id = (Integer) req.getSession().getAttribute("id");
            if (id != null) {
                User user = establishParameters(req);
                req.setAttribute("user", user);
            }
            String path = requestPath.getOrDefault(req.getParameter("action"),  "/WEB-INF/views/Index.jsp");
            req.getRequestDispatcher(path).forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            if (session != null && session.getAttribute("login") == null) {
                resp.sendRedirect(String.format("%s/signIn?login=%s", req.getContextPath(), req.getParameter("login")));
            } else {
                boolean isAdmin = false;
                if (session != null && session.getAttribute("isAdmin") != null) {
                    isAdmin = (Boolean) session.getAttribute("isAdmin");
                }
                if (session != null && !session.getAttribute("login").equals(req.getParameter("login")) && !isAdmin) {
                    resp.sendRedirect(String.format("%s/signIn?login=%s", req.getContextPath(), req.getParameter("login")));
                    return;
                }
                resp.sendRedirect(String.format("%s/users", req.getContextPath()));
            }
        }
    }

    @Override
    public Map<String, String> add(HttpServletRequest req) {
        return service.add(establishParameters(req));
    }

    @Override
    public Map<String, String> update(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        User user = establishParameters(req);
        user.setId(id);
        return service.update(user);
    }

    @Override
    public Map<String, String> delete(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        return service.delete(id);
    }

    private User establishParameters(HttpServletRequest req) {
        Map<String, String[]> parameters = req.getParameterMap();
        User user = new User();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            switch (entry.getKey()) {
                case "action":
                case "id":
                    break;
                case "name":
                    user.setName(entry.getValue()[0]);
                    break;
                case "login":
                    user.setLogin(entry.getValue()[0]);
                    break;
                case "email":
                    user.setEmail(entry.getValue()[0]);
                    break;
                case "password":
                    user.setPassword(entry.getValue()[0]);
                    break;
                default:
                    user.addRole(new Role(Integer.parseInt(entry.getKey()), entry.getValue()[0], false));
            }
        }
        return user;
    }
}
