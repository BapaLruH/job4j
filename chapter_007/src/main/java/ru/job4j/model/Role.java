package ru.job4j.model;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Сlass Role.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 15.05.2019
 */
public class Role {
    private int id;
    private String name;
    private boolean defaultRole;

    public Role(String name, boolean defaultRole) {
        this.name = name;
        this.defaultRole = defaultRole;
    }

    public Role(int id, String name, boolean defaultRole) {
        this.id = id;
        this.name = name;
        this.defaultRole = defaultRole;
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

    public boolean isDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id
                && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Role.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("defaultRole=" + defaultRole)
                .toString();
    }
}
