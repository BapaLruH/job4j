package ru.job4j.student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Сlass CollectUsers.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 07.12.2018
 */
public class CollectUsers {

    /**
     * Method levelOf.
     * Returns a list of users whose average is higher(@bound).
     *
     * @param students type List<Student>.
     * @param bound    type int.
     * @return result type List<Student>.
     */
    public List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .sorted((o1, o2) -> Integer.compare(o2.getScope(), o1.getScope()))
                .flatMap(Stream::ofNullable)
                .takeWhile(v -> v.getScope() > bound)
                .collect(Collectors.toList());
    }
}
