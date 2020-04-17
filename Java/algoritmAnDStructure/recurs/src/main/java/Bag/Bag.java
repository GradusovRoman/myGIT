package Bag;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Bag {
    private final float weightCapacity;
    private float leftWeight;
    private List<Item> itemList;

    public Bag(float weightCapacity) {
        this.weightCapacity = weightCapacity;
        this.leftWeight = weightCapacity;
        this.itemList = new ArrayList<>();
    }

    public void add(Item item) {
        if (item.getWeight() <= this.leftWeight) {
            this.leftWeight -= item.getWeight();
            this.itemList.add(item);
        }
    }

    public int getCost() {
        int[] cost = new int[1];
        this.itemList.forEach(item->{
            cost[0] += item.getPrice();
        });
        return cost[0];
    }

    public void erase() {
        this.itemList.clear();
        this.leftWeight = this.weightCapacity;
    }

    public String getInfo() {
        return "bag can contain " + this.weightCapacity + " unit";
    }

    public int getItemCount() {
        return this.itemList.size();
    }

    public float getLeftWeight() {
        return leftWeight;
    }

    public int recursSearch(Item[] items, int startItem, float leftWeight) {
        if (items.length - 1 == startItem) return 0;
        else if (items[startItem].getWeight() > leftWeight) return 0;

        return Math.max(
                this.recursSearch(items, startItem + 1, leftWeight),
                items[startItem].getPrice() + this.recursSearch(items, startItem + 1, leftWeight - items[startItem].getWeight())
        );
    }

}
