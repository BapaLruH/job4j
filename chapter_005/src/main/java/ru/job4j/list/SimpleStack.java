package ru.job4j.list;

import java.util.EmptyStackException;

/**
 * Class Store.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 17.12.2018
 */
public class SimpleStack<E> extends SimpleArrayList {

    private final SimpleArrayList<E> list;

    public SimpleStack() {
        this.list = new SimpleArrayList<>();
    }

    /**
     * Pushes an data onto the top of this stack.
     *
     * @param data the data to be pushed onto this stack
     */
    public void push(E data) {
        this.list.add(data);
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     *
     * @return The object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public E poll() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return this.list.delete();
    }

    /**
     * Returns the size of the stack.
     *
     * @return Returns the size of the stack
     */
    public int size() {
        return this.list.getSize();
    }

    /**
     * Tests if this stack is empty.
     *
     * @return {@code true} if and only if this stack contains
     * no items; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }
}
