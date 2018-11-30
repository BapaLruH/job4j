package ru.job4j.convert;

import java.util.Objects;
import java.util.Random;

/**
 * Сlass User.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.11.2018
 */
public class User implements Comparable<User> {
    private int id;
    private String name;
    private int age;
    private static final Random RANDOM = new Random();

    public User(String name) {
        this.id = RANDOM.nextInt();
        this.name = name;
    }

    public User(String name, int age) {
        this(name);
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
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
                && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.getAge();
    }
}
