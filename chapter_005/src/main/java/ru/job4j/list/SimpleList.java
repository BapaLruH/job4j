package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class SimpleList.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 17.12.2018
 */
public class SimpleList<E> implements Iterable<E> {

    private int size;
    private int modCount = 0;
    private Node<E> firstNode;
    private Node<E> lastNode;

    /**
     * Ensures that this array contains the specific element.
     *
     * @param data element whose presence in this array is to be ensured
     */
    public void add(E data) {
        if (firstNode == null) {
            this.firstNode = new Node<>(data, null, null);
            this.lastNode = this.firstNode;
        } else {
            Node<E> last = this.lastNode;
            Node<E> newNode = new Node<>(data, null, last);
            last.next = newNode;
            newNode.prev = last;
            this.lastNode = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * Returns the element at the specified index in the array.
     *
     * @param index index of the element to search
     * @return the element at the specified index
     */
    public E get(int index) {
        if (!isPositionIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        return node(index).data;
    }

    /**
     * Returns the first element of the array.
     *
     * @return Returns the first element of the array.
     */
    public E getFirst() {
        return this.firstNode.data;
    }

    /**
     * Returns the last element of the array.
     *
     * @return the last element of the array.
     */
    public E getLast() {
        return this.lastNode.data;
    }

    private Node<E> node(int index) {
        Node<E> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * Returns an iterator of this class.
     *
     * @return iterator type Iterator<E>.
     */
    @Override
    public Iterator<E> iterator() {
        return new Itr(modCount, size);
    }

    private class Itr implements Iterator<E> {
        private int cursor = 0;
        private final int size;
        private final int expectedModCount;

        Itr(int expectedModCount, int size) {
            this.expectedModCount = expectedModCount;
            this.size = size;
        }

        @Override
        public boolean hasNext() {
            if (expectedModCount != SimpleList.this.modCount) {
                throw new ConcurrentModificationException();
            }
            return cursor < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return node(cursor++).data;
        }
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
