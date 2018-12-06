package ru.job4j.lambda;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * Сlass StreamUsage.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 06.12.2018
 */
public class StreamUsage {
    /**
     * Сlass Task.
     *
     * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
     * @version 001
     * @since 06.12.2018
     */
    public static class Task {
        private final String name;
        private final long spent;

        public Task(String name, long spent) {
            this.name = name;
            this.spent = spent;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Task.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("spent=" + spent)
                    .toString();
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("Bug #1", 100),
                new Task("Task #2", 100),
                new Task("Bug #3", 100)
        );
        List<Task> bugs = tasks.stream().filter(
                task -> task.name.contains("Bug")
        ).collect(Collectors.toList());
        bugs.forEach(System.out::println);

        long total = tasks.stream().map(task -> task.spent).reduce(0L, Long::sum);
    }
}
