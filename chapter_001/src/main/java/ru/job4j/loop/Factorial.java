package ru.job4j.loop;

/**
 * Сlass Factorial.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Factorial {

    /**
     * Method add.
     * Calculates factorial n.
     * @param n type int.
     * @return rsl type int.
     */
    public int calc(int n) {
        int rsl = 1;
        for (int i = 1; i <= n; i++) {
            rsl *= i;
        }
        return rsl;
    }
}
