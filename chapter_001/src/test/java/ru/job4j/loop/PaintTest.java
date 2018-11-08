package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * PaintTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {

    @Test
    public void whenPyramidFourRight() {
        Paint paint = new Paint();
        String rst = paint.rightTrl(4);
        assertThat(rst, is(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("^   ")
                        .add("^^  ")
                        .add("^^^ ")
                        .add("^^^^")
                        .toString()
                )
        );
    }

    @Test
    public void whenPyramidFourLeft() {
        Paint paint = new Paint();
        String rst = paint.leftTrl(4);
        assertThat(rst, is(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("   ^")
                        .add("  ^^")
                        .add(" ^^^")
                        .add("^^^^")
                        .toString()
                )
        );
    }

    @Test
    public void whenPyramidIncludeFourRows() {
        Paint paint = new Paint();
        String result = paint.pyramid(4);
        assertThat(result, is(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("   ^   ")
                        .add("  ^^^  ")
                        .add(" ^^^^^ ")
                        .add("^^^^^^^")
                        .toString()
        ));
    }

    @Test
    public void whenPyramidIncludeFiveRows() {
        Paint paint = new Paint();
        String result = paint.pyramid(5);
        assertThat(result, is(
                new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                        .add("    ^    ")
                        .add("   ^^^   ")
                        .add("  ^^^^^  ")
                        .add(" ^^^^^^^ ")
                        .add("^^^^^^^^^")
                        .toString()
        ));
    }
}