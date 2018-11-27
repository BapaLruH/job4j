package ru.job4j.chess.figures;

import org.junit.Test;
import ru.job4j.chess.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopTest {

    @Test(expected = FigureNotFoundException.class)
    public void whenFigureNotFoundThenException() {
        Board board = new Board();
        Bishop bishop = new Bishop(Cell.A3);
        board.add(bishop);
        board.move(Cell.A1, Cell.C3);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenFigureImpossibleMoveThenException() {
        Board board = new Board();
        Bishop bishop = new Bishop(Cell.A3);
        board.add(bishop);
        board.move(Cell.A3, Cell.A1);
    }

    @Test(expected = OccupiedWayException.class)
    public void whenWayIsOccupiedThenException() {
        Board board = new Board();
        Bishop bishop = new Bishop(Cell.A3);
        Bishop bishopOccupiedWay = new Bishop(Cell.B2);
        board.add(bishop);
        board.add(bishopOccupiedWay);
        board.move(Cell.A3, Cell.C1);
    }

    @Test
    public void whenBishopMoveTopRightThenMove() {
        Bishop bishop = new Bishop(Cell.B1);
        Cell[] result = bishop.way(bishop.position, Cell.H7);
        Cell[] expected = {Cell.C2, Cell.D3, Cell.E4, Cell.F5, Cell.G6, Cell.H7};
        assertThat(result, is(expected));
    }

    @Test
    public void whenBishopMoveTopLeftThenMove() {
        Bishop bishop = new Bishop(Cell.H1);
        Cell[] result = bishop.way(bishop.position, Cell.A8);
        Cell[] expected = {Cell.G2, Cell.F3, Cell.E4, Cell.D5, Cell.C6, Cell.B7, Cell.A8};
        assertThat(result, is(expected));
    }
}