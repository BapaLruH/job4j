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
    private City city;
    private List<Role> roles;

    public User() {
        this.createDate = LocalDate.now();
        roles = new ArrayList<>();
    }

    public User(String name, String login, String email, String password, City city) {
        this();
        this.name = name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.city = city;
    }

    public User(int id, String name, String login, String email, String password, City city) {
        this(name, login, email, password, city);
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
                && Objects.equals(name, user.name)
                && Objects.equals(login, user.login)
                && Objects.equals(email, user.email)
                && Objects.equals(createDate, user.createDate)
                && Objects.equals(password, user.password)
                && Objects.equals(city, user.city)
                && Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, email, createDate, password, city, roles);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("login='" + login + "'")
                .add("email='" + email + "'")
                .add("createDate=" + createDate)
                .add("password='" + password + "'")
                .add("city=" + city)
                .add("roles=" + roles)
                .toString();
    }
}
