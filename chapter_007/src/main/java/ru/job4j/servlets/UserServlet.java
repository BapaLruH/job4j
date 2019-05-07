package ru.job4j.servlets;

import ru.job4j.model.User;
import ru.job4j.service.Service;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> users = service.findAll();
        StringBuilder sb = new StringBuilder();
        if (!users.isEmpty()) {
            fillingTableRows(req, sb, null);
            users.forEach(u -> fillingTableRows(req, sb, u));
        }
        writer.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">")
                .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>Title</title>")
                .append("</head>")
                .append("<body>")
                .append("<div><form><button formaction=\"")
                .append(req.getContextPath())
                .append("/create\" formmethod=GET>create</button></form></div>");
        if (!users.isEmpty()) {
            writer.append("<table border=\"1\">")
                    .append(sb.toString())
                    .append("</table>");
        }
        writer.append("</body>")
                .append("</html>");
        writer.flush();
    }

    private void fillingTableRows(HttpServletRequest req, StringBuilder sb, User user) {
        String id = "id";
        String name = "name";
        String login = "login";
        String email = "email";
        String createDate = "create_date";
        if (user != null) {
            id = String.valueOf(user.getId());
            name = user.getName();
            login = user.getLogin();
            email = user.getEmail();
            createDate = String.valueOf(user.getCreateDate());
        }
        sb.append("<tr>");
        sb.append("<td>").append(id).append("</td>");
        sb.append("<td>").append(name).append("</td>");
        sb.append("<td>").append(login).append("</td>");
        sb.append("<td>").append(email).append("</td>");
        sb.append("<td>").append(createDate).append("</td>");
        if (user != null) {
            sb.append("<td>")
                    .append("<form action=\"" + req.getContextPath() + "/edit\" method=\"get\">")
                    .append("<input type=\"hidden\" name=\"action\" value=\"update\">")
                    .append("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">")
                    .append("<input type=\"hidden\" name=\"name\" value=\"" + name + "\">")
                    .append("<input type=\"hidden\" name=\"login\" value=\"" + login + "\">")
                    .append("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">")
                    .append("<input type=\"submit\" value=\"update\">")
                    .append("</form>")
                    .append("</td>");
            sb.append("<td>")
                    .append("<form action=\"" + req.getContextPath() + "/list\" method=\"post\">")
                    .append("<input type=\"hidden\" name=\"action\" value=\"delete\">")
                    .append("<input type=\"hidden\" name=\"id\" value=\"" + id + "\">")
                    .append("<input type=\"submit\" value=\"delete\">")
                    .append("</form>")
                    .append("</td>");
        } else {
            sb.append("<td>");
            sb.append("</td>");
            sb.append("<td>");
            sb.append("</td>");
        }
        sb.append("</tr>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Function<HttpServletRequest, String> function = actions.getOrDefault(action, request -> {
            throw new UnsupportedOperationException(String.format("Action %s is not found", action));
        });
        String result = function.apply(req);
        resp.getOutputStream().println(result);
        doGet(req, resp);
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
