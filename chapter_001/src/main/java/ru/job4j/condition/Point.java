package ru.job4j.condition;

/**
 * Сlass Point.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 07.11.2018
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Method distanceTo.
     * Calculates the distance between Point one and Point two.
     * @param that type Point.
     * @return distance type double.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(that.x - this.x, 2) + Math.pow(that.y - this.y, 2)
        );
    }
}
