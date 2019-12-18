package xokyopo;

/*
+ 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
+ 2. Написать метод, который преобразует массив в ArrayList;
3. Большая задача:
+Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
Для хранения фруктов внутри коробки можно использовать ArrayList;
Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
Не забываем про метод добавления фрукта в коробку.
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generics {

    public static void main (String[] args) {
        Generics generics = new Generics();

        generics.test1Swap();
        System.out.println("");
        generics.test2GetArrayList();
        System.out.println("");
        generics.test3Box();
    }

    public Object[] swap (Object[] arr, int a, int b) {
        Object[] result = arr.clone();
        Object buffer = result[a];
        result[a] = result[b];
        result[b] = buffer;
        return result;
    }

    public <T> ArrayList<T> getArrayList (T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    public Integer[] getRandomArray(int length) {
        Random random = new Random();
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(length);
        }
        return arr;
    }

    public void test1Swap () {
        int length = 10;
        int swapA = 5;
        int swapB = 2;
        System.out.println("1. Написать метод, который меняет два элемента массива местами");
        Integer[] arr = this.getRandomArray(length);
        System.out.printf("исходный массив\n %s\n", Arrays.toString(arr));
        System.out.printf("конечный массив массив в котором %s и %s элементы поменяны местами\n %s\n", swapA, swapB, Arrays.toString(this.swap(arr, swapA, swapB)));
    }

    public void test2GetArrayList () {
        int length = 10;
        System.out.println("2. Написать метод, который преобразует массив в ArrayList");
        Integer[] arr = this.getRandomArray(length);
        System.out.printf("исходный массив\n %s\n", Arrays.toString(arr));
        System.out.printf("конечный массив массив в преобразованный в ArrayList\n %s\n", this.getArrayList(arr).toString());
    }

    public void test3Box () {
        System.out.println("3. Класс Box");
        System.out.println("Создаем коробку с апельсинами");
        Box<Orange> orangeBox = new Box<>(this.getArraysFruits(new Orange(), 10));
        System.out.println("ее масса: " + orangeBox.getWeight());
        System.out.println("Создаем коробку с яблоками");
        Box<Apple> appleBox = new Box<>(this.getArraysFruits(new Apple(), 15));
        System.out.println("ее масса: " + appleBox.getWeight() + " она равна коробке с апельсинами " + appleBox.compare(orangeBox));
        System.out.println("Создаем коробку с апельсинами2");
        Box<Orange> orangeBox2 = new Box<>(this.getArraysFruits(new Orange(), 5));
        System.out.println("ее масса: " + orangeBox2.getWeight()  + " она равна коробке с яблоками " + orangeBox2.compare(appleBox));

        System.out.println("пересыпаем апелисины в 1 коробку");
        System.out.println("масса первой коробки: " + orangeBox.getWeight());
        System.out.println("масса: второй коробки: " + orangeBox2.getWeight());
        System.out.println("пересыпаем");
        orangeBox.putFruit(orangeBox2);
        System.out.println("масса первой коробки: " + orangeBox.getWeight());
        System.out.println("масса: второй коробки: " + orangeBox2.getWeight());
    }

    private <T extends Fruit> T[] getArraysFruits (T fruit, int number) {
        T[] fruits = (T[]) new Fruit[number];
        for (int i = 0; i < number; i++) {
            fruits[i] = fruit;
        }
        return fruits;
    }
}
