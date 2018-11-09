package ru.job4j.array;

/**
 * Сlass ArrayChar.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 09.11.2018
 */
public class BubbleSort {

    /**
     * Method sort.
     * Sorting an array - bubble sort.
     *
     * @param array type int[].
     * @return sorted array type int[].
     */
    public int[] sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }
}
