package ru.job4j.loop;

/**
 * Сlass Counter.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Counter {

    /**
     * Method add.
     * Sum of all even numbers from start to finish.
     * @param start type int.
     * @param finish type int.
     * @return result type int.
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 ==0) {
                result += i;
            }
        }
        return result;
    }
}
