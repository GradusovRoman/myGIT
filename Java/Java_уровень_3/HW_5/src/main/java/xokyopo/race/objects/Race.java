package xokyopo.race.objects;

import xokyopo.race.objects.interfaces.Player;
import xokyopo.race.objects.interfaces.Walkable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private final ArrayList<Walkable> stages;
    private CyclicBarrier start;
    private CountDownLatch noFinished;
    private final AtomicInteger finishCounter= new AtomicInteger(1);

    public Race(Walkable... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }

    public void startRace(Player... cars) {
        //думаю логичнее правила описать тут , так как машины должны ехать и просто ехать по какой то дороге

        this.start = new CyclicBarrier(cars.length, () -> startInfo());

        this.noFinished = new CountDownLatch(cars.length);

        this.waitInfo();

        for (int i = 0; i < cars.length; i++) {
            final Player car = cars[i];
            new Thread(() -> car.go(this.stages, this::preparation, this::finishMessage)).start();
        }

        this.waitAll();

        this.finishInfo();
    }

    private void preparation() {
        try {
            start.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private void finishMessage(String name) {
        int i = this.finishCounter.getAndIncrement();
        this.infoMessage(name + ((i == 1)? " Победил": " Пришел " + i));
        this.noFinished.countDown();
    }

    private void waitAll() {
        try {
            this.noFinished.await();
        } catch (InterruptedException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }
    }

    private  void startInfo() {
        this.raceInfo(true);
    }

    private void  finishInfo() {
        this.raceInfo(false);
    }

    private void raceInfo(boolean isStart) {
        this.infoMessage("Гонка " + ((isStart)? "началась!!!": "закончилась!!!"));
    }

    private void waitInfo() {
        this.infoMessage("Подготовка!!!");
    }

    private void infoMessage(String msg) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> " + msg);
    }
}