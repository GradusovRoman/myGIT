package xokyopo;

/*
Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
+Для хранения фруктов внутри коробки можно использовать ArrayList;
+Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
+Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
+Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
+Не забываем про метод добавления фрукта в коробку.
 */

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private ArrayList<T> boxes = new ArrayList<>();

    public Box(ArrayList<T> boxes) {
        this.boxes = boxes;
    }

    public Box() {
    }

    public Box(T... fruits) {
        this.putFruit(fruits);
    }

    public float getWeight () {
        ArrayList<T> arr = (ArrayList<T>) boxes.clone();
        float count = 0.0f;
        for (T fruit: arr) {
            count += fruit.getWeight();
        }
        return count;
    }

    public boolean compare (Box<?> boxForCompare) {
        return boxForCompare.getWeight() == this.getWeight();
    }

    public T takeFruit () {
        if (!boxes.isEmpty()) {
            this.boxes.get(1);
            this.boxes.remove(1);
        }
        return null;
    }

    public T[] takeAllFruit() {
        T[] arr = (T[])this.boxes.toArray();
        this.boxes.clear();
        return arr;
    }

    public List<T> takeAllFruitAsList() {
        List<T> arr = (List<T>)this.boxes.clone();
        this.boxes.clear();
        return arr;
    }

    public void putFruit (T fruit) {
        this.boxes.add(fruit);
    }

    public void putFruit (T... fruits) {
        for (int i = 0; i < fruits.length; i++) {
            this.putFruit(fruits[i]);
        }
    }

    public void putFruit (Box<T> inBox) {
        this.boxes.addAll(inBox.takeAllFruitAsList());
    }
}
