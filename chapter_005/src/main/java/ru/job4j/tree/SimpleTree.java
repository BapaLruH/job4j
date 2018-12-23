package ru.job4j.tree;

import java.util.Optional;

/**
 * Class SimpleTree.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 24.12.2018
 */
public interface SimpleTree<T extends Comparable<T>> extends Iterable<T> {

    boolean add(T parent, T child);

    Optional<Node<T>> findBy(T value);
}
