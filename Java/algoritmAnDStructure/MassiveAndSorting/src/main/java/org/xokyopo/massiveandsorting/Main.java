package org.xokyopo.massiveandsorting;

/*
1. Создать массив большого размера (миллион элементов).
2. Написать методы удаления, добавления, поиска элемента массива.
3. Заполнить массив случайными числами.
4. Написать методы, реализующие рассмотренные виды сортировок, и проверить скорость выполнения каждой.
 */

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();

        //1. Создать массив большого размера (миллион элементов).
        int[] arr = new int[1000000];

//        3. Заполнить массив случайными числами.
        main.setRandomInArray(arr);

    }

    public void setRandomInArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = this.getRandomInt(arr.length);
        }
    }

    public int getRandomInt(int seed) {
        return new Random().nextInt(seed);
    }

}
