package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Сlass ConvertMatrix2List.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 29.11.2018
 */
public class ConvertMatrix2List {
    /**
     * Method toList.
     * Converting a matrix into a list.
     *
     * @param array type int[][].
     * @return list type List<Integer>.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : array) {
            for (int number : row) {
                list.add(number);
            }
        }
        return list;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        list.forEach(array -> Arrays.stream(array).forEach(result::add));
        return result;
    }
}
