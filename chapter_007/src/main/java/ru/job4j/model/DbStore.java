package ru.job4j.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Сlass DbStore.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class DbStore implements Store {
    private static final BasicDataSource DATA_SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DATA_SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/users");
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
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a new user to the storage.
     *
     * @param user type User
     * @return {@code true} if a user has been added or {@code false}
     */
    @Override
    public boolean add(User user) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO users(name, login, email) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.execute();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Updates the user from the storage with the specified id.
     *
     * @param id   type int
     * @param user type User
     * @return {@code true} if a user has been updated or {@code false}
     */
    @Override
    public boolean update(int id, User user) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE users SET name = ?, login = ?, email = ? WHERE id = ?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes the user from the storage with the specified id.
     *
     * @param id type int
     * @return {@code true} if a user has been deleted or {@code false}
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Returns all entries.
     *
     * @return {@code List<User>}
     */
    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users ORDER BY id")) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Returns the record from the storage with the specified id
     *
     * @param id type int
     * @return {@code User} if a user has been found or {@code null}
     */
    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
