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

/**
 * Сlass SignInServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class SignInServlet extends HttpServlet {
    private final Service service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        if (logout != null && logout.equals("true")) {
            req.getSession().invalidate();
        }
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User currentUser = service.findUserByLoginPassword(login, password);
        if (currentUser != null) {
            Role adminRole = service.findRoleByName("Admin");
            HttpSession session = req.getSession();
            session.setAttribute("login", currentUser.getLogin());
            session.setAttribute("id", currentUser.getId());
            session.setAttribute("isAdmin", service.isRoleAvailable(currentUser, adminRole));
            session.setAttribute("access", String.format("Welcome, %s", currentUser.getName()));
            resp.sendRedirect(String.format("%s/users", req.getContextPath()));
        } else {
            req.setAttribute("access", "access denied");
            doGet(req, resp);
        }
    }
}
