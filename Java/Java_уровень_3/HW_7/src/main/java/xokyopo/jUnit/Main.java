package xokyopo.jUnit;

/*
1 Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов с аннотациями @Test.
Для этого у него должен быть статический метод start(), которому в качестве параметра передается или объект типа Class,
или имя класса. Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется. Далее
запущены методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite. К каждому тесту необходимо
добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться порядок их выполнения. Если приоритет
одинаковый, то порядок не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном
экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».
 */

import static xokyopo.jUnit.testingCore.TestingCore.start;

public class Main {
    public static void main(String[] args) {
        try {
            start(ClassForTesting.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------");
        System.out.println("повторный тест");
        System.out.println("---------------------------------");

        start(ClassForTesting.class);
    }
}
