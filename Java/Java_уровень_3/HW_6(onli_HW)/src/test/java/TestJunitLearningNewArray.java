import Xokyopo.HW_6.testing.JunitLearning;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class TestJunitLearningNewArray {
    @Parameterized.Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[]{
                new TestParameter(new int[]{1,2,3,4,5}, 4, new int[]{5}),
                new TestParameter(new int[]{1,2,3,4,5}, 5, new int[]{}),
                new TestParameter(new int[]{1,2,3,4,5,6}, 4, new int[]{5,6}),
                new TestParameter(new int[]{1,2,3,4,5}, 1, new int[]{2,3,4,5}),
                new TestParameter(new int[]{1,2,3,4,5}, 8, new int[]{})
        });
    }

    public static class TestParameter {
        public int[] in;
        public int numberOfSeed;
        public int[] out;

        public TestParameter(int[] in, int numberOfSeed, int[] out) {
            this.in = in;
            this.numberOfSeed = numberOfSeed;
            this.out = out;
        }
    }

    private int[] in;
    private int numberOfSeed;
    private int[] out;

    public TestJunitLearningNewArray(TestParameter testParameter) {
        this.in = testParameter.in;
        this.numberOfSeed = testParameter.numberOfSeed;
        this.out = testParameter.out;
    }

    private JunitLearning junitLearning;

    @Before
    public void init() {
        this.junitLearning = new JunitLearning();
    }


    @Test
    public void massTestNewArray() {
        try {
            Assert.assertArrayEquals(out, this.junitLearning.newArray(in, numberOfSeed));
        } catch (RuntimeException e) {
            Assert.assertTrue(true);
        }
    }

}
