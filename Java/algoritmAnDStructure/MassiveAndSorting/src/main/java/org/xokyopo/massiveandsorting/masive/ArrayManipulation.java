package org.xokyopo.massiveandsorting.masive;

import java.util.Arrays;

public class ArrayManipulation implements ArrayManipulationImpl {
    @Override
    public int[] remove(int[] arr, int element) {
        int index = this.findElement(arr,element);

        if (index >= 0) {
            int[] bufferArr = new int[arr.length - 1];
            for(int i = 0; i < bufferArr.length; i++ ) {
                bufferArr[i] = arr[(i < index)? i : i + 1];
            }
            return bufferArr;
        }

        return arr;
    }

    @Override
    public int[] add(int[] arr, int element) {
        int[] bufferArr = new int[arr.length + 1];

        for (int i= 0 ; i < arr.length; i++) {
            bufferArr[i] = arr[i];
        }

        bufferArr[arr.length] = element;
        return bufferArr;
    }

    @Override
    public int findElement(int[] arr, int element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) return i;
        }
        return -1;
    }
}
