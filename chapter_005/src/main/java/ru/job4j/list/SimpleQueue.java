package ru.job4j.list;

import java.util.EmptyStackException;

/**
 * Class SimpleQueue.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 18.12.2018
 */
public class SimpleQueue<T> {
    private SimpleStack<T> in;
    private SimpleStack<T> out;

    public SimpleQueue() {
        this.in = new SimpleStack<>();
        this.out = new SimpleStack<>();
    }

    /**
     * Pushes an value onto the bottom of this queue.
     *
     * @param value the data to be pushed onto this queue
     */
    public void push(T value) {
        this.in.push(value);
    }

    /**
     * Removes the object at the top of this queue and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this queue.
     * @throws EmptyStackException  if this stack is empty.
     */
    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                this.out.push(this.in.poll());
            }
        }
        return this.out.poll();
    }

    public int size() {
        return this.in.size() + this.out.size();
    }
}
