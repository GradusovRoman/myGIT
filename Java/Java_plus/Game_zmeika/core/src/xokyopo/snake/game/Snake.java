package xokyopo.snake.game;

//змея ползучая

public class Snake extends GameObject {
    private int lengthCount = 0;
    private Snake snakeCell = null;
    private int[] vector = {0,0};
    private String TYPE = "snake";

    public Snake (int[] _xy , Snake _snakeCell) {
        super(_xy[0] , _xy[1] , false);
        this.snakeCell = _snakeCell;
    }

    public Snake (int[] _xy , int _length) {
        super(_xy[0] , _xy[1] , false);
        this.TYPE="head";
        this.lengthCount = _length;
        for (int i = 1 ; i <= this.lengthCount ; i++) {
            this.addCell();
        }
    }

    @Override
    public String getType(){
        return this.TYPE;
    }

    private void addCell() {
        this.snakeCell = new Snake(this.getXY(), snakeCell);
    }

    public int getLengthCount () {
        return this.lengthCount;
    }

    public Snake getNextSnakeCell() {
        return this.snakeCell;
    }

    public void setVector(int[] _xy) {
        if (_xy[0] != - this.vector[0] || _xy[1] != - this.vector[1]) {
            this.vector = _xy;
        }
    }

    public int[] getVector(){
        return this.vector;
    }

    public void addLength(){
        this.lengthCount++;
        this.addCell();
    }

    public void goByVector(){
        this.snakeCell = new Snake(this.getXY(), this.snakeCell);
        this.setXY(this.getNewCoordByVector());
        this.destroyLastCell(this.lengthCount);
    }

    private void destroyLastCell(int _lengthCount) {
        if (_lengthCount > 1){
            this.getNextSnakeCell().destroyLastCell(_lengthCount - 1);
        } else {
            this.snakeCell = null;
        }
    }

    private int[] getNewCoordByVector() {
        return new int[] {this.getX() - this.getVector()[0] , this.getY() - this.getVector()[1]};
    }

    public boolean eatingGameObject(GameObject _object) {
        boolean result = false;
        if (_object.isDestructible()) {
            this.addLength();
            result = true;
        }
        return result;
    }

    public void printSnake(){
        if (this.getNextSnakeCell() != null){
            this.getNextSnakeCell().printSnake();
        }
        System.out.println(" x = " + this.getX() +  "\ty= " + this.getY());
    }
    //конец
}
