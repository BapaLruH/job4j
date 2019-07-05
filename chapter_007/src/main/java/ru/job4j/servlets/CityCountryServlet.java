package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.City;
import ru.job4j.service.Service;
import ru.job4j.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class CityCountryServlet extends HttpServlet implements ServletUtils {
    private final Service service = ValidateService.getInstance();

    public CityCountryServlet() {
        ServletUtils.ACTIONS.put("add", this::add);
        ServletUtils.ACTIONS.put("update", this::update);
        ServletUtils.ACTIONS.put("delete", this::delete);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if (req.getParameter("id") == null) {
            if (req.getParameter("countryId") != null) {
                int countryId = Integer.parseInt(req.getParameter("countryId"));
                resp.addHeader("citiesByCountry", new ObjectMapper().writeValueAsString(service.findCitiesByCountryId(countryId)));
                req.setAttribute("countryId", countryId);
            } else {
                req.setAttribute("countries", service.findAllCountries());
            }
        } else {
            City city = service.findCityById(Integer.parseInt(req.getParameter("id")));
            if (city != null) {
                req.setAttribute("city", city);
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/Place.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> result = ServletUtils.execute(req);
        String opComplete = result.get("Complete");
        if (opComplete != null && !Boolean.valueOf(opComplete)) {
            req.setAttribute("result", result.get("Result"));
            doGet(req, resp);
        }
        resp.sendRedirect(String.format("%s/places", req.getContextPath()));
    }

    @Override
    public Map<String, String> add(HttpServletRequest req) {
        City city = null;
        try {
            city = createCityObject(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert city != null;
        return service.addCountryCity(city);
    }

    @Override
    public Map<String, String> update(HttpServletRequest req) {
        City city = null;
        int id = -1;
        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }
        try {
            city = createCityObject(req);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert city != null;
        return service.updateCountryCity(id, city);
    }

    @Override
    public Map<String, String> delete(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        Map<String, String> result;
        if (req.getParameter("obj") != null && req.getParameter("obj").equals("country")) {
            result = service.deleteCountry(id);
        } else {
            result = service.deleteCity(id);
        }
        return result;
    }

    private City createCityObject(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        reader.close();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(sb.toString(), City.class);
    }
}
