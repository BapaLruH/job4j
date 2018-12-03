package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiapasonTest {

    @Test
    public void whenLinearFunction() {
        Diapason diapason = new Diapason();
        List<Double> result = diapason.diapason(1, 3, (x) -> 1 * x + 2);
        List<Double> expect = Arrays.asList(3D, 4D);
        assertThat(result, is(expect));
    }

    @Test
    public void whenQuadraticFunction() {
        Diapason diapason = new Diapason();
        List<Double> result = diapason.diapason(1, 3, (x) -> Math.pow(x, 2) + 3 * x + 6);
        List<Double> expect = Arrays.asList(10D, 16D);
        assertThat(result, is(expect));
    }

    @Test
    public void whenLogarithmicFunction() {
        Diapason diapason = new Diapason();
        List<Double> result = diapason.diapason(1, 3, Math::exp);
        List<Double> expect = Arrays.asList(2.718281828459045, 7.38905609893065);
        assertThat(result, is(expect));
    }
}