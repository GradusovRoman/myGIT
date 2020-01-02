package xokyopo.snake.game;

//TODO: гласс игры
//игра, это игровые объекты (карта, змея и еда)
//игра имет правила которые должны соблюдаться объектами

import java.util.Random;

public class Game {
    private GameMap map;
    private Snake player;
    private Food food;
    private final int MAPSIZE = 10;
    private final int SNAKESTARTLENGTH = 2;
    private int snakeSpeed = 1;
    private Random random = new Random();

    public Game() {
        //инициализация игровых объектов
        this.initing();
    }

    public void initing(){
        this.initingMap();
        this.initingPlayer();
        this.initingFood();
        this.refreshMap();
    }

    private void initingMap() {
        this.map = new GameMap(this.MAPSIZE);
    }

    private void initingFood() {
        this.food = new Food(this.map.getRandomEmptyCellCoord());
    }

    private void initingPlayer() {
        this.player = new Snake(this.map.getRandomEmptyCellCoord() , this.SNAKESTARTLENGTH);
    }

    //TODO : тут нужно описать взаимодействия объектов игры (то бишь правира их поведения.)

    private void addFodOnMap() {
        this.map.addObjectOnMap(this.food);
    }

    private void addPlayerOnMap() {
        Snake s = this.player;
        for (int count = 0 ; count < player.getLengthCount() ; count++){
            s = (s.getNextSnakeCell() != null) ? s.getNextSnakeCell() : s;
            this.map.addObjectOnMap(s);
        }
        this.map.addObjectOnMap(this.player);
    }

    private boolean isSnakeCanEatThishObject(GameObject _object){
        return this.player.isToutch(_object.getXY());
    }

    public void gameLoop(){
        //TODO порядок действий
        this.player.goByVector();
        //TODO: если врезаемся в себя
        if (this.map.isValidCoord(player.getXY())) {
            //нормализуем координаты
            player.setXY(map.getValidCoord(player.getXY()));
            if (isSnakeCanEatThishObject(this.food)) {
                this.player.eatingGameObject(this.food);
                this.initingFood();
            }
        } else {
            //TODO: игра окончена
            System.out.println("GameOwer");
            this.initing();
        }
        this.refreshMap();
    }

    private void refreshMap() {
        this.map.refreshMap();
        this.addFodOnMap();
        this.addPlayerOnMap();
    }

    public int getMapSize() {
        return this.MAPSIZE;
    }

    public GameMap getMap(){
        return this.map;
    }

    public Snake getPlayer() {
        return this.player;
    }

}
