package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Сlass UserUpdateServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }
}
