package org.xokyopo.hashtabel;

import org.xokyopo.hashtabel.hashmap.HashMap;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.showMyHash();
    }

    public void showMyHash() {
        int maxValues = 10;
        HashMap<Integer, String> map = new HashMap<>();

        System.out.println("Заполним свой HashMap значениями");

        for (int i = 0; i < maxValues; i++) {
            map.put(i, "str_" + i);
        }

        System.out.println("Получим:\n" + map);

        int del = new Random().nextInt(maxValues);

        System.out.println("удалим элемент с интексом: " + del);
        map.del(del);

        System.out.println("Получим:\n" + map);

        System.out.println("Распечатаем только значения\n" + Arrays.toString(map.values()));
    }
}
