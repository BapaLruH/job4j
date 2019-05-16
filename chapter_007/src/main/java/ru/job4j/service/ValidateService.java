package ru.job4j.service;

import ru.job4j.model.DbStore;
import ru.job4j.model.Store;
import ru.job4j.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Сlass ValidateService.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public class ValidateService implements Service {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = DbStore.getInstance();

    /**
     * Returns an instance of this object.
     */
    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Adds a new user to the storage.
     *
     * @return result of operation
     */
    @Override
    public String add(User user) {
        String rsl;
        if (user.getName().isEmpty()) {
            rsl = "name is empty";
        } else if (user.getLogin().isEmpty()) {
            rsl = "login is empty";
        } else if (user.getEmail().isEmpty()) {
            rsl = "email is empty";
        } else {
            if (store.add(user)) {
                rsl = "User added";
            } else {
                rsl = "User is not added";
            }
        }
        return rsl;
    }

    /**
     * Updates the user from the storage with the specified id.
     *
     * @param user type User
     * @return result of operation
     */
    @Override
    public String update(User user) {
        String rsl;
        if (store.update(user.getId(), user)) {
            rsl = "User updated";
        } else {
            rsl = "User is not updated";
        }
        return rsl;
    }

    /**
     * Deletes the user from the storage with the specified id.
     *
     * @param id type int
     * @return result of operation
     */
    @Override
    public String delete(int id) {
        String rsl;
        if (store.delete(id)) {
            rsl = "User deleted";
        } else {
            rsl = "User is not deleted";
        }
        return rsl;
    }

    /**
     * Returns all entries.
     *
     * @return result of operation
     */
    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        List<User> users = store.findAll();
        if (!users.isEmpty()) {
            result = users;
        }
        return result;
    }

    /**
     * Returns the record from the storage with the specified id
     *
     * @param id type int
     * @return result of operation
     */
    @Override
    public User findById(int id) {
        return store.findById(id);
    }
}
