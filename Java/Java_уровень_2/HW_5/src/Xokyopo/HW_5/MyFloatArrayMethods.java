package Xokyopo.HW_5;

import java.util.Arrays;

public class MyFloatArrayMethods {

    //собираем один массив из нескольких маленьких
    public float[] mergeArraysToArray(float[][] _arrays) {
        int newLength = getLengthNewArray(_arrays);
        float[] newArray = new float[newLength];

        for (int i = 0; i < _arrays.length; i++) {
            System.arraycopy(_arrays[i], 0, newArray, (i * newLength/_arrays.length), _arrays[i].length);
        }

        return newArray;
    }

    //дробим большой массив на _numberOfParts частей и возвращаем итог
    public float[][] divideArraysForTreads(float[] _arr, int _numberOfParts) {
        float[][] arrsForTread = new float[_numberOfParts][_arr.length/_numberOfParts];

        for (int i = 0; i < _numberOfParts; i++) {
            /*
            пришлось изменить формулу для определения конча массива
            ((1 + i)*_arrIn.length)/_numberOfParts
            так как если длинна массива не делилась без остатка не правильно вычислял в длинну
            как пример 10/3 * 3 != 10 (хотя в математике все бы сократили и получили бы коректный результат а тут получили 9)
             */
            arrsForTread[i] = Arrays.copyOfRange(_arr, i * _arr.length/_numberOfParts, ((1 + i)*_arr.length)/_numberOfParts);
        }

        return arrsForTread;
    }

    private int getLengthNewArray(float[][] _arrays) {
        int length = 0;
        for (int i = 0; i <_arrays.length; i++) {
            length += _arrays[i].length;
        }
        return length;
    }
}
