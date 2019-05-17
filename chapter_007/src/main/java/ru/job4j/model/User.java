package ru.job4j.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Сlass User.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 05.05.2019
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;
    private String password;
    private List<Role> roles;

    public User() {
        this.createDate = LocalDate.now();
        roles = new ArrayList<>();
    }

    public User(String name, String login, String email, String password) {
        this();
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, String login, String email, String password) {
        this(name, login, email, password);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        assert role != null;
        this.roles.add(role);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && name.equals(user.name)
                && login.equals(user.login)
                && email.equals(user.email)
                && createDate.equals(user.createDate)
                && roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, createDate, roles);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("login='" + login + "'")
                .add("email='" + email + "'")
                .add("createDate=" + createDate)
                .add("roles=" + roles)
                .toString();
    }
}
