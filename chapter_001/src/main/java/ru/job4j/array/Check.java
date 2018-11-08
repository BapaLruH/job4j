package ru.job4j.array;

/**
 * Сlass Check.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 08.11.2018
 */
public class Check {

    /**
     * Method mono.
     * Checks the elements on similarities.
     *
     * @param data type boolean[].
     * @return result type boolean.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (boolean element : data) {
            if (element != data[0]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
