package ru.job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Сlass Logic3T.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 09.12.2018
 */
public class Logic3T {
    private final Figure3T[][] table;


    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    /**
     * Method isWinnerX.
     * Returns true if x wins.
     *
     * @return result type boolean.
     */
    public boolean isWinnerX() {
        return isWinner(Figure3T::hasMarkX);
    }

    /**
     * Method isWinnerO.
     * Returns true if O wins.
     *
     * @return result type boolean.
     */
    public boolean isWinnerO() {
        return isWinner(Figure3T::hasMarkO);
    }

    /**
     * Method hasGap.
     * Returns true if there are empty cells.
     *
     * @return result type boolean.
     */
    public boolean hasGap() {
        int cellsCount = Arrays.stream(this.table)
                .mapToInt(array -> (int) Arrays.stream(array)
                        .filter(v -> !v.hasMarkX() && !v.hasMarkO())
                        .count())
                .sum();
        return cellsCount > 0;
    }

    private boolean isWinner(Predicate<Figure3T> predicate) {
        var result = false;
        for (int i = 0; i < this.table.length - 1; i++) {
            if (predicate.test(table[0][i])) {
                result = fillBy(predicate, i, 0, 0, 1);
                if (result) {
                    break;
                }
            }
            if (predicate.test(table[i][0])) {
                result = fillBy(predicate, 0, i, 1, 0);
                if (result) {
                    break;
                }
            }
        }
        return fillBy(predicate, 0, 0, 1, 1)
                || fillBy(predicate, this.table.length - 1, 0, -1, 1)
                || result;
    }

    private boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        var result = true;
        for (int i = 0; i < table.length; i++) {
            Figure3T cell = table[startY][startX];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }
}
