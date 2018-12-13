package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Сlass Converter.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 13.12.2018
 */
public class Converter {

    /**
     * Method convert.
     * Returns a generic iterator for the list iterator.
     *
     * @param it type Iterator<Iterator<Integer>>.
     * @return iterator type Iterator<Integer>
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {
            private Iterator<Integer> currentIterator;

            @Override
            public boolean hasNext() {
                while (currentIterator == null || !currentIterator.hasNext()) {
                    if (it.hasNext()) {
                        currentIterator = it.next();
                    } else {
                        break;
                    }
                }
                return currentIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("There is no such element");
                }
                return currentIterator.next();
            }
        };
    }
}
