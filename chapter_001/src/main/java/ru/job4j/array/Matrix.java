package ru.job4j.array;

/**
 * Сlass Matrix.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 09.11.2018
 */
public class Matrix {

    /**
     * Method multiple.
     * Creates a matrix of size N and populates it with indexes multiplication.
     *
     * @param size type int.
     * @return table type int[][].
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = (j + 1) * (i + 1);
            }
        }
        return table;
    }
}
