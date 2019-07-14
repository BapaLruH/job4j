package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Movie;
import ru.job4j.model.Seat;
import ru.job4j.model.User;
import ru.job4j.service.Service;
import ru.job4j.service.ServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сlass HallServlet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.07.2019
 */
public class HallServlet extends HttpServlet {
    private final Service service = ServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("movie_id") != null) {
            int movieId = Integer.parseInt(req.getParameter("movie_id"));
            if (req.getParameter("checkSeats") != null) {
                List<Seat> occupiedSeats = service.getAllOccupiedSeatsByMovieId(movieId);
                List<Seat> currentSeats = new ArrayList<>();
                String txtSeats = req.getParameter("checkSeats");
                String[] ids = txtSeats.split(" ");
                Stream.of(ids).forEach(v -> {
                    Movie mv = new Movie(movieId);
                    Seat seat = new Seat(v.substring(v.indexOf("_") + 1), Integer.parseInt(v.substring(0, v.indexOf("_"))), mv);
                    if (occupiedSeats.contains(seat)) {
                        currentSeats.add(seat);
                    }
                });
                resp.setHeader("occupiedSelectedSeats", new ObjectMapper().writeValueAsString(currentSeats));
                req.getRequestDispatcher("/WEB-INF/pages/index.html").forward(req, resp);
            } else if (req.getParameter("allOccupiedPlaces") != null) {
                List<Seat> seats = service.getAllOccupiedSeatsByMovieId(movieId);
                resp.setHeader("allOccupiedPlaces", new ObjectMapper().writeValueAsString(seats));
                req.getRequestDispatcher("/WEB-INF/pages/index.html").forward(req, resp);
            } else {
                List<Seat> seats = service.getSeatsListByMovieId(movieId);
                resp.setHeader("seats", new ObjectMapper().writeValueAsString(seats));
                req.getRequestDispatcher("/WEB-INF/pages/index.html").forward(req, resp);
            }
        } else {
            resp.setHeader("movies", new ObjectMapper().writeValueAsString(service.getAllMovies()));
            req.getRequestDispatcher("/WEB-INF/pages/movies.html").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        reader.close();
        Map<String, String> res = new ObjectMapper().readValue(sb.toString(), HashMap.class);
        String[] ids = res.get("ids").split(" ");
        int movieId = Integer.parseInt(res.get("movie_id"));
        List<Seat> selectedSeats = Stream.of(ids).map(
                v -> new Seat(
                        v.substring(v.indexOf("_") + 1),
                        Integer.parseInt(v.substring(0, v.indexOf("_"))),
                        new Movie(movieId)))
                .collect(Collectors.toList());
        User user = new User(res.get("username"), res.get("phone"));
        Map<String, String> result = service.saveUserWithOccupiedSeats(user, selectedSeats);
        resp.setHeader("result", new ObjectMapper().writeValueAsString(result.get("Adds")));
        req.getRequestDispatcher("/WEB-INF/pages/index.html").forward(req, resp);
    }
}
