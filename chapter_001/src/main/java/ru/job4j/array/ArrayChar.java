package ru.job4j.array;

/**
 * Сlass ArrayChar.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 09.11.2018
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Method startWith.
     * Checks if the substring starts with the specified prefix.
     *
     * @param prefix type String.
     * @return result type boolean.
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] prefixChar = prefix.toCharArray();
        if (prefixChar.length > data.length) {
            return false;
        }

        for (int i = 0; i < prefixChar.length; i++) {
            if (prefixChar[i] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
