package ru.job4j.tree;

import java.util.*;

/**
 * Class Tree.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 24.12.2018
 */
public class Tree<T extends Comparable<T>> implements SimpleTree<T> {

    private Node<T> root;

    public Tree(T root) {
        this.root = new Node<>(root);
    }

    /**
     * Ensures that this tree contains the specific element
     * if the tree contains a parent element
     * else creates a parent element and adds the child to the list.
     *
     * @param parent element.
     * @param child element whose presence in this tree is to be ensured.
     * @return {@code true} if an element has been added else {@code false}
     */
    @Override
    public boolean add(T parent, T child) {
        boolean rsl = false;
        Optional<Node<T>> resultNode = findBy(parent);
        if (resultNode.isPresent()) {
            Node<T> result = findBy(child).orElse(null);
            if (result == null) {
                resultNode.get().add(new Node<>(child));
                rsl = true;
            }
        } else {
            Node<T> parentNode = new Node<>(parent);
            parentNode.leaves().add(new Node<>(child));
            this.root.leaves().add(parentNode);
            rsl = true;
        }
        return rsl;
    }

    /**
     * Searches for an element in the tree, and returns it.
     *
     * @param value you want to find.
     * @return {@code Optional.empty()} if the element is not found.
     */
    @Override
    public Optional<Node<T>> findBy(T value) {
        Optional<Node<T>> rsl = Optional.empty();
        Queue<Node<T>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<T> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<T> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Returns an iterator of the values of this class.
     *
     * @return iterator type Iterator<T>.
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        Queue<Node<T>> nodes = new LinkedList<>();

        Itr() {
            nodes.offer(root);
        }

        @Override
        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        @Override
        public T next() {
            Node<T> result;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            result = nodes.poll();
            for (Node<T> node: result.leaves()) {
                nodes.offer(node);
            }
            return result.getValue();
        }
    }
}
