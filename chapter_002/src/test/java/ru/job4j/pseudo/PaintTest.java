package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PaintTest {
    @Test
    public void whenDrawSquare() {
        PrintStream sysOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()), is(
                new StringBuilder()
                        .append("*****")
                        .append("*   *")
                        .append("*   *")
                        .append("*   *")
                        .append("*****")
                        .append(System.lineSeparator())
                        .toString()
        ));
        System.setOut(sysOut);
    }
    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("  *  ")
                                .append(" * * ")
                                .append("*****")
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdout);
    }
}