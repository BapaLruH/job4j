package ru.job4j.chess.figures;

import ru.job4j.chess.Cell;
import ru.job4j.chess.ImpossibleMoveException;

import java.util.function.BiPredicate;
import java.util.stream.IntStream;

/**
 * Сlass Bishop.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 27.11.2018
 */
public class Bishop extends Figure {

    private final BiPredicate<Cell, Cell> isDiagonal =
            (first, second) -> Math.abs(second.x - first.x) == Math.abs(second.y - first.y);

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
        if (!isDiagonal.test(source, dest)) {
            throw new ImpossibleMoveException("Impossible move");
        }
        Cell[] steps = new Cell[Math.abs(dest.x - source.x)];
        int deltaX = Integer.compare(dest.x, source.x);
        int deltaY = Integer.compare(dest.y, source.y);
        IntStream.range(1, steps.length + 1).forEach(index -> {
            int stepX = source.x + (deltaX * index);
            int stepY = source.y + (deltaY * index);
            steps[index - 1] = findCell(stepX, stepY);
        });
        return steps;
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
