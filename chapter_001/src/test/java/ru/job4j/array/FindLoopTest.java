package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * FindLoopTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {

    @Test
    public void whenArrayContains5ThenIndex0() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{5, 10, 15};
        int result = find.indexOf(input, 5);
        int expect = 0;
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayNotContainsFiveThenMinusOne() {
        FindLoop find = new FindLoop();
        int[] input = new int[]{6, 10, 15};
        int result = find.indexOf(input, 5);
        int expect = -1;
        assertThat(result, is(expect));
    }

}