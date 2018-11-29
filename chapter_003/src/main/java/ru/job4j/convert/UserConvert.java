package ru.job4j.convert;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сlass UserConvert.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.11.2018
 */
public class UserConvert {
    /**
     * Method process.
     * Puts a values from the list into Map.
     *
     * @param list type List<User> list.
     * @return result type HashMap<Integer, String>.
     */
    public HashMap<Integer, String> process(List<User> list) {
        return (HashMap<Integer, String>) list.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
    }
}
