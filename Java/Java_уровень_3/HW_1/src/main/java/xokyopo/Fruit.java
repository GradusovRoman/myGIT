package xokyopo;

public abstract class Fruit {
    private float weight;

    public Fruit() {
    }

    public Fruit(float weight) {
        this.weight = weight;
    }

    public float getWeight() {
        return this.weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}
