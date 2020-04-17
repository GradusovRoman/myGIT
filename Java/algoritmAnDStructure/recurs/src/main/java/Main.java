import Bag.Bag;

import java.util.*;

import Bag.Item;

public class Main {
    private Random random = new Random();

    public static void main(String[] args) {
        Main main = new Main();
        main.binarySearch();

        System.out.println("");
        main.mathPow();

        System.out.println("");
        main.bagTask();
    }

    public void bagTask() {
        System.out.println("задача о рюкзаке");
        Bag bag = new Bag(10);
        TreeSet<Item> itemList = this.getItemList(100);
        System.out.println("имеем рюкзак " + bag.getInfo());

//        StringBuffer stringBuffer = new StringBuffer();
//        itemList.forEach(item->stringBuffer.append(item.getInfo() + "\t"));
//        System.out.println("имеем вещи : " + stringBuffer.toString());

        itemList.forEach(bag::add);
        System.out.println("через оценки ценности веса:");
        System.out.println("в рюкзак поместилось: " + bag.getItemCount() + " вещи(ей) на сумму: " + bag.getCost());
        System.out.println("в рюкзаке еще можно положить: " + bag.getLeftWeight() + " unit");

        System.out.println("через рекурсию:");
        bag.erase();
        System.out.println("через оценки ценности веса:");
        System.out.println("в рюкзак поместилось вещи(ей) на сумму: " + bag.recursSearch(itemList.toArray(new Item[0]), 0, bag.getLeftWeight()));
    }

    public TreeSet<Item> getItemList(int count) {
        TreeSet<Item> itemList = new TreeSet<>();
        for (int i = 0; i < count; i++) {
            itemList.add(new Item(this.gerRandom(500), this.getRandom(10)));
        }
        return itemList;
    }

    public void binarySearch() {
        System.out.println("демонстрация работы бинарного поиска");
        Integer[] arr = this.getRandomArr(100);
        Integer findable = arr[arr.length / 5];
        Arrays.parallelSort(arr);

        BinarySearch<Integer> binarySearch = new BinarySearch<>();
        System.out.println("массив для поиска" + Arrays.toString(arr) + "\nбудем искать (" + findable + ")");
        System.out.println("элемент со значением (" + findable + ") имет индекс " + binarySearch.binarySearch(arr, findable) );

        System.out.println("Теперь проверим это методом из стандартной библиотеки" +
                "\nэлемент со значением (" + findable + ") имет индекс " + Arrays.binarySearch(arr, findable));
    }

    public void mathPow() {
        System.out.println("демонстрация работы рекурсивного возведения в степень через цыкл и метод");
        MyMath myMath = new MyMath();
        int a = 5;
        int n = 10;
        long start;
        System.out.println("основание a = " + a + " степень" + n);
        System.out.println("используя цыкл");
        start = System.currentTimeMillis();
        System.out.println("мы получили ответ: " + myMath.powFunction(a, n) + " за " + (System.currentTimeMillis() - start) + "млс");

        start = System.currentTimeMillis();
        System.out.println("используя рекурсивный вызов метода");
        System.out.println("мы получили ответ: " + myMath.powRecurs(a, n) + " за " + (System.currentTimeMillis() - start) + "млс");

        start = System.currentTimeMillis();
        System.out.println("Контрольное значение");
        System.out.println("мы получили ответ: " + Math.pow(a,n) + " за " + (System.currentTimeMillis() - start) + "млс");
    }

    public Integer[] getRandomArr(int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < length ; i++) {
            arr[i] = this.gerRandom(length);
        }
        return arr;
    }

    public Integer gerRandom(int seed) {
        return this.random.nextInt(seed);
    }

    public float getRandom(float seed) {
        return this.random.nextFloat() * seed;
    }

}
