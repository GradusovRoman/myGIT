package xokyopo.snake.game;

public abstract class GameObject {
    private int x = 0;
    private int y = 0;
    private boolean destructible = false;

    public GameObject (int _x , int _y , boolean _destructible) {
        this.x = _x;
        this.y = _y;
        this.destructible = _destructible;
    }

    abstract public String getType();

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int[] getXY() {
        return new int[] {this.x , this.y};
    }

    public void setX(int _x) {
        this.x = _x;
    }

    public  void setY(int _y) {
        this.y = _y;
    }

    public void setXY(int[] _xy) {
        this.x = _xy[0];
        this.y = _xy[1];
    }

    public boolean isToutch(int[] _xy) {
        return this.x == _xy[0] && this.y ==_xy[1];
    }

    public boolean isToutch(int _x , int _y) {
        return this.x == _x && this.y == _y;
    }

    public boolean isDestructible() {
        return this.destructible;
    }
    //конец
}
