package ru.job4j.nonblockingalgorithm;

import java.util.Objects;
import java.util.StringJoiner;

public class Base {
    private int id;
    private int version;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
        this.version = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id
                && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Base.class.getSimpleName() + "[", "]")
                .add("version=" + version)
                .add("name='" + name + "'")
                .toString();
    }
}
