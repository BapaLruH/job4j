package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * BubbleSortTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BubbleSortTest {

    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[]{1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
        int[] sortedArrey = bubbleSort.sort(input);
        int[] expect = new int[]{0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(sortedArrey, is(expect));
    }

    @Test
    public void whenSortArrayWithFiveElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        int[] input = new int[]{1, 7, 8, 0, 5};
        int[] sortedArrey = bubbleSort.sort(input);
        int[] expect = new int[]{0, 1, 5, 7, 8};
        assertThat(sortedArrey, is(expect));
    }
}