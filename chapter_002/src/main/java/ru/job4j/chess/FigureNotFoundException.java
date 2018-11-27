package ru.job4j.chess;

public class FigureNotFoundException extends RuntimeException {
    public FigureNotFoundException(String message) {
        super(message);
    }
}
