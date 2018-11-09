package ru.job4j.array;

/**
 * Сlass MatrixCheck.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 09.11.2018
 */
public class MatrixCheck {

    /**
     * Method mono.
     * Verification of the elements on the diagonal.
     *
     * @param data type boolean[].
     * @return result type boolean.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0; i < data.length; i++) {
            if (data[0][0] != data[i][i]) {
                result = false;
                break;
            }
            if (data[0][data[0].length - 1] != data[i][data[i].length - 1 - i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
