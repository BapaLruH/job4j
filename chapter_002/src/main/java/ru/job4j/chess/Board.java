package ru.job4j.chess;

import ru.job4j.chess.figures.Figure;

/**
 * Сlass Board.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 27.11.2018
 */
public class Board {
    private Figure[] figures = new Figure[32];
    private int indexFigure = 0;

    public void add(Figure figure) {
        figures[indexFigure++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean result = false;
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("There is no figure in the cell!");
        }
        Cell[] steps = figures[index].way(source, dest);
        for (Cell cell : steps) {
            index = this.findBy(cell);
            if (index != -1) {
                throw new OccupiedWayException("Occupied way!");
            }
        }
        if (steps.length > 0) {
            this.figures[index] = this.figures[index].copy(dest);
            result = true;
        }
        return result;
    }

    private int findBy(Cell cell) {
        int result = -1;
        for (int i = 0; i < this.figures.length; i++) {
            if (figures[i] != null
                    && figures[i].position.x == cell.x
                    && figures[i].position.y == cell.y) {
                result = i;
                break;
            }
        }
        return result;
    }
}
