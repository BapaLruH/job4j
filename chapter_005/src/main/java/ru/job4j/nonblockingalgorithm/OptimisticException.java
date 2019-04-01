package ru.job4j.nonblockingalgorithm;

public class OptimisticException extends RuntimeException {
    public OptimisticException() {
        super("oops, something went wrong!");
    }
}
