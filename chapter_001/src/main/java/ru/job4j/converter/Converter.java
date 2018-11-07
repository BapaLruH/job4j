package ru.job4j.converter;

/**
 * Сlass Converter.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 07.11.2018
 */
public class Converter {

    private final int courseDollar = 60;
    private final int courseEuro = 70;

    /**
     * Method rubleToEuro.
     * Converting ruble to euro on course 70rub = 1euro.
     * @param value type int.
     * @return result type int.
     */
    public int rubleToEuro(int value) {
        return value / courseEuro;
    }

    /**
     * Method rubleToDollar.
     * Converting ruble to dollar on course 60rub = 1dollar.
     * @param value type int.
     * @return result type int.
     */
    public int rubleToDollar(int value) {
        return value / courseDollar;
    }

    /**
     * Method euroToRuble.
     * Converting euro to ruble on course 1euro = 70rub.
     * @param value type int.
     * @return result type int.
     */
    public int euroToRuble(int value) {
        return courseEuro * value;
    }

    /**
     * Method dollarToRuble.
     * Converting dollar to ruble on course 1dollar = 60rub.
     * @param value type int.
     * @return result type int.
     */
    public int dollarToRuble(int value) {
        return courseDollar * value;
    }
}
