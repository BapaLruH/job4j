package ru.job4j.convert;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
}
