package xokyopo.race.objects.player;

import xokyopo.race.objects.interfaces.Player;
import xokyopo.race.objects.interfaces.Finished;
import xokyopo.race.objects.interfaces.ReadyChecker;
import xokyopo.race.objects.interfaces.Walkable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Car implements Player {
    private static int CARS_COUNT;
    private final int speed;
    private final String name;

    static {
        CARS_COUNT = 0;
    }

    public Car(int speed) {
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public void go(ArrayList<Walkable> stages, ReadyChecker readyChecker, Finished finished) {
        //подготовка
        this.preparation();

        //ожидание других
        readyChecker.imReady();

        //движение
        this.runFromRoad(stages);

        //конец движения
        finished.imFinishing(this.name);
    }

    private void preparation() {
        try {
            System.out.println(this.name + " готовится");

            Thread.sleep(500 + (int)(Math.random() * 800));

            System.out.println(this.name + " готов");
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void runFromRoad(List<Walkable> stages) {
        for (int i = 0; i < stages.size(); i++) {
            stages.get(i).go(this);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }

}
