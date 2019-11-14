package sample;

import javafx.scene.Scene;

import java.util.Random;

public class GameField {
    private int[][] map;
    private int number = 2;
    private Random random = new Random();
    private boolean noMoves;

    public GameField(int _mapSize) {
        this.map = new int[_mapSize][_mapSize];
        this.cleaningMap();
    }

    public void cleaningMap() {
        this.noMoves = false;
        for (int row = 0 ; row < map.length ; row++) {
            for (int col = 0 ; col < map[0].length ; col++) {
                this.map[row][col] = 0;
            }
        }
    }

    public int[][] getMap() {
        return this.map.clone();
    }

    public void addNewNumberOnMap() {
        int row = 0;
        int col = 0;
        do {
            row = this.random.nextInt(this.map.length);
            col = this.random.nextInt(this.map[0].length);
        } while(!this.isEmptyCell(row , col));
        this.map[row][col] = this.number;
    }

    private boolean isEmptyCell(int _row , int _col) {
        return map[_row][_col] ==  0;
    }

    public void slidingBorder(int _key) {
        switch (_key) {
            case (Comand.Keys.LEFT):
                this.slidingToLeft();
                break;
            case (Comand.Keys.RIGHT):
                this.slidingToRight();
                break;
            case (Comand.Keys.DOWN):
                this.slidingToDown();
                break;
            case (Comand.Keys.UP):
                this.slidingToUP();
                break;
        }
        if (!this.isMapFull()) {
            this.addNewNumberOnMap();
        } else {
            this.noMoves = !isHaveCombination();
        }
    }

    private void slidingToUP() {
        for (int col = 0 ; col < map.length ; col++) {
            setVerticalLine(col , slidingLineToLeft(getVerticalLine(col)));
        }
    }

    private void slidingToDown() {
        for (int col = 0 ; col < map.length ; col++) {
            setVerticalLine(col , slidingLineToRight(getVerticalLine(col)));
        }
    }

    private int[] getVerticalLine(int _col) {
        int[] line = new int[map.length];
        for (int row = 0 ; row < map.length ; row++) {
            line[row] = map[row][_col];
        }
        return line;
    }

    private void setVerticalLine(int _col , int[] _line) {
        for (int row = 0 ; row < map.length ; row++) {
            map[row][_col] = _line[row];
        }
    }

    private void slidingToLeft() {
        for (int row = 0 ; row < map[0].length ; row++) {
            this.map[row] = this.slidingLineToLeft(map[row]);
        }
    }

    private void slidingToRight() {
        for (int row = 0 ; row < map[0].length ; row++) {
            this.map[row] = this.slidingLineToRight(map[row]);
        }
    }

    private int[] slidingLineToLeft(int[] _line) {
        for (int i = 0 ; i < _line.length ; i++) {
            for (int count = 1 ; count < _line.length - i ; count++) {
                if (_line[count - 1] != 0) {
                    if (_line[count - 1] == _line[count]) {
                        _line[count - 1] +=_line[count];
                        _line[count] = 0;
                    }
                } else {
                    _line[count - 1] = _line[count];
                    _line[count] = 0;
                }
            }
        }
        return _line;
    }

    private int[] slidingLineToRight(int[] _line) {
        for (int i = 0 ; i < _line.length ; i++) {
            for (int count = _line.length - 2 ; count >= i ; count--) {
                if (_line[count + 1] != 0) {
                    if (_line[count + 1] == _line[count]) {
                        _line[count + 1] +=_line[count];
                        _line[count] = 0;
                    }
                } else {
                    _line[count + 1] = _line[count];
                    _line[count] = 0;
                }
            }
        }
        return _line;
    }

    public void printMap(){
        System.out.println("map");
        for (int row = 0 ; row < map.length; row++) {
            for (int col = 0 ; col < map[0].length ; col++) {
                System.out.print(map[row][col] + "\t");
            }
            System.out.println("");
        }
    }

    private boolean isMapFull(){
        boolean result = true;
        for (int count = 0 ; count < this.map.length * this.map[0].length ; count++) {
            if (this.map[count / this.map[0].length][count % this.map.length] == 0){
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean isHaveCombination() {
        boolean result = false;
        for (int row = 0 ; row < this.map.length ; row++) {
            for (int col = 0 ; col < this.map[0].length ; col++) {
                result = (result || isCellHaveCombination(row , col));
            }
            System.out.println();
        }
        return result;
    }

    private boolean isCellHaveCombination(int _row , int _col) {
        boolean result = false;
        if (_row - 1 >= 0) result = (result || (this.map[_row][_col] == this.map[_row - 1][_col]));
        if (_row + 1 < this.map.length) result = (result || (this.map[_row][_col] == this.map[_row + 1][_col]));
        if (_col - 1 >= 0) result = (result || (this.map[_row][_col] == this.map[_row][_col - 1]));
        if (_col + 1 < this.map[0].length) result = (result || (this.map[_row][_col] == this.map[_row][_col + 1]));
        return result;
    }

    public boolean isNoMoves(){
        return this.noMoves;
    }
}
