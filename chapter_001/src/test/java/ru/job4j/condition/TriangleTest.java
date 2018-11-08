package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * TriangleTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class TriangleTest {

    /**
     * Test area.
     * When triangle exist.
     */
    @Test
    public void whenTriangleExist() {
        Point a = new Point(2, 4);
        Point b = new Point(5, 7);
        Point c = new Point(8, 2);

        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();

        assertThat(result, closeTo(12, 0.1));
    }

    /**
     * Test area.
     * When triangle not exist.
     */
    @Test
    public void whenTriangleNotExist() {
        Point a = new Point(2, 2);
        Point b = new Point(2, 4);
        Point c = new Point(2, 8);

        Triangle triangle = new Triangle(a, b, c);
        double result = triangle.area();

        assertThat(result, is(-1.0));
    }

}