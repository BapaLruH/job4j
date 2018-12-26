package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;
    private final int speed;
    private Vector vector = new Vector(Horizontal.Lift, Vertical.Down);

    public RectangleMove(Rectangle rect, int limitX, int limitY, int speed) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
        this.speed = speed;
    }

    @Override
    public void run() {
        while (true) {
            determineMove();
            double xPos = this.rect.getX();
            double yPos = this.rect.getY();
            if (vector.getHv() == Horizontal.Lift) {
                this.rect.setX(xPos - speed + 0.5);
            } else {
                this.rect.setX(xPos + speed);
            }
            if (vector.getVv() == Vertical.Up) {
                this.rect.setY(yPos - speed);
            } else {
                this.rect.setY(yPos + speed + 0.5);
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void determineMove() {
        if (vector.getHv() == Horizontal.Lift && this.rect.getX() <= 0) {
            vector.setHv(Horizontal.Right);
        } else if (vector.getHv() == Horizontal.Right && this.rect.getX() >= limitX - 10) {
            vector.setHv(Horizontal.Lift);
        }
        if (vector.getVv() == Vertical.Up && this.rect.getY() <= 0) {
            vector.setVv(Vertical.Down);
        } else if (vector.getVv() == Vertical.Down && this.rect.getY() >= limitY - 10) {
            vector.setVv(Vertical.Up);
        }
    }

    private class Vector {
        private Horizontal hv;
        private Vertical vv;

        Vector(Horizontal hv, Vertical vv) {
            this.hv = hv;
            this.vv = vv;
        }

        Horizontal getHv() {
            return hv;
        }

        void setHv(Horizontal hv) {
            this.hv = hv;
        }

        Vertical getVv() {
            return vv;
        }

        void setVv(Vertical vv) {
            this.vv = vv;
        }
    }

    enum Vertical {
        Up, Down
    }

    enum Horizontal {
        Lift, Right
    }
}