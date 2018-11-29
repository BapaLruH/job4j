package ru.job4j.list;

import java.util.List;

/**
 * Сlass ConvertList2Array.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.11.2018
 */
public class ConvertList2Array {
    /**
     * Method toArray.
     * Converting list to matrix[rows][cells].
     * cells = Math.ceil(listSize / rows).
     *
     * @param list type List<Integer>.
     * @param rows type int.
     * @return result type int[][].
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][cells];
        int row = 0;
        int cell = 0;
        for (int number : list) {
            if (cell != cells) {
                array[row][cell] = number;
                cell++;
            } else {
                row++;
                cell = 0;
                array[row][cell++] = number;
            }
        }
        return array;
    }
}
