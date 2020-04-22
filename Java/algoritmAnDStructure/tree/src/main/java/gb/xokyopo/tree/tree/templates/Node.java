package gb.xokyopo.tree.tree.templates;

public class Node<K extends Comparable<K>, T> implements Comparable<Node<K, T>> {
    private T object;
    private K key;
    private Node<K,T> below;
    private Node<K,T> above;

    public Node(K key, T object) {
        this.object = object;
        this.key = key;
    }

    public T getObject() {
        return this.object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public Node<K, T> getBelow() {
        return below;
    }

    public void setBelow(Node<K, T> below) {
        this.below = below;
    }

    public Node<K, T> getAbove() {
        return above;
    }

    public void setAbove(Node<K, T> above) {
        this.above = above;
    }

    public int compareTo(Node<K, T> o) {
        return this.getKey().compareTo(o.getKey());
    }

    public int getDeep() {
        return Math.max(
                (this.above != null) ? this.above.getDeep() : 0,
                (this.below != null) ? this.below.getDeep() : 0
        ) + 1;
    }

    @Override
    public String toString() {
        if (this.object.getClass() == this.key.getClass()) {
            if (this.key.equals(this.object))
                return String.format("[%s]", this.object);
        }
        return String.format("[%s, %s]", key.toString(), this.object);
    }
}
