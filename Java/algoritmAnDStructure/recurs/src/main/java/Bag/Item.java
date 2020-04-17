package Bag;

public class Item implements Comparable<Item>{
    private int price;
    private float weight;

    public Item(int price, float weight) {
        this.price = price;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Item item) {
        if (this.getWorth() == item.getWorth()) {
            return 0;
        } else if (this.getWorth() > item.getWorth()) {
            return -1;
        } else {
            return 1;
        }
    }

    public float getWorth() {
        return (float)this.price/this.weight;
    }

    public String getInfo() {
        return String.format("[price = %s; weight = %.4f unit]", this.price, this.weight);
    }
}
