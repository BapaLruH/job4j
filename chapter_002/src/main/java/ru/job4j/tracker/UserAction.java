package ru.job4j.tracker;

public interface UserAction {
    int key();
    void execute(Input input, ITracker tracker);
    String info();
}
