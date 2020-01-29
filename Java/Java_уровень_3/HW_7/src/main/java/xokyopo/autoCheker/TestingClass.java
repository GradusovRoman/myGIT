package xokyopo.autoCheker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestingClass {

    private Class testClass;

    public TestingClass(Class testClass) {
        this.testClass = testClass;
    }

    public void testing() {
        boolean[] out = {false, true, false};
        int[] in = {1, -1, 0};
        this.isNegative(out, in);

    }

    private boolean isNegative(boolean[] out ,int[] in) {
        System.out.println("Тест метода isNegative");
        boolean result = true;
        if (out.length != in.length) throw new ArrayStoreException();
        try {
            Method method = this.testClass.getDeclaredMethod("isNegative", int.class);
            method.setAccessible(true);

            for (int i = 0 ; i < in.length; i++) {
                try {
                    result = ((out[i] == (boolean) method.invoke(null, in[i])) && result);
                    System.out.println("in = " + in[i] + "\t\t out = " + out[i] + "\t\t result = " + (boolean) method.invoke(null, in[i]));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println( e.getMessage());
                }
            }
        }catch (NoSuchMethodException e) {
            System.err.println(e.getMessage());
            result = false;
        }

        System.out.println("Тест метода isNegative прошол : " + ((result)? "успешно" : "провалился"));

        return result;
    }

//    private boolean isLeapYear(boolean[] out ,int[] in) {
//        boolean result = true;
//        if (out.length != in.length) throw new ArrayStoreException();
//        try {
//            Method method = this.testClass.getDeclaredMethod("isLeapYear", int.class);
//            method.setAccessible(true);
//
//            for (int i = 0 ; i < in.length; i++) {
//                try {
//                    result = ((out[i] == (boolean) method.invoke(null, in[i])) && result);
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    System.err.println( e.getStackTrace());
//                }
//            }
//        }catch (NoSuchMethodException e) {
//            result = false;
//        }
//
//        return result;
//    }


}
