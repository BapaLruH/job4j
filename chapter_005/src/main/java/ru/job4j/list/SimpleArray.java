package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleArray.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 16.12.2018
 */
public class SimpleArray<E> implements Iterable<E> {

    private int position = 0;
    private Object[] array;
    private int modCount = 0;

    public SimpleArray() {
        this.array = new Object[1];
    }

    /**
     * Ensures that this array contains the specific element.
     *
     * @param data element whose presence in this array is to be ensured
     */
    public void add(E data) {
        if (this.array.length - 1 == position) {
            newCapacity();
        }
        this.array[position++] = data;
        modCount++;
    }

    /**
     * Returns the element at the specified id in the array.
     *
     * @param index index of the element to search
     * @return the element at the specified index
     */
    public E get(int index) {
        if (index > position) {
            throw new NoSuchElementException();
        }
        return (E) this.array[index];
    }

    public int getPosition() {
        return position;
    }

    private void newCapacity() {
        int oldCapacity = this.array.length;
        int newCapacity = oldCapacity + (Math.max(oldCapacity, 2) >> 1);
        this.array = Arrays.copyOf(this.array, newCapacity);
    }

    /**
     * Returns an iterator of this class.
     *
     * @return iterator type Iterator<E>.
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr(modCount);
    }

    private class Itr implements Iterator<E> {

        private int cursor = 0;
        private int size;
        private int expectedModCount;

        Itr(int modCount) {
            this.expectedModCount = modCount;
            this.size = SimpleArray.this.position;
        }

        @Override
        public boolean hasNext() {
            if (this.expectedModCount != SimpleArray.this.modCount) {
                throw new ConcurrentModificationException();
            }
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) SimpleArray.this.array[cursor++];
        }
    }
}
