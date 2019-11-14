package xokyopo.snake.game;

import java.util.Random;

public class GameMap {
    private int mapSize;
    private GameObject[][] map;
    private boolean roundMap = true;
    private Random random = new Random();

    public  GameMap(int _mapSize) {
        this.mapSize = _mapSize;
        this.map = new GameObject[this.mapSize][this.mapSize];
    }

    //TODO : нужно более точное название метода
    public int[] getValidCoord(int[] _xy) {
        int[] coord = _xy;
        coord[0] = (_xy[0] >= 0) ?  _xy[0] % this.map.length : this.map.length + _xy[0] % this.map.length;
        coord[1] = (_xy[1] >= 0) ?  _xy[1] % this.map[0].length : this.map[0].length + _xy[1] % this.map.length;
        return coord;
    }

    public boolean isValidCoord(int[] _xy) {
        boolean isPsetInMap = (this.map.length >= _xy[0] && _xy[0] >= 0) && (this.map[0].length >= _xy[1] && _xy[1] >= 0);
        return this.roundMap || isPsetInMap;
    }

    public GameObject getGameObjectByCoord(int[] _xy) {
        return this.map[_xy[0]][_xy[1]];
    }

    public int[] getRandomEmptyCellCoord() {
        int[] coord = {0 , 0};
        do {
            coord = new int[] {this.random.nextInt(this.map.length) , this.random.nextInt(this.map[0].length)};
        }while (!this.isEmptyCell(coord));
        return coord;
    }

    private boolean isEmptyCell(int[] _xy) {
        return this.getGameObjectByCoord(_xy) == null;
    }

    public void addObjectOnMap(GameObject _gameObject) {
        this.map[_gameObject.getX()][_gameObject.getY()] = _gameObject;
    }

    public void refreshMap() {
        for (int row = 0 ; row < this.map.length ; row++) {
            for (int col = 0 ; col < this.map[0].length ; col++) {
                this.map[row][col] = null;
            }
        }
    }
}
