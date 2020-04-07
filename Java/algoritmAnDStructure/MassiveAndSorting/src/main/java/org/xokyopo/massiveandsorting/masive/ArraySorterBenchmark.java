package org.xokyopo.massiveandsorting.masive;

import java.util.Arrays;

public class ArraySorterBenchmark implements ArraySorterBenchmarkImpl{
    private boolean debug = false;
    private ArraySorter arraySorter;

    public ArraySorterBenchmark() {
        this.arraySorter = new ArraySorter();
    }

    @Override
    public int[] benchmarkSelectSort(int[] arr) {
        return this.benchmark("SelectSort", this.arraySorter::selectSort, arr);
    }

    @Override
    public int[] benchmarkClassicSort(int[] arr) {
        return this.benchmark("ClassicSort", this.arraySorter::classicSort, arr);
    }

    @Override
    public int[] benchmarkInsertSort(int[] arr) {
        return this.benchmark("InsertSort", this.arraySorter::insertSort , arr);
    }

    @Override
    public int[] benchmarkMargeSort(int[] arr) {
        return this.benchmark("MargeSort", this.arraySorter::margeSort , arr);
    }

    @Override
    public int[] benchmarkQuickSort(int[] arr) {
        return this.benchmark("QuickSort", this.arraySorter::quickSort, arr);
    }

    private void printArray(int[] arr) {
        int arrayMaxLength = 25;
        if (arr.length < arrayMaxLength) {
            System.out.println(Arrays.toString(arr));
        } else {
            System.out.println(String.format("%s  .........  %s",
                    Arrays.toString(this.getArraySlice(arr, arrayMaxLength /2, true)),
                    Arrays.toString(this.getArraySlice(arr, arrayMaxLength/2, false))
            ));
        }
    }

    private int[] getArraySlice(int[] arr, int length, boolean leftToRight) {
        int[] sliceArray = new int[length];
        for (int i = 0; i < length; i++) {
            sliceArray[i] = arr[(leftToRight)? i : arr.length - length + i];
        }
        return sliceArray;
    }

    private int[] benchmark(String nameMethod, Sort sort, int[] arr) {
        long startTimer;
        long stopTimer;

        System.out.println(nameMethod + " start");
        if (this.debug) {
            System.out.println("input Array");
            this.printArray(arr);
        }

        startTimer = System.currentTimeMillis();
        sort.sort(arr, 0, arr.length -1);
        stopTimer = System.currentTimeMillis();

        if (debug) {
            System.out.println("output Array ");
            this.printArray(arr);
        }

        System.out.println("sorting spend " + (stopTimer - startTimer) + " ms\n" + nameMethod + " stop\n\n");
        return arr;
    }
}
