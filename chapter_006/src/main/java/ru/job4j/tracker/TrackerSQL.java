package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Сlass TrackerSQL.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 17.04.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
    private Connection connection;

    public TrackerSQL() {
        init();
    }

    /**
     * Initializes the database connection or returns the last connection.
     *
     * @return result of the connection initialization.
     */
    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this.connection != null;
    }

    /**
     * Adds a item to the DB of items.
     *
     * @param item type Item.
     * @return item type Item.
     */
    @Override
    public Item add(Item item) {
        try (PreparedStatement st = this.connection.prepareStatement("INSERT into item(id_author, title, description, id_state, id_category) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            setParams(item, st);
            st.execute();
            ResultSet resultSet = st.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(String.valueOf(resultSet.getInt(1)));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    /**
     * Replaces an DB item with an id.
     *
     * @param id   type String.
     * @param item type Item.
     * @return result type boolean.
     */
    @Override
    public boolean replace(String id, Item item) {
        boolean result = false;
        try (PreparedStatement st = this.connection.prepareStatement("UPDATE item set id_author = ?, title = ?, description = ?, id_state = ?, id_category = ? where id = ?")) {
            setParams(item, st);
            st.setInt(6, Integer.parseInt(id));
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private void setParams(Item item, PreparedStatement st) throws SQLException {
        st.setInt(1, item.getAuthorId());
        st.setString(2, item.getName());
        st.setString(3, item.getDescription());
        st.setInt(4, item.getStateId());
        st.setInt(5, item.getCategoryId());
    }

    /**
     * Deleted an DB item with an id.
     *
     * @param id type String.
     * @return result type boolean.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (PreparedStatement st = this.connection.prepareStatement("DELETE from item where id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Returns all items of the DB.
     *
     * @return items type List<Item>.
     */
    @Override
    public List<Item> findAll() {
        ArrayList<Item> users = new ArrayList<>();
        try (PreparedStatement st = this.connection.prepareStatement("SELECT * from item")) {
            fillUserList(users, st);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    /**
     * Returns all items of the DB with the name(key).
     *
     * @param key type String.
     * @return items type List<Item>.
     */
    @Override
    public List<Item> findByName(String key) {
        ArrayList<Item> users = new ArrayList<>();
        try (PreparedStatement st = this.connection.prepareStatement("SELECT * from item where title = ?")) {
            st.setString(1, key);
            fillUserList(users, st);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    private void fillUserList(ArrayList<Item> users, PreparedStatement st) throws SQLException {
        ResultSet resultSet = st.executeQuery();
        while (resultSet.next()) {
            users.add(new Item(
                    String.valueOf(resultSet.getInt("id")),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getInt("id_author"),
                    resultSet.getInt("id_state"),
                    resultSet.getInt("id_category")
            ));
        }
    }

    /**
     * Returns item of the DB with the id.
     *
     * @param id type String.
     * @return item type Item.
     */
    @Override
    public Item findById(String id) {
        Item result = null;
        try (PreparedStatement st = this.connection.prepareStatement("SELECT * from item where id = ?")) {
            st.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                result = new Item(
                        String.valueOf(resultSet.getInt("id")),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("id_author"),
                        resultSet.getInt("id_state"),
                        resultSet.getInt("id_category")
                );
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * Close the connection.
     *
     * @throws Exception
     */
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
