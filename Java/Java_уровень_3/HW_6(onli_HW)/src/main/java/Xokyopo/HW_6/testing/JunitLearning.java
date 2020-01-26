package Xokyopo.HW_6.testing;

/*
2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен
вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException. Написать
набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].

3. Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы,
то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 */

import java.util.Arrays;

public class JunitLearning {
    //методы написаны остались тесты

//    public static void main(String[] args) {
//
//        JunitLearning junitLearning = new JunitLearning();
//
//        System.out.println(Arrays.toString(
//
//        ));
//    }


    public int[] newArray(int[] arr, int findNumber) {
        int number = -1;

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == findNumber) {
                number = Math.max(i, number);
                break;
            }
        }

        if (number < 0) throw new RuntimeException();

        int[] newArray = new int[0];
        if (number < arr.length - 1) {
            number += 1;
            newArray = new int[arr.length - number];
            System.arraycopy(arr, number, newArray, 0, newArray.length);
        }
        return newArray;
    }

    public boolean isContain(int[] arr, int... number) {
        String s = Arrays.toString(arr);
        for  (int i = 0; i < number.length; i++) {
            if (!s.contains("" + number[i])) return  false;
        }
        return true;
    }
}
