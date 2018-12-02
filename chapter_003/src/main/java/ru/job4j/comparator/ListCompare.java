package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Сlass ListCompare.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 02.12.2018
 */
public class ListCompare implements Comparator<String> {

    /**
     * Method compare.
     * Comparison of the first and second strings.
     *
     * @param o1 type String.
     * @param o2 type String.
     * @return result type int.
     */
    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        boolean compare = false;
        char[] left = o1.toCharArray();
        char[] right = o2.toCharArray();
        int limit = Math.min(left.length, right.length);
        for (int i = 0; i < limit; i++) {
            if (left[i] != right[i]) {
                result = left[i] - right[i];
                compare = true;
                break;
            }
        }
        if (!compare) {
            result = o1.length() - o2.length();
        }
        return result;
    }
}
