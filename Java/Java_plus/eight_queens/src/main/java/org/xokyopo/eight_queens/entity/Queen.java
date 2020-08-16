package org.xokyopo.eight_queens.entity;

import org.xokyopo.eight_queens.templates.AbstractCoordinate;
import org.xokyopo.eight_queens.templates.AbstractFigure;
import org.xokyopo.eight_queens.templates.impl.ICoordinate;

public class Queen extends AbstractFigure {
    public Queen(AbstractCoordinate coordinate) {
        super(coordinate);
    }

    public boolean canEating(ICoordinate figureCoordinate) {
        return this.coordinate.getX() == figureCoordinate.getX()
                || this.coordinate.getY() == figureCoordinate.getY()
                || Math.abs(this.coordinate.getX() - figureCoordinate.getX()) == Math.abs(this.coordinate.getY() - figureCoordinate.getY());
    }

    public int getX() {
        return this.coordinate.getX();
    }

    public int getY() {
        return this.coordinate.getY();
    }

    public void setX(int x) {
        this.coordinate.setX(x);
    }

    public void setY(int y) {
        this.coordinate.setY(y);
    }

    @Override
    public String toString() {
        return "Q";
    }

    @Override
    public AbstractFigure getClone() {
        return new Queen(this.coordinate.getClone());
    }
}
