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

    boolean update(int id, User user);

    boolean delete(int id);

    List<User> findAll();

    User findById(int id);
}
