package ru.job4j.convert;

import java.util.*;

/**
 * Сlass SortUser.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 30.11.2018
 */
public class SortUser {
    /**
     * Method sort.
     * Adds users to the user set and sort by age.
     *
     * @param users type List<User>.
     * @return result type Set<User>.
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<>(users);
    }

    /**
     * Method sortNameLength.
     * Sort by name length.
     *
     * @param users type List<User>.
     * @return result type List<User>.
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort(Comparator.comparingInt(o -> o.getName().length()));
        return users;
    }

    /**
     * Method sortByAllFields.
     * Sort by name and age.
     *
     * @param users type List<User>.
     * @return result type List<User>.
     */
    public List<User> sortByAllFields(List<User> users) {
        users.sort(Comparator.comparing(User::getName).thenComparingInt(User::getAge));
        return users;
    }
}
