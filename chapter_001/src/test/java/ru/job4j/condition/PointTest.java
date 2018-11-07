package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * PointTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PointTest {

    /**
     * Test distanceTo.
     */
    @Test
    public void whenFirstPointX2Y2AndSecondPointX2Y4Then2() {
        Point firstPoint = new Point(2, 2);
        Point secondPoint = new Point(2, 4);
        double distance = firstPoint.distanceTo(secondPoint);
        double expected = 2D;
        assertThat(distance, is(expected));
    }
}