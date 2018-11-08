package ru.job4j.loop;

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
        StringBuilder sb = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= column) {
                    sb.append("^");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Method leftTrl.
     * Draws a left triangle in string with height N.
     *
     * @param height type int.
     * @return rsl type String.
     */
    public String leftTrl(int height) {
        StringBuilder sb = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= weight - column - 1) {
                    sb.append("^");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Method pyramid.
     * Draws a pyramid in string with height N.
     *
     * @param height type int.
     * @return rsl type String.
     */
    public String pyramid(int height) {
        StringBuilder sb = new StringBuilder();
        int weight = height * 2 - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
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
