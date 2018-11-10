package ru.job4j.array;

import java.util.Arrays;

/**
 * Сlass ArrayDuplicate.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 09.11.2018
 */
public class ArrayDuplicate {

    /**
     * Method remove.
     * Removes all duplicates in the array.
     *
     * @param array type String[].
     * @return result type String[].
     */
    public String[] remove(String[] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[size - j];
                    size--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, size);
    }
}
