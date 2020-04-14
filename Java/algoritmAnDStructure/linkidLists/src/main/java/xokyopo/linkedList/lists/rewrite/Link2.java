package xokyopo.linkedList.lists.rewrite;

public class Link2<T> {
    private T value;
    protected Link2<T> next;

    public Link2() {
    }

    public Link2(T value, Link2<T> next) {
        this.value = value;
        this.next = next;
    }

    public Link2(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public Link2<T> getNext() {
        return next;
    }

    public void setNext(Link2<T> next) {
        this.next = next;
    }
}
