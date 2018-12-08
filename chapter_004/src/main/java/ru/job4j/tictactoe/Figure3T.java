package ru.job4j.tictactoe;

import javafx.scene.shape.Rectangle;

/**
 * Сlass Figure3T.
 *
 * @author Evgeny Shevchenko (xxx-zet-xxx@yandex.ru)
 * @version 001
 * @since 09.12.2018
 */
public class Figure3T extends Rectangle {
    private boolean markX = false;
    private boolean markO = false;

    public Figure3T() {
    }

    public Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public void take(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }

    public boolean hasMarkX() {
        return this.markX;
    }

    public boolean hasMarkO() {
        return this.markO;
    }
}