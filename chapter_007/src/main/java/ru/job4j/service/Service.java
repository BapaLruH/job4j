package ru.job4j.service;

import ru.job4j.model.User;
/**
 * Interface Service.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public interface Service {
    String add(User user);

    String update(User user);

    String delete(int id);

    String findAll();

    String findById(int id);
}
