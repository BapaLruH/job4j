package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

import java.util.Arrays;

/**
 * Сlass Figure.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 27.11.2018
 */
public abstract class Figure {
    public final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Method way.
     * Calculates the way from source to dest.
     *
     * @param source type Cell.
     * @param dest   type Cell.
     * @return steps type Cell[].
     */
    public abstract Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException;

    /**
     * Method copy.
     * Copies the figure to its dest.
     *
     * @param dest type Cell.
     * @return figure type Figure.
     */
    public abstract Figure copy(Cell dest);

    /**
     * Method findCell.
     * Cell search by coordinates.
     *
     * @param x type int.
     * @param y type int.
     * @return result type Cell.
     */
    protected Cell findCell(int x, int y) {
        return Arrays.stream(Cell.values())
                .filter(cell -> cell.x == x && cell.y == y)
                .findFirst()
                .orElse(Cell.A1);
    }
}
