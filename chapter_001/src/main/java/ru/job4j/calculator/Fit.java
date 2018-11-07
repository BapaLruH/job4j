package ru.job4j.calculator;

/**
 * Сlass Fit.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 07.11.2018
 */
public class Fit {

    /**
     * Method manWeight.
     * Calculate ideal weight for male.
     * @param height type double.
     * @return weight type double.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Method womanWeight.
     * Calculate ideal weight for female.
     * @param height type double.
     * @return weight type double.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}
