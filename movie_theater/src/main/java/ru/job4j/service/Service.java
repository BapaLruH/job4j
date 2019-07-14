package ru.job4j.service;

import ru.job4j.model.Movie;
import ru.job4j.model.Seat;
import ru.job4j.model.User;

import java.util.List;
import java.util.Map;

/**
 * Сlass Service.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.07.2019
 */
public interface Service {
    Map<String, String> saveUserWithOccupiedSeats(User user, List<Seat> seats);

    List<Movie> getAllMovies();

    List<Seat> getSeatsListByMovieId(int id);

    List<Seat> getAllOccupiedSeatsByMovieId(int id);
}
