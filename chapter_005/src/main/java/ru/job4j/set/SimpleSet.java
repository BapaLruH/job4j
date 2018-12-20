package ru.job4j.set;

import ru.job4j.list.SimpleArray;

import java.util.Iterator;

/**
 * Class SimpleSet.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 19.12.2018
 */
public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> array;

    public SimpleSet() {
        this.array = new SimpleArray<>();
    }

    /**
     * Ensures that this set contains the specific element.
     *
     * @param data element whose presence in this set is to be ensured
     */
    public void add(T data) {
        if (!contains(data)) {
            array.add(data);
        }
    }

    /**
     * Returns {@code true} if this array contains the specified element.
     *
     * @return {@code true} if this array contains the specified element
     */
    public boolean contains(T data) {
        boolean dupl = false;
        for (T value : array) {
            if (value != null && value.equals(data)) {
                dupl = true;
                break;
            }
        }
        return dupl;
    }

    /**
     * Returns the number of elements in this array.
     *
     * @return the number of elements in this array
     */
    public int size() {
        return array.getPosition();
    }

    /**
     * Returns an iterator of this class.
     *
     * @return iterator type Iterator<E>.
     */
    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
