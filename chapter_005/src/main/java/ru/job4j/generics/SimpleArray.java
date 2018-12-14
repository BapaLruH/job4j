package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Сlass SimpleArray.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 14.12.2018
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int position = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Method add.
     * Adds a new element to the array.
     *
     * @param model type T.
     */
    public void add(T model) {
        this.array[this.position++] = model;
    }

    /**
     * Method set.
     * Sets an array element by index.
     *
     * @param index type int.
     * @param model type T.
     */
    public void set(int index, T model) {
        this.array[index] = model;
    }

    /**
     * Method remove.
     * Removes an array element by index.
     *
     * @param index type int.
     */
    public void remove(int index) {
        if (position > 0) {
        System.arraycopy(this.array, index + 1, this.array, index, this.array.length - index - 1);
            this.array[--position] = null;
        }
    }

    /**
     * Method get.
     * Returns an array element by index.
     *
     * @param index type int.
     * @return element type T.
     */
    public T get(int index) {
        return (T) this.array[index];
    }

    /**
     * Method size.
     * Returns the size of the array.
     *
     * @return size type int.
     */
    public int size() {
        return this.position;
    }

    /**
     * Method iterator.
     * Returns an iterator of this class.
     *
     * @return iterator type Iterator<T>.
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    /**
     * Сlass Itr.
     *
     * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
     * @version 001
     * @since 14.12.2018
     */
    private class Itr implements Iterator<T> {
        private int cursor;
        private int size;

        Itr() {
            this.cursor = 0;
            this.size = SimpleArray.this.position;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (cursor > size) {
                throw new NoSuchElementException();
            }
            return (T) SimpleArray.this.array[cursor++];
        }
    }

}
