package Xokyopo.hw_1.arena;

import Xokyopo.hw_1.arena.obstacles.Marathon;
import Xokyopo.hw_1.arena.obstacles.River;
import Xokyopo.hw_1.arena.obstacles.Wall;
import Xokyopo.hw_1.arena.obstacles.template.Obstacle;

import java.util.Random;

public class Arena {
    private Obstacle[] obstacles;
    private Random random = new Random();

    public static final class ObstaclesType {
        public static final int MARATHON = 0;
        public static final int RIVER = 1;
        public static final int WALL = 2;
    }

    public Arena(int... _obstaclesTypes) {
        this.obstacles = new Obstacle[_obstaclesTypes.length];
        for (int i = 0 ; i < _obstaclesTypes.length ; i++) {
            this.obstacles[i] = getObstacleByType(_obstaclesTypes[i]);
        }
    }

    private Obstacle getObstacleByType(int _type) {
        Obstacle resolve = null;
        switch (_type) {
            case ObstaclesType.MARATHON: resolve = new Marathon(this.random.nextInt(5000));
                break;
            case ObstaclesType.RIVER: resolve = new River(this.random.nextInt(200));
                break;
            case ObstaclesType.WALL: resolve = new Wall(this.random.nextInt(30));
                break;
        }
        return resolve;
    }

    public Obstacle[] getObstacles() {
        return this.obstacles.clone();
    }

    public void printingInfo() {
        System.out.println("Наши препядствия");
        for (Obstacle obstacle : this.obstacles) {
            obstacle.printingInfo();
        }
    }
}
