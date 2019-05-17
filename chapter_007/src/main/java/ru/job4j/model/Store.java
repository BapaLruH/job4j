package ru.job4j.model;

import java.util.List;

/**
 * Interface Store.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public interface Store {
    boolean add(User user);

    boolean addRole(Role role);

    boolean update(int id, User user);

    boolean updateRole(int id, Role role);

    boolean delete(int id);

    boolean deleteRole(int id);

    List<User> findAll();

    User findById(int id);

    List<User> findUserByLogin(String login);

    List<User> findUserByEmail(String email);

    Role findRoleByName(String name);

    List<Role> findAllRoles();

    Role findRoleById(int id);
}
