import Xokyopo.HW_6.testing.JunitLearning;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestJunitLearningIsContain {
    public static class ValueOfTest {
        public int[] in;
        public int[] find;
        public boolean out;

        public ValueOfTest(int[] in, int[] find, boolean out) {
            this.in = in;
            this.find = find;
            this.out = out;
        }
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[]{
                new ValueOfTest(new int[]{1,1,1,1,2,2,3,4,5}, new int[]{1,4}, true),
                new ValueOfTest(new int[]{1,1,1,1,2,2,3,4,5}, new int[]{1,5}, true),
                new ValueOfTest(new int[]{1,1,1,1,2,2,3,4,5}, new int[]{1,4}, true),
                new ValueOfTest(new int[]{1,1,1,1,2,2,3,4,5}, new int[]{1,8}, false),
                new ValueOfTest(new int[]{1,1,1,1,2,2,3,4,5}, new int[]{1}, true),
                new ValueOfTest(new int[]{1,1,1,1,2,2,3,4,5}, new int[]{9}, false),
        });
    }

    private ValueOfTest valueOfTest;
    private JunitLearning junitLearning;

    public TestJunitLearningIsContain(ValueOfTest valueOfTest) {
        this.valueOfTest = valueOfTest;
    }

    @Before
    public void init() {
        this.junitLearning = new JunitLearning();
    }

    @Test
    public void massTestJunitLearningIsContain() {
        Assert.assertArrayEquals(new boolean[]{valueOfTest.out}, new boolean[]{junitLearning.isContain(valueOfTest.in, valueOfTest.find)});
    }
}
