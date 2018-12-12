package ru.job4j.iterators;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MatrixIteratorTest {

    @Test
    public void whenMatrix2x2ThenResult() {
        int[][] input = new int[][]{
                {1, 2},
                {3, 4}
        };
        MatrixIterator matrixIterator = new MatrixIterator(input);
        int count = 1;
        while (matrixIterator.hasNext()) {
            assertThat(matrixIterator.next(), is(count++));
        }
    }

    @Test
    public void whenMatrixHas5RowsAndAnyNumberOfCellsThenResult() {
        int[][] input = new int[][]{
                {1, 2, 3},
                {4},
                {5, 6, 7, 8},
                {9, 10},
        };
        int count = 1;
        MatrixIterator matrixIterator = new MatrixIterator(input);
        while (matrixIterator.hasNext()) {
            assertThat(matrixIterator.next(), is(count++));
        }
    }

}