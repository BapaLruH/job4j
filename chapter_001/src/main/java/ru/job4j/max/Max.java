package ru.job4j.max;

/**
 * Сlass Max.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 07.11.2018
 */
public class Max {

    /**
     * Method max.
     * Calculates max number from first and second values.
     * @param first type int.
     * @param second type int.
     * @return result type int.
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Method max.
     * Calculates max number from first, second and third values.
     * @param first type int.
     * @param second type int.
     * @param third type int.
     * @return result type int.
     */
    public int max(int first, int second, int third) {
        int firstTmp = max(first, second);
        int secTmp = max(second, third);
        return firstTmp > secTmp ? firstTmp : secTmp;
    }

}
