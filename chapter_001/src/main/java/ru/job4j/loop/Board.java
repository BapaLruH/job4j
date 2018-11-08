package ru.job4j.loop;

/**
 * Сlass Board.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Board {

    /**
     * Method paint.
     * Draws a board in string with width and height.
     * @param width type int.
     * @param height type int.
     * @return rsl type int.
     */
    public String paint(int width, int height) {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                if ((j + i) % 2 == 0) {
                    sb.append("X");
                } else {
                   sb.append(" ");
                }
            }
            sb.append(ln);
        }

        return sb.toString();
    }
}
