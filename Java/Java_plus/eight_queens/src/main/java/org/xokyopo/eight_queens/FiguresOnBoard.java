package org.xokyopo.eight_queens;

import org.xokyopo.eight_queens.entity.Coordinate;
import org.xokyopo.eight_queens.entity.Queen;
import org.xokyopo.eight_queens.templates.AbstractFigure;
import org.xokyopo.eight_queens.templates.impl.ICoordinate;
import org.xokyopo.eight_queens.templates.impl.InRunnable;

import java.util.Arrays;
import java.util.Objects;

public class FiguresOnBoard {
    private int size;

    public FiguresOnBoard(int boardSize) {
        this.size = boardSize;
    }

    private AbstractFigure[] findMax(AbstractFigure... abstractFigures) {
        final AbstractFigure[][] result = {abstractFigures};
        AbstractFigure currentFigure = abstractFigures[0].getClone();

        this.forEach(in -> {
            currentFigure.setX(in.getX());
            currentFigure.setY(in.getY());

            if (!this.canEating(currentFigure, abstractFigures)) {
                    result[0] = this.max(result[0], this.findMax(this.addToArray(currentFigure.getClone(), abstractFigures)));
            }

        });

        return result[0];
    }

    public AbstractFigure[] getMaxElementCombination(AbstractFigure figureTemplate) {
        Objects.requireNonNull(figureTemplate, "Вы не задали фигуры");
        return this.findMax(figureTemplate);
    }

    public void forEach(InRunnable<ICoordinate> runnable) {
        Coordinate coordinate = new Coordinate();
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                coordinate.setX(i);
                coordinate.setY(j);
                runnable.run(coordinate);
            }
        }
    }

    public AbstractFigure[] max(AbstractFigure[] leftArray, AbstractFigure[] rightArray) {
        return  (leftArray.length < rightArray.length) ? rightArray : leftArray;
    }

    public boolean canEating(AbstractFigure eatFigure, AbstractFigure... arrFigures) {
        if (arrFigures != null) {
            for (int j = 0; j < arrFigures.length; j++) {
                if (arrFigures[j].canEating(eatFigure) || eatFigure.canEating(arrFigures[j]))
                    return true;
            }
        }
        return false;
    }

    private AbstractFigure[] addToArray(AbstractFigure addingFigure, AbstractFigure... arrFigures) {
        AbstractFigure[] result = Arrays.copyOf(arrFigures, arrFigures.length + 1);
        result[result.length - 1] = addingFigure;
        return result;
    }

    public void printF(AbstractFigure... abstractFigures) {
        if (abstractFigures != null) {
            System.out.println("всего можно поставить: " + abstractFigures.length + " фигур типа " + abstractFigures[0]);
            this.printFigureCoordinate(abstractFigures);
        } else {
            System.out.println("Не возможно поставить ни одной фигуры, ошибка приложения.");
        }
    }

    public void printFigureCoordinate(AbstractFigure... abstractFigures) {
        Objects.requireNonNull(abstractFigures);
        for (int i = 0; i < abstractFigures.length; i++) {
            System.out.println(String.format("Фигура № %s X= %s; Y= %s;", i + 1, abstractFigures[i].getX(), abstractFigures[i].getY()));
        }
    }

    public static void main(String[] args) {
        System.out.println("Расчет максимального количества фигур которые можно расположить.");
        Queen queen = new Queen(new Coordinate(0,0));
        FiguresOnBoard figuresOnBoard = new FiguresOnBoard(10);
        AbstractFigure[] abstractFigures = figuresOnBoard.getMaxElementCombination(queen);
        figuresOnBoard.printF(abstractFigures);
    }
}
