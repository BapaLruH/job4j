package ru.job4j.map;

import java.util.*;

/**
 * Class SimpleMap.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 22.12.2018
 */
public class SimpleMap<K, V> implements Iterable<V> {
    private static final float LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] values;
    private int size;
    private int threshold;
    private int modCount;

    public SimpleMap() {
        this.values = new Node[DEFAULT_CAPACITY];
        this.threshold = (int) (this.values.length * LOAD_FACTOR);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @return {@code true} if inserts are complete, else {@code false}
     */
    public boolean insert(K key, V value) {
        int hash = hash(key);
        int index, size;
        boolean result = false;
        if (this.size > this.threshold) {
            this.values = resize();
        }
        size = this.values.length;
        index = (size - 1) & hash;
        if (this.values[index] == null) {
            this.values[index] = new Node<>(hash, key, value);
            result = true;
            this.modCount++;
            this.size++;
        } else {
            Node<K, V> element = this.values[index];
            if (element.hash == hash && (element.key == key || element.key.equals(key))) {
                this.values[index].value = value;
                result = true;
                this.modCount++;
                this.size++;
            }
        }
        return result;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @return the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     */
    public V get(K key) {
        int hash = hash(key);
        int index;
        int size = this.values.length;
        V element = null;
        index = hash & (size - 1);
        if (this.values[index] != null) {
            element = this.values[index].value;
        }
        return element;
    }

    /**
     * Removes the element with the specified key from this map.
     *
     * @return {@code true} if the deletion is complete, either {@code false}
     */
    public boolean delete(K key) {
        int hash = hash(key);
        int index;
        int size = this.values.length;
        boolean result = false;
        index = hash & (size - 1);
        if (this.values[index] != null) {
            this.values[index] = null;
            result = true;
        }
        return result;
    }

    private int hash(K key) {
        int hash = 0;
        if (key != null) {
            hash = key.hashCode();
            hash = hash ^ (hash >>> 16);
        }
        return hash;
    }

    private Node<K, V>[] resize() {
        int oldCapacity = values.length;
        int newCapacity = (oldCapacity << 1);
        Node<K, V>[] newContainer = (Node<K, V>[]) new Node[newCapacity];
        Node<K, V>[] oldContainer = this.values;
        Node<K, V> element;
        for (int i = 0; i < oldCapacity; i++) {
            if (oldContainer[i] != null) {
                element = oldContainer[i];
                newContainer[(newCapacity - 1) & element.hash] = element;
                this.modCount++;
            }
        }
        this.threshold = (int) (newContainer.length * LOAD_FACTOR);
        return newContainer;
    }

    /**
     * Returns the size of the map.
     *
     * @return Returns the size of the map
     */
    public int size() {
        return size;
    }

    /**
     * Returns an iterator of the values of this class.
     *
     * @return iterator type Iterator<V>.
     */
    @Override
    public Iterator<V> iterator() {
        return new Itr(this.modCount);
    }

    private class Itr implements Iterator<V> {
        int cursor = 0;
        final int modCount;

        private Itr(int modCount) {
            this.modCount = modCount;
        }

        @Override
        public boolean hasNext() {
            if (modCount != SimpleMap.this.modCount) {
                throw new ConcurrentModificationException();
            }
            boolean result = false;
            for (int i = cursor; i < values.length; i++) {
                if (values[i] != null) {
                    cursor = i;
                    result = true;
                    break;
                }
            }
            return result;
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return values[cursor++].value;
        }
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }
}
