package org.xokyopo.eight_queens.entity;

import org.xokyopo.eight_queens.templates.AbstractCoordinate;

public class Coordinate extends AbstractCoordinate {
    private int x;
    private int y;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(AbstractCoordinate abstractCoordinate) {
        this.x = abstractCoordinate.getX();
        this.y = abstractCoordinate.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public AbstractCoordinate getClone() {
        return new Coordinate(this);
    }
}
