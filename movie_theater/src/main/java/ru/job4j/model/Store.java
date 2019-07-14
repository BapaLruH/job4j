package ru.job4j.model;

import java.util.List;

/**
 * Сlass Store.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.07.2019
 */
public interface Store {
    List<Movie> getAllMovies();

    List<Seat> getSeatsListByMovieId(int id);

    List<Seat> getAllOccupiedSeatsByMovieId(int id);

    boolean saveUserWithOccupiedSeats(User user, List<Seat> seats);
}
