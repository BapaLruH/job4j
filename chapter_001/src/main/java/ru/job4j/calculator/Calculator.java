package ru.job4j.calculator;

/**
 * Сlass Calculator.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 07.11.2018
 */
public class Calculator {
    private double result;

    /**
     * Method add.
     * Adds two numbers.
     * @param first type double.
     * @param second type double.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract.
     * Subtracts from the first number of the second number.
     * @param first type double.
     * @param second type double.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method div.
     * Divides the first number by the second.
     * @param first type double.
     * @param second type double.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method multiple.
     * Multiplies the first number by the second.
     * @param first type double.
     * @param second type double.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method gerResult.
     * Returns the result.
     * @return result type double.
     */
    public double getResult() {
        return this.result;
    }
}
