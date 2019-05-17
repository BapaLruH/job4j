package ru.job4j.service;

import ru.job4j.model.DbStore;
import ru.job4j.model.Role;
import ru.job4j.model.Store;
import ru.job4j.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public Map<String, String> add(User user) {
        Map<String, String> rsl = new HashMap<>();
        if (user.getName().isEmpty()) {
            rsl.put("Result", "Name is empty");
            rsl.put("Complete", "false");
        } else if (user.getLogin().isEmpty()) {
            rsl.put("Result", "Login is empty");
            rsl.put("Complete", "false");
        } else if (user.getEmail().isEmpty()) {
            rsl.put("Result", "Email is empty");
            rsl.put("Complete", "false");
        } else {
            boolean isUniqueLogin = isUniqueLogin(user.getLogin(), 0);
            if (isUniqueLogin && isUniqueEmail(user.getEmail(), 0)) {
                if (store.add(user)) {
                    rsl.put("Result", "User added");
                    rsl.put("Complete", "true");
                } else {
                    rsl.put("Result", "User is not added");
                    rsl.put("Complete", "false");
                }
            } else {
                if (!isUniqueLogin) {
                    rsl.put("Result", "Login is not unique");
                    rsl.put("Complete", "false");
                } else {
                    rsl.put("Result", "Email is not unique");
                    rsl.put("Complete", "false");
                }
            }
        }
        return rsl;
    }

    /**
     * Adds a new role to the storage.
     *
     * @return result of operation
     */
    @Override
    public Map<String, String> addRole(Role role) {
        Map<String, String> rsl = new HashMap<>();
        if (role.getName().isEmpty()) {
            rsl.put("Result", "name is empty");
            rsl.put("Complete", "false");
        } else {
            if (isUniqueRoleName(role.getName(), 0)) {
                if (store.addRole(role)) {
                    rsl.put("Result", "Role added");
                    rsl.put("Complete", "true");
                } else {
                    rsl.put("Result", "Role is not added");
                    rsl.put("Complete", "false");
                }
            } else {
                rsl.put("Result", "Role name is not unique");
                rsl.put("Complete", "false");
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
    public Map<String, String> update(User user) {
        Map<String, String> rsl = new HashMap<>();
        if (user.getName().isEmpty()) {
            rsl.put("Result", "Name is empty");
            rsl.put("Complete", "false");
        } else if (user.getLogin().isEmpty()) {
            rsl.put("Result", "Login is empty");
            rsl.put("Complete", "false");
        } else if (user.getEmail().isEmpty()) {
            rsl.put("Result", "Email is empty");
            rsl.put("Complete", "false");
        } else {
            if (isUniqueLogin(user.getLogin(), user.getId())) {
                if (isUniqueEmail(user.getEmail(), user.getId())) {
                    if (store.update(user.getId(), user)) {
                        rsl.put("Result", "User updated");
                        rsl.put("Complete", "true");
                    } else {
                        rsl.put("Result", "User is not updated");
                        rsl.put("Complete", "false");
                    }
                } else {
                    rsl.put("Result", "User email is not unique");
                    rsl.put("Complete", "false");
                }
            } else {
                rsl.put("Result", "User login is not unique");
                rsl.put("Complete", "false");
            }
        }
        return rsl;
    }

    /**
     * Updates the role from the storage with the specified id.
     *
     * @param role type User
     * @return result of operation
     */
    @Override
    public Map<String, String> updateRole(Role role) {
        Map<String, String> rsl = new HashMap<>();
        if (role.getName().isEmpty()) {
            rsl.put("Result", "Name is empty");
            rsl.put("Complete", "false");
        } else {
            if (isUniqueRoleName(role.getName(), role.getId())) {
                if (store.updateRole(role.getId(), role)) {
                    rsl.put("Result", "Role updated");
                    rsl.put("Complete", "true");
                } else {
                    rsl.put("Result", "Role is not updated");
                    rsl.put("Complete", "false");
                }
            } else {
                rsl.put("Result", "Role name is not unique");
                rsl.put("Complete", "false");
            }
        }
        return rsl;
    }

    /**
     * Deletes the user or role from the storage with the specified id.
     *
     * @param id type int
     * @return result of operation
     */
    @Override
    public Map<String, String> delete(int id, Function<Integer, Boolean> function) {
        Map<String, String> rsl = new HashMap<>();
        if (function.apply(id)) {
            rsl.put("Result", "Deleted");
            rsl.put("Complete", "true");
        } else {
            rsl.put("Result", "Is not deleted");
            rsl.put("Complete", "false");
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
    public Map<String, String> delete(int id) {
        return delete(id, store::delete);
    }

    /**
     * Deletes the role from the storage with the specified id.
     *
     * @param id type int
     * @return result of operation
     */
    @Override
    public Map<String, String> deleteRole(int id) {
        return delete(id, store::deleteRole);
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
     * Returns the record from the storage with the specified id.
     *
     * @param id type int
     * @return result of operation
     */
    @Override
    public User findById(int id) {
        return store.findById(id);
    }

    /**
     * Checks the user login, if the database contains this login, then returns {@code false}, otherwise {@code true}.
     *
     * @param login type String
     * @param id    type int
     * @return result of operation
     */
    @Override
    public boolean isUniqueLogin(String login, int id) {
        boolean isUnique = true;
        List<User> userList = store.findUserByLogin(login);
        if (!userList.isEmpty()) {
            List<User> userListWithoutId = userList.stream().filter(user -> user.getId() != id).collect(Collectors.toList());
            isUnique = userListWithoutId.isEmpty();
        }
        return isUnique;
    }

    /**
     * Checks the user email, if the database contains this email, then returns {@code false}, otherwise {@code true}.
     *
     * @param email type String
     * @param id    type int
     * @return result of operation
     */
    @Override
    public boolean isUniqueEmail(String email, int id) {
        boolean isUnique = true;
        List<User> userList = store.findUserByEmail(email);
        if (!userList.isEmpty()) {
            List<User> userListWithoutId = userList.stream().filter(user -> user.getId() != id).collect(Collectors.toList());
            isUnique = userListWithoutId.isEmpty();
        }
        return isUnique;
    }

    /**
     * Checks the role name, if the database contains this name, then returns {@code false}, otherwise {@code true}.
     *
     * @param name type String
     * @param id   type int
     * @return result of operation
     */
    @Override
    public boolean isUniqueRoleName(String name, int id) {
        boolean isUnique = true;
        Role currentRole = store.findRoleByName(name);
        if (currentRole != null) {
            isUnique = currentRole.getId() == id;
        }
        return isUnique;
    }

    /**
     * Returns the record from the storage with the specified login and password.
     *
     * @param loginOrEmail type String
     * @param password     type String
     * @return result of operation
     */
    @Override
    public User findUserByLoginPassword(String loginOrEmail, String password) {
        User user = null;
        List<User> userList = store.findUserByLogin(loginOrEmail);
        if (userList.isEmpty()) {
            userList = store.findUserByEmail(loginOrEmail);
        }
        if (!userList.isEmpty()) {
            for (User u : userList) {
                if (u.getPassword().equals(password)) {
                    user = u;
                    break;
                }
            }
        }
        return user;
    }

    /**
     * Checks the user {@code user} for the presence of the current role {@code role}.
     *
     * @param user type User
     * @param role type Role
     * @return {@code true} if the user.role contains this role or {@code false}
     */
    @Override
    public boolean isRoleAvailable(User user, Role role) {
        return user.getRoles().contains(role);
    }

    /**
     * Returns the record from the storage with the specified name.
     *
     * @param name type String
     * @return result of operation
     */
    @Override
    public Role findRoleByName(String name) {
        return store.findRoleByName(name);
    }

    /**
     * Returns all entries from the table "roles".
     *
     * @return result of operation
     */
    @Override
    public List<Role> findAllRoles() {
        return store.findAllRoles();
    }

    /**
     * Returns the record from the storage with the specified id.
     *
     * @param id type int
     * @return result of operation
     */
    @Override
    public Role findRoleById(int id) {
        return store.findRoleById(id);
    }
}
