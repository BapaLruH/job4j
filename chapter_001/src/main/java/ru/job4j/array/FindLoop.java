package ru.job4j.array;

/**
 * Сlass FindLoop.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class FindLoop {

    /**
     * Method indexOf.
     * Finding an element in an array.
     *
     * @param element type int.
     * @param data    type int[].
     * @return rsl    type int.
     */
    public int indexOf(int[] data, int element) {
        int rsl = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
}
