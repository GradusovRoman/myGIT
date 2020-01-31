package xokyopo.hw7.lastTask;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestingClass {

    private Class testClass;

    public TestingClass(Class testClass) {
        this.testClass = testClass;
    }

    public void testing() {
        boolean result = true;
        //testIsNegative
        boolean[] out1 = {false, true, false};
        int[] in1 = {1, -1, 0};
        result &= this.testIsNegative(out1, in1);

        //testIsLeapYear
        boolean[] out2 = {true, true, false};
        int[] in2 = {2000, 2020, 2019};
        result &= this.testIsLeapYear(out2, in2);

        //testCheckTwoNumbers
        boolean[] out3 = {true, true, true, false, false};
        int[][] in3 = {
                {5,15},
                {5,10},
                {5,5},
                {4,5},
                {6,15}
        };
        result &= this.testCheckTwoNumbers(out3, in3);

        //testCalculate
        int[][] in4 = {
                {1,2,3,5},
                {6,7,8,10},
                {5,5,4,8},
                {4,5,3,7},
        };
        result &= this.testCalculate(in4);

        //testCalculate
        float[][] in5 = {
                {1,2,3,5},
                {6,7,8,10},
                {5,5,4,8},
                {4,5,3,7},
        };
        result &= this.testCalculate(in5);

        if (result) System.out.println("Все тесты прошли успешно");
        else System.out.println("МассТест завершился неудачей");
    }

    private void printInfoStartMethodTest(String method) {
        System.out.println("Тест метода " + method);
    }

    private void printInfoEndMethodTest(String method, boolean result) {
        System.out.println("Тест метода " + method + " : " + ((result)? " прошол успешно" : "провалился"));
    }

    private void printInfoNoMethodForTest(String method) {
        System.out.println("Метод с именем \"" + method + "\" не обнаружен");
    }

//    private boolean testIsNegativeTemplate(String nameOfMethod, boolean[] out , int[] in) { // унифицировать входные параметры
//        boolean result = true; // это можно оставить
//
//        try {
//            //TODO получаем метод
//            Method method = this.testClass.getDeclaredMethod(nameOfMethod);
//            method.setAccessible(true);
//
//            this.printInfoStartMethodTest(nameOfMethod);
//
//            //TODO получить тип возвращаемого значения
//            //TODO если его нет, то тест закончен.
//
//            //TODO получить список входных данных
//            //TODO сопоставить данные для проверки с входными и выходными данными
//
//            //TODO проверить соответсвие переданных данных, с теми чт онеобходимо передать.
//
//            for (int i = 0; i < in.length; i++) {
//                try {
//                    result = ((out[i] == (boolean) method.invoke(null, in[i])) && result);
//                    //debug System.out.println("in = " + in[i] + "\t\t out = " + out[i] + "\t\t result = " + (boolean) method.invoke(null, in[i]));
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    System.err.println(e.getMessage());
//                }
//            }
//
//            this.printInfoEndMethodTest(nameOfMethod, result);
//
//        }catch (NoSuchMethodException e) {
//            this.printInfoNoMethodForTest(nameOfMethod);
//            System.err.println(e.getMessage());
//            result = false;
//        }
//
//        return result;
//    }

    private boolean testIsNegative(boolean[] out , int[] in) {
        boolean result = true;
        final String nameOfMethod = "isNegative";

        this.printInfoStartMethodTest(nameOfMethod);

        if (out.length != in.length) throw new ArrayStoreException();
        try {
            Method method = this.testClass.getDeclaredMethod(nameOfMethod, int.class);
            method.setAccessible(true);

            for (int i = 0 ; i < in.length; i++) {
                try {
                    result = ((out[i] == (boolean) method.invoke(null, in[i])) && result);
                    //debug System.out.println("in = " + in[i] + "\t\t out = " + out[i] + "\t\t result = " + (boolean) method.invoke(null, in[i]));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println( e.getMessage());
                }
            }
        }catch (NoSuchMethodException e) {
            System.err.println(e.getMessage());
            result = false;
        }

        this.printInfoEndMethodTest(nameOfMethod, result);

        return result;
    }

    private boolean testIsLeapYear(boolean[] out ,int[] in) {
        boolean result = true;
        final String nameOfMethod = "isLeapYear";

        this.printInfoStartMethodTest(nameOfMethod);

        if (out.length != in.length) throw new ArrayStoreException();

        try {
            Method method = this.testClass.getDeclaredMethod(nameOfMethod, int.class);
            method.setAccessible(true);

            for (int i = 0 ; i < in.length; i++) {
                try {
                    result = ((out[i] == (boolean) method.invoke(null, in[i])) && result);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println( e.getMessage());
                }
            }
        }catch (NoSuchMethodException e) {
            result = false;
        }

        this.printInfoEndMethodTest(nameOfMethod , result);

        return result;
    }

    private boolean testCheckTwoNumbers(boolean[] out ,int[][] in) {
        boolean result = true;
        final String nameOfMethod = "checkTwoNumbers";

        this.printInfoStartMethodTest(nameOfMethod);

        if (out.length != in.length) throw new ArrayStoreException();

        try {
            Method method = this.testClass.getDeclaredMethod(nameOfMethod, int.class, int.class);
            method.setAccessible(true);

            for (int i = 0 ; i < in.length; i++) {
                try {
                    result = ((out[i] == (boolean) method.invoke(null, in[i][0], in[i][1])) && result);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println( e.getMessage());
                }
            }
        }catch (NoSuchMethodException e) {
            result = false;
        }

        this.printInfoEndMethodTest(nameOfMethod , result);

        return result;
    }

    private boolean testCalculate(int[][] in) {
        boolean result = true;
        final String nameOfMethod = "calculate";

        this.printInfoStartMethodTest(nameOfMethod + "(int)");

        try {
            Method method = this.testClass.getDeclaredMethod(nameOfMethod, int.class, int.class, int.class, int.class);
            method.setAccessible(true);

            for (int i = 0 ; i < in.length; i++) {
                try {
                    result = ((in[i][0] * (in[i][1] + (in[i][2] / in[i][3])) == (int) method.invoke(null, in[i][0], in[i][1], in[i][2], in[i][3])) && result);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println( e.getMessage());
                }
            }
        }catch (NoSuchMethodException e) {
            result = false;
        }

        this.printInfoEndMethodTest(nameOfMethod , result);

        return result;
    }

    private boolean testCalculate(float[][] in) {
        boolean result = true;
        final String nameOfMethod = "calculate";

        this.printInfoStartMethodTest(nameOfMethod+ "(float)");

        try {
            Method method = this.testClass.getDeclaredMethod(nameOfMethod, float.class, float.class, float.class, float.class);
            method.setAccessible(true);

            for (int i = 0 ; i < in.length; i++) {
                try {
                    result = ((in[i][0] * (in[i][1] + (in[i][2] / in[i][3])) == (float) method.invoke(null, in[i][0], in[i][1], in[i][2], in[i][3])) && result);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println( e.getMessage());
                }
            }
        }catch (NoSuchMethodException e) {
            result = false;
        }

        this.printInfoEndMethodTest(nameOfMethod , result);

        return result;
    }


}
