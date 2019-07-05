package ru.job4j.model;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
             PreparedStatement userSt = connection.prepareStatement("INSERT INTO users(name, login, email, password, city_id) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            userSt.setString(1, user.getName());
            userSt.setString(2, user.getLogin());
            userSt.setString(3, user.getEmail());
            userSt.setString(4, user.getPassword());
            userSt.setInt(5, user.getCity().getId());
            userSt.execute();
            try (ResultSet resultSet = userSt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(1));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            addUserRoles(user, connection);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Adds a new role to the storage.
     *
     * @param role type Role
     * @return {@code true} if a role has been added or {@code false}
     */
    @Override
    public boolean addRole(Role role) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement roleSt = connection.prepareStatement("INSERT INTO roles(name, default_role) values(?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            roleSt.setString(1, role.getName());
            roleSt.setBoolean(2, role.isDefaultRole());
            roleSt.execute();
            try (ResultSet resultSet = roleSt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    role.setId(resultSet.getInt(1));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Adds a new country or city to the storage.
     *
     * @param city type City
     * @return {@code true} if a city has been added or {@code false}
     */
    @Override
    public boolean addCountryCity(City city) {
        boolean result = false;
        int countryId = city.getCountry().getId();
        if (countryId == 0) {
            Country country = findCountryByName(city.getCountry().getName());
            if (Objects.isNull(country)) {
                countryId = addNewCountry(city.getCountry().getName());
            } else {
                countryId = country.getId();
            }
        }
        if (city.getId() == 0) {
            addNewCity(city, countryId);
            result = true;
        }
        return result;
    }

    /**
     * Adds a new city to the cities database.
     *
     * @param city      type City
     * @param countryId type int
     */
    private void addNewCity(City city, int countryId) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement citySt = connection.prepareStatement("INSERT INTO cities(name, country_id) values (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            citySt.setString(1, city.getName());
            citySt.setInt(2, countryId);
            citySt.execute();
            try (ResultSet resultSet = citySt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    city.setId(resultSet.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new country to the countries database and return the record id.
     *
     * @param name type String
     * @return id type int
     */
    private int addNewCountry(String name) {
        int resultId = 0;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement countrySt = connection.prepareStatement("INSERT INTO countries(name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            countrySt.setString(1, name);
            countrySt.execute();
            try (ResultSet resultSet = countrySt.getGeneratedKeys()) {
                if (resultSet.next()) {
                    resultId = resultSet.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultId;
    }

    /**
     * Adds a new relationships to the role_user database.
     *
     * @param user       type User
     * @param connection sqlConnection.
     */
    private void addUserRoles(User user, Connection connection) {
        try (PreparedStatement userRoleSt = connection.prepareStatement("INSERT INTO role_user(role_id, user_id) values(?, ?)")) {
            connection.setAutoCommit(false);
            List<Role> roles = user.getRoles();
            int userId = user.getId();
            for (Role role : roles) {
                userRoleSt.setInt(1, role.getId());
                userRoleSt.setInt(2, userId);
                userRoleSt.addBatch();
            }
            userRoleSt.executeBatch();
            connection.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
             PreparedStatement st = connection.prepareStatement("UPDATE users SET name = ?, login = ?, email = ?, password = ?, city_id = ? WHERE id = ?")) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setInt(5, user.getCity().getId());
            st.setInt(6, id);
            st.executeUpdate();
            try (PreparedStatement userRoleSt = connection.prepareStatement("DELETE FROM role_user WHERE user_id = ?")) {
                userRoleSt.setInt(1, id);
                userRoleSt.executeUpdate();
                addUserRoles(user, connection);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Updates the role from the storage with the specified id.
     *
     * @param id   type int
     * @param role type Role
     * @return {@code true} if a role has been updated or {@code false}
     */
    @Override
    public boolean updateRole(int id, Role role) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("UPDATE roles SET name = ?, default_role = ? WHERE id = ?")) {
            st.setString(1, role.getName());
            st.setBoolean(2, role.isDefaultRole());
            st.setInt(3, id);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Updates the country or city from the storage with the specified id.
     * If the country name doesn't exist and
     * if the request contains an id, then replaced the name of the element from the database,
     * else creates new entity.
     * else
     * if the request doesn't contain an id, the country id of that city is replaced.
     * <p>
     * Search city by name, if the result is not equals to the {@code city}, then the name of the city from the database is replaced.
     *
     * @param id   type int
     * @param city type City
     * @return {@code true} if a city has been updated or {@code false}
     */
    @Override
    public boolean updateCountryCity(int id, City city) {
        boolean result = false;

        Country country = city.getCountry();

        Country countryByName = findCountryByName(country.getName());
        if (Objects.nonNull(countryByName)) {
            if (countryByName.getId() != country.getId()) {
                country.setId(countryByName.getId());
            }
        } else {
            if (country.getId() != 0) {
                try (Connection connection = DATA_SOURCE.getConnection();
                     PreparedStatement updCountrySt = connection.prepareStatement("UPDATE countries SET name = ? WHERE id = ?")) {
                    updCountrySt.setString(1, country.getName());
                    updCountrySt.setInt(2, country.getId());
                    updCountrySt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                country.setId(addNewCountry(country.getName()));
            }
        }

        City cityById = findCityById(city.getId());
        if (Objects.nonNull(cityById) && !cityById.equals(city)) {
            try (Connection connection = DATA_SOURCE.getConnection();
                 PreparedStatement citySt = connection.prepareStatement("UPDATE cities SET name = ?, country_id = ? WHERE id = ?")) {
                citySt.setString(1, city.getName());
                citySt.setInt(2, city.getCountry().getId());
                citySt.setInt(3, city.getId());
                citySt.executeUpdate();
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
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
     * Deletes the role from the storage with the specified id.
     *
     * @param id type int
     * @return {@code true} if a role has been deleted or {@code false}
     */
    @Override
    public boolean deleteRole(int id) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM roles WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes the city from the storage with the specified id.
     *
     * @param id type int
     * @return {@code true} if a city has been deleted or {@code false}
     */
    @Override
    public boolean deleteCity(int id) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM cities WHERE id = ?")) {
            st.setInt(1, id);
            st.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes the country from the storage with the specified id.
     *
     * @param id type int
     * @return {@code true} if a country has been deleted or {@code false}
     */
    @Override
    public boolean deleteCountry(int id) {
        boolean result = false;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("DELETE FROM countries WHERE id = ?")) {
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
             PreparedStatement st = connection.prepareStatement(
                     "SELECT users.*, roles.id as role_id, roles.name as role_name, roles.default_role, cities.id as city_id, cities.name as city_name, countries.id as country_id, countries.name as country_name "
                             + "FROM users "
                             + "LEFT JOIN role_user ON users.id = role_user.user_id "
                             + "LEFT JOIN roles ON role_user.role_id = roles.id "
                             + "LEFT JOIN cities ON users.city_id = cities.id "
                             + "LEFT JOIN countries ON cities.country_id = countries.id "
                             + "ORDER BY users.id")) {
            ResultSet resultSet = st.executeQuery();
            userList = createNewUsers(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Creates a new users with data from the database{@code ResultSet}
     *
     * @param resultSet type ResultSet
     * @return userList type List<User>
     */
    private List<User> createNewUsers(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        int currentId = -1;
        User currentUser = null;
        while (resultSet.next()) {
            int userDbId = resultSet.getInt("id");
            if (currentId != userDbId) {
                if (currentUser != null) {
                    userList.add(currentUser);
                }
                currentId = userDbId;
                currentUser = new User(userDbId,
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        new City(
                                resultSet.getInt("city_id"),
                                resultSet.getString("city_name"),
                                new Country(resultSet.getInt("country_id"),
                                        resultSet.getString("country_name")
                                )
                        )
                );
                currentUser.addRole(
                        new Role(
                                resultSet.getInt("role_id"),
                                resultSet.getString("role_name"),
                                resultSet.getBoolean("default_role")
                        )
                );
            } else {
                if (currentUser != null) {
                    currentUser.addRole(
                            new Role(
                                    resultSet.getInt("role_id"),
                                    resultSet.getString("role_name"),
                                    resultSet.getBoolean("default_role")
                            )
                    );
                }
            }
        }
        if (currentUser != null) {
            userList.add(currentUser);
        }
        return userList;
    }

    /**
     * Returns the record from the storage with the specified id.
     *
     * @param id type int
     * @return {@code User} if a user has been found or {@code null}
     */
    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT users.*, roles.id as role_id, roles.name as role_name, roles.default_role, cities.id as city_id, cities.name as city_name, countries.id as country_id, countries.name as country_name "
                             + "FROM users "
                             + "LEFT JOIN role_user ON users.id = role_user.user_id "
                             + "LEFT JOIN roles ON role_user.role_id = roles.id "
                             + "LEFT JOIN cities ON users.city_id = cities.id "
                             + "LEFT JOIN countries ON cities.country_id = countries.id "
                             + "WHERE users.id = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            List<User> userList = createNewUsers(resultSet);
            if (userList.size() > 0) {
                user = userList.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Returns the records from the storage with the specified email.
     *
     * @param email type String
     * @return {@code List<User>} if a users has been found or {@code empty list}
     */
    @Override
    public List<User> findUserByEmail(String email) {
        return findUsers(email, "WHERE users.email = ?");
    }

    /**
     * Returns the records from the database with a condition {@code expression}.
     *
     * @param param      parameter
     * @param expression sql condition
     * @return {@code List<User>} if a users has been found or {@code empty list}
     */
    private List<User> findUsers(String param, String expression) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     new StringBuilder()
                             .append("SELECT users.*, roles.id as role_id, roles.name as role_name, roles.default_role, ")
                             .append("cities.id as city_id, cities.name as city_name, countries.id as country_id, countries.name as country_name ")
                             .append("FROM users ")
                             .append("LEFT JOIN role_user ON users.id = role_user.user_id ")
                             .append("LEFT JOIN roles ON role_user.role_id = roles.id ")
                             .append("LEFT JOIN cities ON users.city_id = cities.id ")
                             .append("LEFT JOIN countries ON cities.country_id = countries.id ")
                             .append(expression)
                             .toString()
             )) {
            st.setString(1, param);
            ResultSet resultSet = st.executeQuery();
            userList = createNewUsers(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * Returns the records from the storage with the specified login.
     *
     * @param login type String
     * @return {@code List<User>} if a users has been found or {@code empty list}
     */
    @Override
    public List<User> findUserByLogin(String login) {
        return findUsers(login, "WHERE users.login = ?");
    }

    /**
     * Returns all roles entries.
     *
     * @return {@code List<Role>}
     */
    @Override
    public List<Role> findAllRoles() {
        List<Role> roleList = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM roles ORDER BY id")) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                roleList.add(
                        new Role(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getBoolean("default_role")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }

    /**
     * Returns the records from the roles_database with the specified name.
     *
     * @param name type String
     * @return {@code Role} if a users has been found or {@code null}
     */
    @Override
    public Role findRoleByName(String name) {
        Role role = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("SELECT * FROM roles WHERE name = ?")) {
            st.setString(1, name);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                role = new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("default_role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Returns the record from the storage with the specified id.
     *
     * @param id type int
     * @return {@code Role} if a role has been found or {@code null}
     */
    @Override
    public Role findRoleById(int id) {
        Role role = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT * FROM roles WHERE roles.id = ?")) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                role = new Role(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getBoolean("default_role")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Returns all cities.
     *
     * @return {@code List<City>}
     */
    @Override
    public List<City> findAllCities() {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT city.id, city.name, country.id as count_id, country.name as count_name "
                             + "FROM cities city "
                             + "LEFT JOIN countries country "
                             + "ON city.country_id = country.id "
                             + "ORDER BY country.id, city.name")
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cities.add(
                        new City(
                                rs.getInt("id"),
                                rs.getString("name"),
                                new Country(
                                        rs.getInt("count_id"),
                                        rs.getString("count_name")
                                )
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    /**
     * Returns all countries.
     *
     * @return {@code List<Country>}
     */
    @Override
    public List<Country> findAllCountries() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT country.id as count_id, country.name as count_name "
                             + "FROM countries country "
                             + "ORDER BY country.id")
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                countries.add(new Country(
                                rs.getInt("count_id"),
                                rs.getString("count_name")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    /**
     * Returns all cities from database with the specified name.
     *
     * @return {@code City} or {@code null} if the city doesn't exist.
     */
    @Override
    public City findCityByName(String name) {
        City city = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement citySt = connection.prepareStatement(
                     "SELECT cities.*, countries.name as country_name "
                             + "FROM cities "
                             + "LEFT JOIN countries "
                             + "ON countries.id = cities.country_id "
                             + "WHERE cities.name = ?")) {
            citySt.setString(1, name);
            ResultSet resultSet = citySt.executeQuery();
            if (resultSet.next()) {
                city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Country(
                                resultSet.getInt("country_id"),
                                resultSet.getString("country_name")
                        )
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    /**
     * Returns all cities from database with the specified name.
     *
     * @return {@code Country} or {@code null} if the country doesn't exist.
     */
    @Override
    public Country findCountryByName(String name) {
        Country country = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement countrySt = connection.prepareStatement(
                     "SELECT * FROM countries WHERE name = ?")) {
            countrySt.setString(1, name);
            ResultSet resultSet = countrySt.executeQuery();
            while (resultSet.next()) {
                country = new Country(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return country;
    }

    /**
     * Returns the record from the storage with the specified id.
     *
     * @param id type int
     * @return {@code City} if a city has been found or {@code null}
     */
    @Override
    public City findCityById(int id) {
        City cityDb = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement citySt = connection.prepareStatement(
                     "SELECT cities.*, countries.name as country_name "
                             + "FROM cities "
                             + "LEFT JOIN countries "
                             + "ON countries.id = cities.country_id "
                             + "WHERE cities.id = ?")) {
            citySt.setInt(1, id);
            ResultSet resultSet = citySt.executeQuery();
            if (resultSet.next()) {
                cityDb = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        new Country(
                                resultSet.getInt("country_id"),
                                resultSet.getString("country_name")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cityDb;
    }

    /**
     * Returns all cities from database with the specified country id.
     *
     * @return {@code Country} or {@code null} if the country doesn't exist.
     */
    @Override
    public List<City> findCitiesByCountryId(int id) {
        List<City> cities = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(
                     "SELECT city.id, city.name, country.id as count_id, country.name as count_name "
                             + "FROM cities city "
                             + "LEFT JOIN countries country "
                             + "ON city.country_id = country.id "
                             + "WHERE country.id = ? "
                             + "ORDER BY city.id")
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                cities.add(
                        new City(
                                rs.getInt("id"),
                                rs.getString("name"),
                                new Country(
                                        rs.getInt("count_id"),
                                        rs.getString("count_name")
                                )
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }
}
