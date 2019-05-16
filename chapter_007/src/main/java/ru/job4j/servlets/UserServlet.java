package ru.job4j.servlets;

import ru.job4j.model.User;
import ru.job4j.service.Service;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Сlass UserServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public class UserServlet extends HttpServlet {
    private final Service service = ValidateService.getInstance();
    private final HashMap<String, Function<HttpServletRequest, String>> actions = new HashMap<>();

    public UserServlet() {
        actions.put("add", this::add);
        actions.put("update", this::update);
        actions.put("delete", this::delete);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("id") == null) {
            req.setAttribute("users", service.findAll());
        } else {
            User user = service.findById(Integer.parseInt(req.getParameter("id")));
            if (user != null) {
                req.setAttribute("users", List.of(user));
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Function<HttpServletRequest, String> function = actions.getOrDefault(action, request -> {
            throw new UnsupportedOperationException(String.format("Action %s is not found", action));
        });
        String result = function.apply(req);
        req.setAttribute("result", result);
        req.setAttribute("users", service.findAll());
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    private String add(HttpServletRequest req) {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        return service.add(new User(name, login, email));
    }

    private String update(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        return service.update(new User(id, name, login, email));
    }

    private String delete(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        return service.delete(id);
    }
}
