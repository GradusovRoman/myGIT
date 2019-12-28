package Xokyopo;

/*
+ 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
+ 2. Создать модель MFU с возможностью печати и сканирования(копирования) (данные процессы могут происходить параллельно).
 */

import java.util.Random;

public class Main {
    public int count = 0;

    public static void main(String[] args) {
        Main main = new Main();
        main.ABCPrinting();
        main.HumansAndMFU();

    }


    public void HumansAndMFU() {
        int count = 10;
        final int maxMFUFunction = 3;
        MFUDriver mfu = new MFUDriver();
        Random random = new Random();

        for (int i = 0 ; i < count; i++) {
            new Human("" + (i + 1), mfu, random.nextInt(maxMFUFunction)).start();
        }
    }

    public void ABCPrinting() {
        final int count = 5;
        String[] arr = {"A", "B", "C"};
        ABCPrint t1 = new ABCPrint(arr, 0, count, this);
        ABCPrint t2 = new ABCPrint(arr, 1, count, this);
        ABCPrint t3 = new ABCPrint(arr, 2, count, this);
        t1.start();
        t2.start();
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
    }


}
