package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сlass UserCreateServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class UserCreateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head>"
                + "    <meta charset=\"UTF-8\">"
                + "    <title>Title</title>"
                + "</head>"
                + "<body>"
                + "<div><form><button formaction=\"" + req.getContextPath() + "/list\" formmethod=GET>back</button></form></div>"
                + "<div>\n"
                + "            <form action=\"" + req.getContextPath() + "/list\" method=\"post\">\n"
                + "                <input type=\"hidden\" name=\"action\" value=\"add\">\n"
                + "                <input type=\"text\" placeholder=\"name\" name=\"name\">\n"
                + "                <input type=\"text\" placeholder=\"login\" name=\"login\">\n"
                + "                <input type=\"text\" placeholder=\"email\" name=\"email\">\n"
                + "                <input type=\"submit\">\n"
                + "            </form>\n"
                + "        </div>"
                + "</body>"
                + "</html>");
        writer.flush();
    }
}
