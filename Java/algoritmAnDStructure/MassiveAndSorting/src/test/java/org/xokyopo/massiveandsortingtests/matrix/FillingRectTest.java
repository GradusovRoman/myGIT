package org.xokyopo.massiveandsortingtests.matrix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xokyopo.massiveandsorting.matrix.FillingRect;
import org.xokyopo.massiveandsorting.matrix.interfaces.MatrixImpl;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FillingRectTest {
    private MatrixImpl<Integer> matrix;
    private ValuesOfTest<Integer> valuesOfTest;

    static class ValuesOfTest <T>{
        public final T[] inArray;
        public final int HEIGHT;
        public final int WIDTH;
        public final T[][] outArray;

        public ValuesOfTest(T[] inArray, int height, int width, T[][] outArray) {
            this.inArray = inArray;
            this.HEIGHT = height;
            this.WIDTH = width;
            this.outArray = outArray;
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                new ValuesOfTest<>(
                        new Integer[]{1,2,3,4}, 2,2,
                        new Integer[][]{
                                {1,2},
                                {4,3}}
                        ),

                new ValuesOfTest<>(
                        new Integer[]{1,2,3,4,5,6,7,8,9}, 3,3,
                        new Integer[][]{
                                {1,2,3},
                                {8,9,4},
                                {7,6,5}}
                ),

                new ValuesOfTest<>(
                        new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12}, 4,3,
                        new Integer[][]{
                                {1,2,3},
                                {10,11,4},
                                {9,12,5},
                                {8,7,6},}
                ),

                new ValuesOfTest<>(
                        new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12}, 1,12,
                        new Integer[][]{
                                {1,2,3,4,5,6,7,8,9,10,11,12}}
                ),
                new ValuesOfTest<Integer>(
                        new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12}, 12,1,
                        new Integer[][]{
                                {1},
                                {2},
                                {3},
                                {4},
                                {5},
                                {6},
                                {7},
                                {8},
                                {9},
                                {10},
                                {11},
                                {12}}
                ),
        });
    }

    public FillingRectTest(ValuesOfTest<Integer> valuesOfTest) {
        this.valuesOfTest = valuesOfTest;
    }

    @Before
    public void init() {
        this.matrix = new FillingRect();
    }

    @Test
    public void startTesting() {
        Assert.assertArrayEquals(
                this.matrix.generatingMatrix(this.valuesOfTest.inArray, this.valuesOfTest.HEIGHT, this.valuesOfTest.WIDTH),
                this.valuesOfTest.outArray
        );
    }
}