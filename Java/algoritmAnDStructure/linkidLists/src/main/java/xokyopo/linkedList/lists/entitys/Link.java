package xokyopo.linkedList.lists.entitys;

public class Link<T> {
    private T value;
    private Link<T> preceding;

    public Link() {
    }

    public Link(T value, Link<T> next) {
        this.value = value;
        this.preceding = next;
    }

    public Link(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Link<T> getPreceding() {
        return preceding;
    }

    public void setPreceding(Link<T> last) {
        this.preceding = last;
    }

    public boolean hasPreceding() {
        return this.preceding != null;
    }
}
