package org.xokyopo.massiveandsortingtests.massive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xokyopo.massiveandsorting.masive.ArraySorterBenchmark;
import org.xokyopo.massiveandsorting.masive.ArraySorterBenchmarkImpl;

import java.util.*;

@RunWith(Parameterized.class)
public class ArraySortingTest {
    private static int ARR_LENGTH = 10000;
    private static int TEST_COUNT = 10;

    private ArraySorterBenchmarkImpl arrayUtil;
    private ValuesOfTest valuesOfTest;

    public static class ValuesOfTest {
        public int[] inArr;
        public int[] outArr;

        public ValuesOfTest(int[] inArr, int[] outArr) {
            this.inArr = inArr;
            this.outArr = outArr;
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> createParameterList() {
        Object[] objectArr = new Object[TEST_COUNT];
        for (int i = 0; i < TEST_COUNT; i++) {
            objectArr[i] = createValuesOfText(ARR_LENGTH);
        }
        return Arrays.asList(objectArr);
    }

    private static ValuesOfTest createValuesOfText(int length) {
        int[] arr = createArrayWithRandomValue(length);
        return new ValuesOfTest(arr, getSortingArray(arr));
    }

    private static int[] createArrayWithRandomValue(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = getRandomInt(length);
        }
        return arr;
    }

    private static int getRandomInt(int seed) {
        return new Random().nextInt(seed);
    }

    private static int[] getSortingArray(int[] arr) {
        int[] outArr = arr.clone();
        Arrays.parallelSort(outArr);
        return outArr;
    }

    @Before
    public void init() {
        this.arrayUtil = new ArraySorterBenchmark();
    }

    public ArraySortingTest(ValuesOfTest valuesOfText) {
        this.valuesOfTest = valuesOfText;
    }

    @Test
    public void classicSortTest() {
        Assert.assertArrayEquals(this.arrayUtil.benchmarkClassicSort(this.valuesOfTest.inArr.clone()), this.valuesOfTest.outArr);
    }

    @Test
    public void insertSortTest() {
        Assert.assertArrayEquals(this.arrayUtil.benchmarkInsertSort(this.valuesOfTest.inArr.clone()), this.valuesOfTest.outArr);
    }

    @Test
    public void mergeSortTest() {
        Assert.assertArrayEquals(this.arrayUtil.benchmarkMargeSort(this.valuesOfTest.inArr.clone()), this.valuesOfTest.outArr);
    }

    @Test
    public void quickSortTest() {
        Assert.assertArrayEquals(this.arrayUtil.benchmarkQuickSort(this.valuesOfTest.inArr.clone()), this.valuesOfTest.outArr);
    }

    @Test
    public void selectSortTest() {
        Assert.assertArrayEquals(this.arrayUtil.benchmarkSelectSort(this.valuesOfTest.inArr.clone()), this.valuesOfTest.outArr);
    }
}
