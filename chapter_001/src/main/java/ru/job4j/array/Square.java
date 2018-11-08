package ru.job4j.array;

/**
 * Сlass Square.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Square {

    /**
     * Method calculate.
     * Creates an array with an N boundary and calculate a square (index plus one).
     *
     * @param bound  type int.
     * @return rsltArray type int[].
     */
    public int[] calculate(int bound) {
        int[] rsltArray = new int[bound];
        for (int i = 0; i < rsltArray.length; i++) {
            rsltArray[i] = (int) Math.pow(i + 1, 2);
        }
        return rsltArray;
    }
}
