package ru.job4j.lambda;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Сlass Calculator.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 03.12.2018
 */
public class Calculator {
    public interface Operation {
        double calc(int left, int right);
    }

    /**
     * Method multiple.
     * Multiplies all numbers between the start and the finish.
     *
     * @param start  type int.
     * @param finish type int.
     * @param value  type int.
     * @param op     type BiFunction<Integer, Integer, Double>.
     * @param media  type Consumer<Double>.
     */
    public void multiple(int start, int finish, int value,
                         BiFunction<Integer, Integer, Double> op,
                         Consumer<Double> media
    ) {
        for (int i = start; i != finish; i++) {
            media.accept(op.apply(value, i));
        }
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.multiple(
                0, 10, 2,
                (value, index) -> {
                    double result = value * index;
                    System.out.printf("Multiple %s * %s = %s %n", value, index, result);
                    return result;
                },
                System.out::println);
    }
}
