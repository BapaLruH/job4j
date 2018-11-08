package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Сlass Paint.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Paint {

    /**
     * Method rightTrl.
     * Draws a right triangle in string with height N.
     *
     * @param height type int.
     * @return rsl type String.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Method leftTrl.
     * Draws a left triangle in string with height N.
     *
     * @param height type int.
     * @return rsl type String.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Method pyramid.
     * Draws a pyramid in string with height N.
     *
     * @param height type int.
     * @return rsl type String.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    /**
     * Method loopBy.
     * Draws a shape in a string with height N.
     *
     * @param height type int.
     * @return rsl type String.
     */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predicate) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predicate.test(row, column)) {
                    sb.append("^");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
