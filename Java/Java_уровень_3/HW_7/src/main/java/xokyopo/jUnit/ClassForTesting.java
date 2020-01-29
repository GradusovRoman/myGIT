package xokyopo.jUnit;

import xokyopo.jUnit.myAnnotation.AfterSuit;
import xokyopo.jUnit.myAnnotation.BeforeSuite;
import xokyopo.jUnit.myAnnotation.Test;

public class ClassForTesting {
    private int count = 0;

    public ClassForTesting() {
    }

    @BeforeSuite
    public void before() {
        this.count += 1;
        System.out.println("before count = " + this.count);
    }

    @Test(id = 5)
    public void test3() {
        this.count += 1;
        System.out.println("count = " + this.count + "\t id = 5");
    }

    @Test(id = 10)
    public void test4() {
        this.count += 1;
        System.out.println("count = " + this.count + "\t id = 10");
    }

    @Test(id = 1)
    public void test1() {
        this.count += 1;
        System.out.println("count = " + this.count + "\t id = 1 ");
    }


    @Test(id = 1)
    public void test2() {
        this.count += 1;
        System.out.println("count = " + this.count + "\t id = 1 ");
    }


    @AfterSuit
    public void after() {
        this.count += 1;
        System.out.println("after count = " + this.count);
    }
}
