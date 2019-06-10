package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PersonServlet extends HttpServlet {
    private List<Person> persons = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("persons", new ObjectMapper().writeValueAsString(this.persons));
        req.getRequestDispatcher("/WEB-INF/jscss/persons.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        reader.close();
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(sb.toString(), Person.class);
        this.persons.add(person);
        resp.sendRedirect(String.format("%s/persons", req.getContextPath()));
    }
}
