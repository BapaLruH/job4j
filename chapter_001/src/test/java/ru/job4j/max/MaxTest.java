package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * MaxTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {

    /**
     * Test max(?, ?).
     * First condition.
     */
    @Test
    public void whenSecondLessFirst() {
        Max maximum = new Max();
        int result = maximum.max(5, 2);
        assertThat(result, is(5));
    }

    /**
     * Test max(?, ?).
     * Second condition.
     */
    @Test
    public void whenFirstLessSecond() {
        Max maximum = new Max();
        int result = maximum.max(1, 2);
        assertThat(result, is(2));
    }

    /**
     * Test max(?, ?).
     * When first equals second.
     */
    @Test
    public void whenFirstEqualSecond() {
        Max maximum = new Max();
        int result = maximum.max(5, 5);
        assertThat(result, is(5));
    }

    /**
     * Test max(?, ?, ?).
     * When the first is greater than second and third.
     */
    @Test
    public void whenFirstGreaterThanSecondAndThird() {
        Max maximum = new Max();
        int result = maximum.max(6, 5, 3);
        assertThat(result, is(6));
    }

    /**
     * Test max(?, ?, ?).
     * When the second is greater than first and third.
     */
    @Test
    public void whenSecondGreaterThanFirstAndThird() {
        Max maximum = new Max();
        int result = maximum.max(6, 8, 3);
        assertThat(result, is(8));
    }

    /**
     * Test max(?, ?, ?).
     * When the third is greater than first and second.
     */
    @Test
    public void whenThirdGreaterThanFirstAndThird() {
        Max maximum = new Max();
        int result = maximum.max(6, 5, 9);
        assertThat(result, is(9));
    }

    /**
     * Test max(?, ?, ?).
     * When the first, second and third are equal.
     */
    @Test
    public void whenFirstAndSecondAndThirdEqual() {
        Max maximum = new Max();
        int result = maximum.max(9, 9, 9);
        assertThat(result, is(9));
    }
}