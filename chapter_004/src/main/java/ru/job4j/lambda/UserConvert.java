package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;

/**
 * Сlass UserConvert.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 03.12.2018
 */
public class UserConvert {
    /**
     * Сlass User.
     *
     * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
     * @version 001
     * @since 03.12.2018
     */
    public static class User {
        private final String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .toString();
        }
    }

    /**
     * Method convert.
     * Creates a new user by name from the names and add to the list of results.
     *
     * @param names  type List<String>.
     * @param op     type Function<String, User>.
     */
    public List<User> convert(List<String> names, Function<String, User> op) {
        List<User> users = new ArrayList<>();
        names.forEach(
                n -> users.add(op.apply(n))
        );
        return users;
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Petr", "Nick", "Ban");
        UserConvert users = new UserConvert();
        List<User> data = users.convert(names, User::new);
        data.forEach(System.out::println);
    }
}
