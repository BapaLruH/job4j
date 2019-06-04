package ru.job4j.service;

import ru.job4j.model.Role;
import ru.job4j.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ValidateStub implements Service {
    private final Map<Integer, User> userStore = new HashMap<>();
    private final Map<Integer, Role> roleStore = new HashMap<>();
    private int userIds = 0;
    private int roleIds = 0;

    @Override
    public Map<String, String> add(User user) {
        Map<String, String> rsl = new HashMap<>();
        user.setId(this.userIds++);
        this.userStore.put(user.getId(), user);
        if (this.userStore.containsValue(user)) {
            rsl.put("Result", "User added");
            rsl.put("Complete", "true");
        } else {
            rsl.put("Result", "User is not added");
            rsl.put("Complete", "false");
        }
        return rsl;
    }

    @Override
    public Map<String, String> addRole(Role role) {
        Map<String, String> rsl = new HashMap<>();
        role.setId(this.roleIds++);
        this.roleStore.put(role.getId(), role);
        if (this.roleStore.containsValue(role)) {
            rsl.put("Result", "Role added");
            rsl.put("Complete", "true");
        } else {
            rsl.put("Result", "Role is not added");
            rsl.put("Complete", "false");
        }
        return rsl;
    }

    @Override
    public Map<String, String> update(User user) {
        Map<String, String> rsl = new HashMap<>();
        this.userStore.put(user.getId(), user);
        if (this.userStore.containsValue(user)) {
            rsl.put("Result", "User updated");
            rsl.put("Complete", "true");
        } else {
            rsl.put("Result", "User is not updated");
            rsl.put("Complete", "false");
        }
        return rsl;
    }

    @Override
    public Map<String, String> updateRole(Role role) {
        Map<String, String> rsl = new HashMap<>();
        this.roleStore.put(role.getId(), role);
        if (this.roleStore.containsValue(role)) {
            rsl.put("Result", "Role updated");
            rsl.put("Complete", "true");
        } else {
            rsl.put("Result", "Role is not updated");
            rsl.put("Complete", "false");
        }
        return rsl;
    }

    @Override
    public Map<String, String> delete(int id, Function<Integer, Boolean> function) {
        return null;
    }

    @Override
    public Map<String, String> delete(int id) {
        Map<String, String> rsl = new HashMap<>();
        User user = this.userStore.remove(id);
        if (user != null) {
            rsl.put("Result", "User deleted");
            rsl.put("Complete", "true");
        } else {
            rsl.put("Result", "User is not deleted");
            rsl.put("Complete", "false");
        }
        return rsl;
    }

    @Override
    public Map<String, String> deleteRole(int id) {
        Map<String, String> rsl = new HashMap<>();
        Role role = this.roleStore.remove(id);
        if (role != null) {
            rsl.put("Result", "Role deleted");
            rsl.put("Complete", "true");
        } else {
            rsl.put("Result", "Role is not deleted");
            rsl.put("Complete", "false");
        }
        return rsl;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(this.userStore.values());
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findUserByLoginPassword(String login, String password) {
        return null;
    }

    @Override
    public List<Role> findAllRoles() {
        return new ArrayList<>(this.roleStore.values());
    }

    @Override
    public Role findRoleByName(String name) {
        return null;
    }

    @Override
    public boolean isRoleAvailable(User user, Role role) {
        return false;
    }

    @Override
    public boolean isUniqueLogin(String login, int id) {
        return false;
    }

    @Override
    public boolean isUniqueEmail(String email, int id) {
        return false;
    }

    @Override
    public boolean isUniqueRoleName(String name, int id) {
        return false;
    }

    @Override
    public Role findRoleById(int id) {
        return this.roleStore.get(id);
    }
}
