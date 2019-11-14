package com.company;

import java.util.Arrays;

/*
1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], пройти по нему циклом, и числа, меньшие 6, умножить на 2;
4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и
правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) → true, граница
показана символами ||, эти символы в массив не входят;
7. *Написать метод, которому на вход подается одномерный массив и число n (может быть положительным или отрицательным), при этом метод должен сместить все элементы
массива на n позиций. Нельзя пользоваться вспомогательными массивами.

*/
public class Main {

    public static void main(String[] args) {

        task_1();
        task_2();
        task_3();
        task_4();
        task_5();
        task_6();
        task_7();

    }

    static void task_1(){
        String msg = "\n1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;";
        System.out.println(msg);

        int[] arr = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        System.out.println("Начальный массив");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++){
            arr[i] = (arr[i] > 0)? 0 : 1;
        }

        System.out.println("Конечный массив");
        System.out.println(Arrays.toString(arr));
    }

    static void task_2(){
        String msg = "\n2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21";
        System.out.println(msg);

        int[] arr = new int[8];

        System.out.println("Начальный массив");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++){
            arr[i] = i*3;
        }

        System.out.println("Конечный массив");
        System.out.println(Arrays.toString(arr));
    }

    static void task_3(){
        String msg = "\n3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ], пройти по нему циклом, и числа, меньшие 6, умножить на 2;";
        System.out.println(msg);

        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };

        System.out.println("Начальный массив");
        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < arr.length; i++){
            arr[i] = (arr[i] < 6)? (arr[i] * 6): arr[i];
        }

        System.out.println("Конечный массив");
        System.out.println(Arrays.toString(arr));
    }

    static void task_4(){
        String msg = "\n4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;";
        System.out.println(msg);

        int arrSize = 8;
        int[][] arr = new int[arrSize][arrSize];

        for (int x = 0; x < arrSize; x++){
            for (int y = 0; y < arrSize; y++) {
                arr[x][y] = (x == y)? 1:arr[x][y];
            }
        }

        System.out.println("Конечный массив");
        for (int[] x: arr){
            for (int y: x){
                System.out.print(y + "\t");
            }
            System.out.println("");
        }
    }

    static void task_5(){
        String msg = "\n 5. Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);";
        System.out.println(msg);

        //буду использовать массив из задания №3 [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        int min = arr[0];
        int max = arr[0];
        System.out.println("Исследуемый массив");
        System.out.println(Arrays.toString(arr));

        for (int i : arr){
            min = (i < min) ? i: min;
            max = (i > max) ? i: max;
        }
        System.out.println("min = " + min + "\n" + "max = "+ max);
    }

    static void task_6(){
        String msg = "\n6. Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true, если в массиве есть место, в котором сумма левой и\n" +
                "правой части массива равны. Примеры: checkBalance([1, 1, 1, || 2, 1]) → true, checkBalance ([2, 1, 1, 2, 1]) → false, checkBalance ([10, || 10]) → true, граница\n" +
                "показана символами ||, эти символы в массив не входят;";
        System.out.println(msg);

        int arrLength = 10;
        int[] arr = new int[arrLength];

        //для начала заполним архив случайными числами от 1 до 10
        for (int i = 0; i < arrLength; i++){
            arr[i] = (int)Math.round(Math.random()*10);
        }

        System.out.println("Масссив со случайными числами");
        System.out.println(Arrays.toString(arr));
        System.out.println("Данный архив сбаансирован? "+checkBalance(arr));
    }

    static boolean checkBalance(int[] arr){
        boolean exit = false;
        int leftSumm = 0;
        int rightSumm = 0;

        for (int seed = 0; seed < (arr.length - 1); seed++){
            leftSumm +=arr[seed];
            rightSumm = 0;
            for (int i = (seed + 1); i < arr.length; i++){
                rightSumm += arr[i];
            }
            exit = (leftSumm == rightSumm)? true : exit;
        }

        return exit;
    }

    static void task_7(){
        String msg = "\n7. *Написать метод, которому на вход подается одномерный массив и число n (может быть положительным или отрицательным), при этом метод должен сместить все элементы\n" +
                "массива на n позиций. Нельзя пользоваться вспомогательными массивами.";
        System.out.println(msg);

        int arrLength = 10;
        int[] arr = new int[arrLength];
        int arrOffset = 5;

        System.out.println("для начала заполним нащ массив случайными числами от 0 до 100");

        for (int i = 0; i < arrLength; i++){
            arr[i] = (int)Math.round(Math.random()*100);
        }

        System.out.println("Начальный массив");
        System.out.println(Arrays.toString(arr));

        arrOffseter(arr, arrOffset);

        System.out.println("Конечный массив со смещением: " + arrOffset);
        System.out.println(Arrays.toString(arr));
    }

    static void arrOffseter(int[] arr, int arrOffset){
        int arrLength = arr.length;

        if (arrOffset %  arrLength !=0) {
            int start = arrOffset > 0 ? 0 : arrLength -1;
            int vector = arrOffset > 0 ? 1 : -1;
            int oldNumber = arr[start];
            int newNumber = arr[start];

            for (int i = 0; i < Math.abs(arrOffset); i++) {
                int count = start;
                while(count >= 0 && count <= arrLength -1){
                    oldNumber = arr[count];
                    arr[count] = newNumber;
                    newNumber = oldNumber;
                    count += vector;
                }
                arr[start] = newNumber;
            }
        }
    }
}
