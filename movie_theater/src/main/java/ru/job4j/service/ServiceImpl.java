package ru.job4j.service;

import ru.job4j.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сlass ServiceImpl.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.07.2019
 */
public class ServiceImpl implements Service {
    private static final ServiceImpl INSTANCE = new ServiceImpl();
    private final Store store = DbStoreImpl.getInstance();

    /**
     * Returns an instance of this object or creates a new.
     *
     * @return instance of this object
     */
    public static ServiceImpl getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a new entries in the database.
     *
     * @param user type User.
     * @param seats type List<Seat>
     * @return result of operation.
     */
    @Override
    public Map<String, String> saveUserWithOccupiedSeats(User user, List<Seat> seats) {
        Map<String, String> result = new HashMap<>();
        if (store.saveUserWithOccupiedSeats(user, seats)) {
            result.put("Adds", "Seats reserved");
        } else {
            result.put("Adds", "Oops! Something went wrong");
        }
        return result;
    }

    /**
     * Returns a list of movies.
     *
     * @return result of operation.
     */
    @Override
    public List<Movie> getAllMovies() {
        return store.getAllMovies();
    }

    /**
     * Returns a list of seats with the specified movie id.
     *
     * @return result of operation.
     */
    @Override
    public List<Seat> getSeatsListByMovieId(int id) {
        return store.getSeatsListByMovieId(id);
    }

    /**
     * Returns a list of occupied seats with the specified movie id.
     *
     * @return result of operation.
     */
    @Override
    public List<Seat> getAllOccupiedSeatsByMovieId(int id) {
        return store.getAllOccupiedSeatsByMovieId(id);
    }
}
