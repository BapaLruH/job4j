package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * FactorialTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {

    /**
     * Test calc(?).
     * Factorial 5;
     */
    @Test
    public void whenCalculateFactorial5Then120() {
        Factorial factorial = new Factorial();
        int rsl = factorial.calc(5);
        assertThat(rsl, is(120));
    }

    /**
     * Test calc(?).
     * Factorial 0;
     */
    @Test
    public void whenCalculateFactorial0Then1() {
        Factorial factorial = new Factorial();
        int rsl = factorial.calc(0);
        assertThat(rsl, is(1));
    }
}