package org.xokyopo.massiveandsortingtests.massive;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xokyopo.massiveandsorting.masive.ArrayManipulation;
import org.xokyopo.massiveandsorting.masive.ArrayManipulationImpl;

public class ArrayManipulationTest {
    private ArrayManipulationImpl arrayManipulation;

    @Before
    public void init() {
        this.arrayManipulation = new ArrayManipulation();
    }

    @Test
    public void addTest() {
        Assert.assertArrayEquals(this.arrayManipulation.add(new int[]{1, 2, 3, 4, 5}, 6), new int[]{1, 2, 3, 4, 5, 6});
        Assert.assertArrayEquals(this.arrayManipulation.add(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 8), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 8});
        Assert.assertArrayEquals(this.arrayManipulation.add(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 1), new int[]{1, 2, 3, 4, 5, 6, 7, 8, 1});
    }

    @Test
    public void removeTest() {
        Assert.assertArrayEquals(this.arrayManipulation.remove(new int[]{1, 2, 3, 4, 5}, 5), new int[]{1, 2, 3, 4});
        Assert.assertArrayEquals(this.arrayManipulation.remove(new int[]{1, 2, 3, 4, 5, 7, 8}, 1), new int[]{2, 3, 4, 5, 7, 8});
        Assert.assertArrayEquals(this.arrayManipulation.remove(new int[]{1, 2, 3, 4, 5, 7, 8}, 4), new int[]{1, 2, 3, 5, 7, 8});
    }

    @Test
    public void findElementTest() {
        Assert.assertEquals(this.arrayManipulation.findElement(new int[]{1,2,3,4,5,6}, 6), 5);
        Assert.assertEquals(this.arrayManipulation.findElement(new int[]{1,2,3,4,5,6,6}, 6), 5);
        Assert.assertEquals(this.arrayManipulation.findElement(new int[]{1,2,3,6,4,5,6}, 6), 3);
        Assert.assertEquals(this.arrayManipulation.findElement(new int[]{1,2,3,4,5,6}, 1), 0);
    }
}
