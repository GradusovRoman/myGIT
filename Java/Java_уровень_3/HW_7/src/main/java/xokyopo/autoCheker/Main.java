package xokyopo.autoCheker;
/*
2 Создать авто-проверялку для ДЗ(см. задание в материалах)
Это домашнее задание никак не связано с темой тестирования через JUnit и использованием этой библиотеки: проект пишется с нуля.
 */

import xokyopo.autoCheker.testfile.TestFile;
import xokyopo.jUnit.myAnnotation.Test;

import java.lang.reflect.Method;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        TestingClass testingClass = new TestingClass(TestFile.class);
        testingClass.testing();
    }
}
