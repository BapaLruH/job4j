package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Node.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 24.12.2018
 */
public class Node<T extends Comparable<T>> {
    private final List<Node<T>> children = new ArrayList<>();
    private final T value;

    public Node(T value) {
        this.value = value;
    }

    /**
     * Appends the specified element of this node.
     * @param child element
     */
    public  void add(Node<T> child) {
        this.children.add(child);
    }

    /**
     * Returns a list of child nodes.
     * @return returns a list of child nodes.
     */
    public List<Node<T>> leaves() {
        return this.children;
    }

    public boolean eqValue(T that) {
        return this.value.compareTo(that) == 0;
    }

    public T getValue() {
        return value;
    }
}
