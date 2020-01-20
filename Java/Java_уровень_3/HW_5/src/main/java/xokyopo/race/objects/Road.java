package xokyopo.race.objects;

import xokyopo.race.objects.interfaces.Player;
import xokyopo.race.objects.template.Stage;

import java.util.Arrays;

public class Road extends Stage {

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Player c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);

            Thread.sleep(length / c.getSpeed() * 1000);

            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
