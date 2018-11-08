package ru.job4j.array;

/**
 * Сlass Turn.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Turn {

    /**
     * Method back.
     * Revers order.
     *
     * @param array  type int[].
     * @return array type int[].
     */
    public int[] back(int[] array) {
        int arraySize = array.length;
        for (int i = 0; i < arraySize; i++) {
            int tmp = array[i];
            array[i] = array[--arraySize];
            array[arraySize] = tmp;
        }
        return array;
    }
}
