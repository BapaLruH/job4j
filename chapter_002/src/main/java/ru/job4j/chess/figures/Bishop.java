package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

/**
 * Сlass Bishop.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 27.11.2018
 */
public class Bishop extends Figure {

    public Bishop(final Cell position) {
        super(position);
    }

    /**
     * Method way.
     * Calculates the way from source to dest.
     *
     * @param source type Cell.
     * @param dest   type Cell.
     * @return steps type Cell[].
     */
    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        int stepX = 0;
        int stepY = 0;
        for (int i = 1; i <= steps.length; i++) {
            stepX = source.x + (deltaX * i);
            stepY = source.y + (deltaY * i);
            steps[i - 1] = findCell(stepX, stepY);
        }
        return steps;
    }

    /**
     * Method isDiagonal.
     * Checks if the way is diagonal.
     *
     * @param source type Cell.
     * @param dest   type Cell.
     * @return result type boolean.
     */
    private boolean isDiagonal(Cell source, Cell dest) {
        boolean result = false;
        if (Math.abs((dest.x - source.x)) == Math.abs((dest.y - source.y))) {
            result = true;
        }
        return result;
    }

    /**
     * Method copy.
     * Copies the figure to its dest.
     *
     * @param dest type Cell.
     * @return figure type Figure.
     */
    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }


}
