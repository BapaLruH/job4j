package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TurnTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TurnTest {

    @Test
    public void whenArrayWasFlippedOddLengthThenTrue() {
        Turn turn = new Turn();
        int[] array = new int[]{1, 2, 3, 4, 5};
        int[] result = turn.back(array);
        int[] expect = new int[]{5, 4, 3, 2, 1};
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayWasFlippedEvenLengthThenTrue() {
        Turn turn = new Turn();
        int[] array = new int[]{1, 2, 3, 4};
        int[] result = turn.back(array);
        int[] expect = new int[]{4, 3, 2, 1};
        assertThat(result, is(expect));
    }
}