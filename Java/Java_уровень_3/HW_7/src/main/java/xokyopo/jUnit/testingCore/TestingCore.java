package xokyopo.jUnit.testingCore;

/*
1 Создать класс, который может выполнять «тесты».

В качестве тестов выступают классы с наборами методов с аннотациями @Test.

Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект типа Class, или имя класса.

Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.

Далее запущены методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.

К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться порядок их выполнения.

Если приоритет одинаковый, то порядок не имеет значения.

Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном
экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».
 */


import xokyopo.jUnit.myAnnotation.AfterSuit;
import xokyopo.jUnit.myAnnotation.BeforeSuite;
import xokyopo.jUnit.myAnnotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestingCore {

    private Class testObject;

    public TestingCore(Class testObject) {
        this.testObject = testObject;
    }

    public static void start(String name) throws ClassNotFoundException {
        start(Class.forName(name));
    }

    public static void start(Class obj) {
        new TestingCore(obj).run();
    }

    private void run() {
        List<Method> beforeList = this.getMethodByAnnotation(BeforeSuite.class) ;
        List<Method> MethodsList = this.getMethodByAnnotation(Test.class);
        List<Method> afterList = this.getMethodByAnnotation(AfterSuit.class);

        //TODO проверка условия запуска
        if (beforeList.size() <= 1 && afterList.size() <=1) {
            //сортируем список тестовых методов
            TreeMap<Integer, List<Method>> sortMethodList = new TreeMap<>();

            MethodsList.forEach(method -> {
                int i = method.getAnnotation(Test.class).id();

                if (!sortMethodList.containsKey(i)) {
                    sortMethodList.put(i, new ArrayList<>(Arrays.asList(method)));
                } else {
                    sortMethodList.get(i).add(method);
                }

            } );

            sortMethodList.forEach(((integer, methods) -> {
                methods.forEach(method -> {
                    try {
                        Object testClass = this.testObject.newInstance();

                        for (Method before : beforeList) {
                            before.invoke(testClass);
                        }

                        System.out.println("");
                        System.out.println("выполняю метод " + method.getName());

                        method.invoke(testClass);

                        System.out.println("");

                        for (Method after : afterList) {
                            after.invoke(testClass);
                        }

                    } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                        System.err.println(e.getStackTrace());
                    }
                });
            }));

        } else throw new RuntimeException();
    }

    private List<Method> getMethodByAnnotation(Class annotation) {
        List<Method> list = new ArrayList<>();
        Arrays.asList(this.testObject.getMethods()).forEach((method)->{
            if (method.isAnnotationPresent(annotation)) list.add(method);
        });
        return list;
    }

}
