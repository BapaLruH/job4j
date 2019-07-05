package ru.job4j.service;

import ru.job4j.model.City;
import ru.job4j.model.Country;
import ru.job4j.model.Role;
import ru.job4j.model.User;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Interface Service.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public interface Service {
    Map<String, String> add(User user);

    Map<String, String> addRole(Role role);

    Map<String, String> addCountryCity(City city);

    Map<String, String> update(User user);

    Map<String, String> updateRole(Role role);

    Map<String, String> updateCountryCity(int id, City city);

    Map<String, String> delete(int id, Function<Integer, Boolean> function);

    Map<String, String> delete(int id);

    Map<String, String> deleteRole(int id);

    Map<String, String> deleteCity(int id);

    Map<String, String> deleteCountry(int id);

    List<User> findAll();

    User findById(int id);

    User findUserByLoginPassword(String login, String password);

    List<Role> findAllRoles();

    Role findRoleByName(String name);

    boolean isRoleAvailable(User user, Role role);

    boolean isUniqueLogin(String login, int id);

    boolean isUniqueEmail(String email, int id);

    boolean isUniqueRoleName(String name, int id);

    Role findRoleById(int id);

    List<City> findAllCities();

    City findCityById(int id);

    List<City> findCitiesByCountryId(int id);

    List<Country> findAllCountries();
}