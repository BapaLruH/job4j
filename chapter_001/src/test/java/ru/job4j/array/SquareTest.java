package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * SquareTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SquareTest {

    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] rslt = square.calculate(bound);
        int[] expect = new int[]{1, 4, 9};
        assertThat(rslt, is(expect));
    }

    @Test
    public void whenBound4Then14916() {
        int bound = 4;
        Square square = new Square();
        int[] rslt = square.calculate(bound);
        int[] expect = new int[]{1, 4, 9, 16};
        assertThat(rslt, is(expect));
    }

    @Test
    public void whenBound4ThenLength4() {
        int bound = 4;
        Square square = new Square();
        int rslt = square.calculate(bound).length;
        int expect = 4;
        assertThat(rslt, is(expect));
    }
}