package ru.job4j.storesql;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Сlass StoreSQL.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 20.04.2019
 */
public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
    }

    /**
     * Start database manipulation.
     *
     * @param size type int
     */
    public void start(int size) {
        try {
            connect();
            createNewTable();
            clearTable();
            generate(size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to establish a connection to the given database URL.
     *
     * @throws SQLException if a database access error occurs or the url is
     *                      {@code null}
     */
    private void connect() throws SQLException {
        Connection conn = DriverManager.getConnection(
                config.get("url"),
                config.get("username"),
                config.get("password")
        );
        if (conn != null) {
            this.connect = conn;
        }
    }

    /**
     * Creates a new table in the DB if the table does not exists.
     *
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     */
    private void createNewTable() throws SQLException {
        connect.createStatement().execute("CREATE TABLE IF NOT EXISTS Entrys(field integer)");
    }

    /**
     * Deletes all items in the current table.
     *
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     */
    private void clearTable() throws SQLException {
        connect.createStatement().execute("DELETE FROM Entrys");
    }

    /**
     * Generates N entries in the table.
     */
    private void generate(int size) {
        try {
            connect.setAutoCommit(false);
            PreparedStatement statement = connect.prepareStatement("INSERT INTO Entrys(field) values (?)");
            for (int i = 0; i < size; i++) {
                statement.setInt(1, i);
                statement.addBatch();
            }
            statement.executeBatch();
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Returns a list with all items from db.
     *
     * @return List<Entry>.
     * @throws SQLException if a database access error occurs
     *                      or this method is called on a closed connection
     */
    public List<Entry> load() throws SQLException {
        ArrayList<Entry> entries = new ArrayList<>();
        ResultSet resultSet = connect.createStatement().executeQuery("SELECT * FROM Entrys");
        while (resultSet.next()) {
            entries.add(new Entry(resultSet.getInt("field")));
        }
        return entries;
    }

    /**
     * Close the connection.
     *
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void close() throws SQLException {
        if (connect != null) {
            connect.close();
        }
    }

    @XmlRootElement
    public static class Entries {
        private List<Entry> entry;

        public Entries() {
        }

        public Entries(List<Entry> entry) {
            this.entry = entry;
        }

        public List<Entry> getEntry() {
            return entry;
        }

        public void setEntry(List<Entry> entry) {
            this.entry = entry;
        }
    }
}


