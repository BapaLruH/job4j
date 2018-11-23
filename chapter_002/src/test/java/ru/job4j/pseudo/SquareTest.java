package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        assertThat(square.draw(), is(
                new StringBuilder()
                        .append("*****")
                        .append("*   *")
                        .append("*   *")
                        .append("*   *")
                        .append("*****")
                        .toString()
        ));
    }
}