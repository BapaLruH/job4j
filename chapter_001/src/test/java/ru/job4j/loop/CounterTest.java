package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * CounterTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {

    /**
     * Test add(?, ?)
     * Sum of all even numbers from 1 to 10 = 30
     */
    @Test
    public void whenSumEvenNumbersFromOneToTenThanThirty() {
        Counter counter = new Counter();
        int result = counter.add(1, 10);
        assertThat(result, is(30));
    }
}