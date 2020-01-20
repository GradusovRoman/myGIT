package xokyopo.race.objects;

import xokyopo.race.objects.interfaces.Player;
import xokyopo.race.objects.template.Stage;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

/*
+В тоннель не может заехать одновременно больше половины участников (условность).
 */

public class Tunnel extends Stage {
    private final Semaphore semaphore;

    public Tunnel(int width) {
        this.semaphore = new Semaphore(width);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Player c) {
        try {
            System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
            this.semaphore.acquire();

            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);

            this.semaphore.release();
        } catch (InterruptedException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

}
