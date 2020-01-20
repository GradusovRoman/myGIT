package xokyopo.race;

import xokyopo.race.objects.player.Car;
import xokyopo.race.objects.Race;
import xokyopo.race.objects.Road;
import xokyopo.race.objects.Tunnel;

/*
Организуем гонки:
+Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
+ В тоннель не может заехать одновременно больше половины участников (условность).
+Попробуйте все это синхронизировать.
+Только после того, как все завершат гонку, нужно выдать объявление об окончании.
+Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.

 */

public class Main {
    public final int CARS_COUNT = 4;

    public static void main(String[] args) {
        Main main = new Main();
        main.raceRunning();
    }

    public void raceRunning() {
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));

        race.startRace(this.getCars(this.CARS_COUNT));
    }

    public Car[] getCars(final int count) {
        Car[] cars = new Car[count];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car( 20 + (int) (Math.random() * 10));
        }

        return cars;
    }
}

