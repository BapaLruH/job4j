package ru.job4j.search;

import java.util.LinkedList;

/**
 * Сlass PriorityQueue.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.11.2018
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Method put.
     * Adds a task to the list by priority.
     *
     * @param task type Task.
     */
    public void put(Task task) {
        int count = (int) tasks.stream().filter(element -> element.getPriority() < task.getPriority()).count();
        tasks.add(count, task);
    }

    /**
     * Method take.
     * Returns the first task in the list.
     *
     * @return result type Task.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
