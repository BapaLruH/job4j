package ru.job4j.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Сlass MatrixIterator.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 12.12.2018
 */
public class MatrixIterator implements Iterator<Integer> {
    private final int[][] values;
    private int cell = 0;
    private int row = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (values.length - 1 > row) {
            result = true;
        } else if (values.length - 1 == row && values[row].length > cell) {
            result = true;
        }
        return result;
    }

    @Override
    public Integer next() {
        int result;
        if (!hasNext()) {
            throw new NoSuchElementException("There is no such element");
        }
        if (values[row].length > cell) {
            result = values[row][cell++];
        } else {
            cell = 0;
            result = values[++row][cell++];
        }
        return result;
    }
}
