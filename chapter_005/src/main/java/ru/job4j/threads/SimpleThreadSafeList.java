package ru.job4j.threads;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleList;

import java.util.Iterator;

/**
 * Class SimpleThreadSafeList.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 25.03.2019
 */

@ThreadSafe
public class SimpleThreadSafeList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleList<T> simpleList = new SimpleList<>();

    /**
     * Ensures that this array contains the specified element.
     *
     * @param data element whose presence in this array is to be ensured
     */
    public synchronized void add(T data) {
        this.simpleList.add(data);
    }

    /**
     * Returns the element at the specified index in the array.
     *
     * @param index index of the element to search
     * @return the element at the specified index
     */
    public synchronized T get(int index) {
        return this.simpleList.get(index);
    }

    /**
     * Returns the first element of the array.
     *
     * @return Returns the first element of the array.
     */
    public synchronized T getFirst() {
        return this.simpleList.getFirst();
    }

    /**
     * Returns the last element of the array.
     *
     * @return the last element of the array.
     */
    public synchronized T getLast() {
        return this.simpleList.getLast();
    }

    /**
     * Returns an iterator of this class.
     *
     * @return iterator type Iterator<E>.
     */
    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.simpleList).iterator();
    }

    private synchronized SimpleList<T> copy(SimpleList<T> list) {
        SimpleList<T> resultList = new SimpleList<>();
        list.forEach(resultList::add);
        return resultList;
    }

}
