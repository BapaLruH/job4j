package ru.job4j.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.model.Vacancy;
import ru.job4j.service.Config;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сlass VacancyDaoImpl.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 28.04.2019
 */
public class VacancyDaoImpl implements VacancyDao, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(VacancyDaoImpl.class);
    private final Connection vacancyDBConnect;
    private final Config config;

    /**
     * It is used for integration test.
     */
    public VacancyDaoImpl(Connection vacancyDBConnect, Config config) {
        this.vacancyDBConnect = vacancyDBConnect;
        this.config = config;
    }

    public VacancyDaoImpl(Config config) {
        Connection connection;
        this.config = config;
        try {
            connection = initConnection();
        } catch (SQLException e) {
            connection = null;
            LOG.error(e.getMessage(), e);
        }
        this.vacancyDBConnect = connection;
    }

    /**
     * Initiates a connection to the database.
     *
     * @return database connection
     * @throws SQLException if the method throws an exception.
     */
    private Connection initConnection() throws SQLException {
        return DriverManager.getConnection(
                config.get("jdbc.url"),
                config.get("jdbc.username"),
                config.get("jdbc.password")
        );
    }

    /**
     * Creates a new record in the database with the specified data,
     * returns the vacancy with filled id code from the database.
     *
     * @param vacancy data
     * @return vacancy.
     */
    @Override
    public Vacancy create(Vacancy vacancy) {
        try {
            PreparedStatement statement = this.vacancyDBConnect.prepareStatement(
                    "INSERT INTO vacancy(author, name, text, link, source, date) VALUES (?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            establishParameters(vacancy, statement);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                vacancy.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancy;
    }

    /**
     * Updates a record from the database with the specified key(id).
     *
     * @param id type String
     * @param vacancy type Vacancy
     * @return execution result
     */
    @Override
    public boolean update(String id, Vacancy vacancy) {
        boolean rsl = false;
        try {
            PreparedStatement statement = this.vacancyDBConnect.prepareStatement(
                    "UPDATE vacancy SET author = ?, name = ?, text = ?, link = ?, source = ?, date = ? WHERE id = ?"
            );
            establishParameters(vacancy, statement);
            statement.setInt(7, Integer.parseInt(id));
            rsl = statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * Fills in the query parameters.
     *
     * @param vacancy type Vacancy
     * @param statement type PreparedStatement
     * @throws SQLException if the method throws an exception.
     */
    private void establishParameters(Vacancy vacancy, PreparedStatement statement) throws SQLException {
        statement.setString(1, vacancy.getAuthor());
        statement.setString(2, vacancy.getName());
        statement.setString(3, vacancy.getText());
        statement.setString(4, vacancy.getLink());
        statement.setString(5, vacancy.getSource());
        statement.setTimestamp(6, Timestamp.valueOf(vacancy.getDate()));
    }

    /**
     * Deletes a record from the database with the specified key(id).
     *
     * @param id type String
     * @return execution result
     */
    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try {
            PreparedStatement statement = this.vacancyDBConnect.prepareStatement(
                    "DELETE FROM vacancy WHERE id = ?"
            );
            statement.setInt(1, Integer.parseInt(id));
            rsl = statement.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    /**
     * Returns the last date from the entries in the database.
     *
     * @return last date from db
     */
    @Override
    public LocalDateTime getLastDate() {
        LocalDateTime lastDate = null;
        try {
            Statement statement = this.vacancyDBConnect.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(date) as date from vacancy having max(date) notnull");
            if (resultSet.next()) {
                lastDate = resultSet.getTimestamp("date").toLocalDateTime();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return lastDate;
    }

    /**
     * Returns an element from the database with specified id.
     *
     * @param id specified key
     * @return result vacancy.
     */
    @Override
    public Vacancy findById(String id) {
        Vacancy resultVacancy = null;
        try {
            PreparedStatement statement = this.vacancyDBConnect.prepareStatement(
                    "SELECT * FROM vacancy WHERE id = ?"
            );
            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultVacancy = getResultJob(resultSet);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return resultVacancy;
    }

    /**
     * Returns a list of vacancies whose dates are between the start date and end date.
     *
     * @param start date
     * @param end date
     * @return result list<Vacancy>
     */
    @Override
    public List<Vacancy> findJobsBetweenDates(LocalDate start, LocalDate end) {
        List<Vacancy> vacancyList = new ArrayList<>();
        try {
            PreparedStatement statement = this.vacancyDBConnect.prepareStatement(
                    "SELECT * FROM vacancy WHERE date between ? and ?"
            );
            statement.setObject(1, start);
            statement.setObject(2, end);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancyList.add(getResultJob(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancyList;
    }

    /**
     * Creates a new vacancy with information from the database.
     *
     * @param resultSet type ResultSet
     * @return result Vacancy
     * @throws SQLException if the method throws an exception.
     */
    private Vacancy getResultJob(ResultSet resultSet) throws SQLException {
        return new Vacancy(
                resultSet.getInt("id"),
                resultSet.getString("author"),
                resultSet.getString("name"),
                resultSet.getString("text"),
                resultSet.getString("link"),
                resultSet.getString("source"),
                resultSet.getTimestamp("date").toLocalDateTime()
        );
    }

    /**
     * Returns a list of vacancies with the specified name from the database.
     *
     * @param name type String
     * @return result List
     */
    @Override
    public List<Vacancy> findByName(String name) {
        List<Vacancy> vacancyList = new ArrayList<>();
        try {
            PreparedStatement statement = this.vacancyDBConnect.prepareStatement(
                    "SELECT * FROM vacancy WHERE name = ?"
            );
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancyList.add(getResultJob(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancyList;
    }

    /**
     * Returns a list of vacancies with the specified author from the database.
     *
     * @param author type String
     * @return result List
     */
    @Override
    public List<Vacancy> findByAuthor(String author) {
        List<Vacancy> vacancyList = new ArrayList<>();
        try {
            PreparedStatement statement = this.vacancyDBConnect.prepareStatement(
                    "SELECT * FROM vacancy WHERE author = ?"
            );
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vacancyList.add(getResultJob(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancyList;
    }

    /**
     * Returns all vacancies from the database.
     *
     * @return result List
     */
    @Override
    public List<Vacancy> findAll() {
        List<Vacancy> vacancyList = new ArrayList<>();
        try {
            Statement statement = this.vacancyDBConnect.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vacancy");
            while (resultSet.next()) {
                vacancyList.add(getResultJob(resultSet));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancyList;
    }

    /**
     * Deletes all vacancies in the database.
     */
    @Override
    public void deleteAll() {
        try {
            Statement statement = this.vacancyDBConnect.createStatement();
            statement.execute("DELETE FROM vacancy");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Close connection.
     */
    @Override
    public void close() {
        try {
            this.vacancyDBConnect.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
