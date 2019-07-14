package ru.job4j.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сlass DbStoreImpl.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.07.2019
 */
public class DbStoreImpl implements Store {
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();
    private static final DbStoreImpl INSTANCE = new DbStoreImpl();

    public DbStoreImpl() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DATA_SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/movie_theater");
        DATA_SOURCE.setUsername("postgres");
        DATA_SOURCE.setPassword("123456789");
        DATA_SOURCE.setMinIdle(5);
        DATA_SOURCE.setMaxIdle(10);
        DATA_SOURCE.setMaxOpenPreparedStatements(100);
    }

    /**
     * Returns an instance of this object or creates a new.
     *
     * @return instance of this object
     */
    public static DbStoreImpl getInstance() {
        return INSTANCE;
    }

    /**
     * Returns a list of movies.
     *
     * @return result type List<Movie>
     */
    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement plSt = connection.prepareStatement(
                     "select * from movies")
        ) {
            ResultSet resultSet = plSt.executeQuery();
            while (resultSet.next()) {
                movies.add(new Movie(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("link")
                        )
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    /**
     * Returns a list of seats with the specified movie id.
     *
     * @param id type int
     * @return list type List<Seat>
     */
    @Override
    public List<Seat> getSeatsListByMovieId(int id) {
        List<Seat> seats = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement plSt = connection.prepareStatement(
                     "select pl.id, pl.name, pl.price, pl.row, count(pl.row), mv.id as movieId, mv.name as movieName, mv.link as movieLink, up.id as occupied "
                             + "from places pl "
                             + "    left join movies mv "
                             + "        on pl.movie_id = mv.id "
                             + "    left join usr_places up "
                             + "        on pl.id = up.place_id "
                             + "where mv.id = ? "
                             + "group by rollup (pl.row, (pl.id, pl.name, pl.price, mv.name, mv.id, mv.link, up.id)) "
                             + "having pl.row notnull "
                             + "order by pl.row, pl.id is not null, pl.id;")
        ) {
            plSt.setInt(1, id);
            ResultSet resultSet = plSt.executeQuery();
            while (resultSet.next()) {
                int placeId = resultSet.getInt("id");
                if (placeId == 0) {
                    Seat emptySeat = new Seat();
                    emptySeat.setName(String.valueOf(resultSet.getInt("row")));
                    emptySeat.setRow(resultSet.getInt("count"));
                    seats.add(emptySeat);
                } else {
                    seats.add(new Seat(
                            placeId,
                            resultSet.getString("name"),
                            resultSet.getInt("row"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("occupied") > 0,
                            new Movie(
                                    resultSet.getInt("movieId"),
                                    resultSet.getString("movieName"),
                                    resultSet.getString("movieLink")
                            )
                    ));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return seats;
    }

    /**
     * Returns a list of occupied seats with the specified movie id.
     *
     * @param id type int
     * @return list type List<Seat>
     */
    @Override
    public List<Seat> getAllOccupiedSeatsByMovieId(int id) {
        List<Seat> seats = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement plSt = connection.prepareStatement(
                     "select pl.name, pl.row "
                             + "from places pl "
                             + "    left join movies mv "
                             + "        on pl.movie_id = mv.id "
                             + "    left join usr_places up "
                             + "        on pl.id = up.place_id "
                             + "where mv.id = ? and up.id is not null;")
        ) {
            plSt.setInt(1, id);
            ResultSet resultSet = plSt.executeQuery();
            while (resultSet.next()) {
                seats.add(new Seat(
                        resultSet.getString("name"),
                        resultSet.getInt("row"),
                        new Movie(id)
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return seats;
    }

    /**
     * Adds a new entry in users/usr_places table of database.
     *
     * @param user type User.
     * @param seats type List<Seat>
     * @return {@code true} if the operation is completed, or {@code false}
     */
    @Override
    public boolean saveUserWithOccupiedSeats(User user, List<Seat> seats) {
        boolean rsl = false;
        Connection connection = null;
        try {
            connection = DATA_SOURCE.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement usrSt = connection.prepareStatement("insert into users(name, phone) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            usrSt.setString(1, user.getName());
            usrSt.setString(2, user.getPhone());
            usrSt.execute();
            try (ResultSet key = usrSt.getGeneratedKeys()) {
                if (key.next()) {
                    user.setId(key.getInt(1));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            PreparedStatement sSt = connection.prepareStatement("select id from places where row = ? and name = ? for update");
            StringBuilder sb = new StringBuilder();
            seats.forEach(v -> {
                try {
                    sSt.setInt(1, v.getRow());
                    sSt.setString(2, v.getName());
                    ResultSet resultSet = sSt.executeQuery();
                    if (resultSet.next()) {
                        sb.append(resultSet.getInt("id"));
                        sb.append(" ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            String ids = sb.toString().trim();
            String[] arrId = ids.split(" ");
            PreparedStatement usrPl = connection.prepareStatement("insert into usr_places(usr_id, place_id) values (?, ?)");
            for (String id : arrId) {
                usrPl.setInt(1, user.getId());
                usrPl.setInt(2, Integer.parseInt(id));
                usrPl.addBatch();
            }
            usrPl.executeBatch();
            connection.commit();
            rsl = true;
        } catch (Exception ex) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return rsl;
    }
}
