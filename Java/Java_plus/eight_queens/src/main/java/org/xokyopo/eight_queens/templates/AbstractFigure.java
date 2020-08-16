package org.xokyopo.eight_queens.templates;

import org.xokyopo.eight_queens.templates.impl.IClonable;
import org.xokyopo.eight_queens.templates.impl.ICoordinate;
import org.xokyopo.eight_queens.templates.impl.ICanEat;

import java.util.Objects;

public abstract class AbstractFigure implements ICanEat, ICoordinate, IClonable<AbstractFigure> {
    protected AbstractCoordinate coordinate;

    public AbstractFigure(AbstractCoordinate coordinate) {
        Objects.requireNonNull(coordinate);
        this.coordinate = coordinate;
    }
}
