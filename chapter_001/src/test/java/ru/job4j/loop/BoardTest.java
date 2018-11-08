package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * CounterTest.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BoardTest {

    /**
     * Test paint(3, 3)
     */
    @Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String rsl = board.paint(3, 3);
        String lnSeparator = System.lineSeparator();
        assertThat(rsl, is(
                String.format("X X%s X %sX X%s", lnSeparator, lnSeparator, lnSeparator)
                )
        );
    }

    /**
     * Test paint(5, 4)
     */
    @Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();
        String rsl = board.paint(5, 4);
        String lnSeparator = System.lineSeparator();
        assertThat(rsl, is(
                String.format("X X X%s X X %sX X X%s X X %s", lnSeparator, lnSeparator, lnSeparator, lnSeparator)
                )
        );
    }
}