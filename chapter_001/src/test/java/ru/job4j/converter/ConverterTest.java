package ru.job4j.converter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * ConverterTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConverterTest {

    /**
     * Test rubleToDollar.
     */
    @Test
    public void whenSixtyRublesToDollarThenOne() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    /**
     * Test rubleToEuro.
     */
    @Test
    public void whenSeventyRublesToEuroThenOne() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }

    /**
     * Test dollarToRuble.
     */
    @Test
    public void whenOneDollarToRublesThenSixty() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(1);
        assertThat(result, is(60));
    }

    /**
     * Test euroToRuble.
     */
    @Test
    public void whenOneEuroToRublesThenSeventy() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(1);
        assertThat(result, is(70));
    }
}