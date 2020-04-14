package xokyopo.linkedList.lists.entitys;

public class DualLink<T> extends Link<T> {
    private Link<T> next;

    public DualLink() {
    }

    public DualLink(T value) {
        super(value);
    }

    public DualLink(T value, Link<T> next) {
        super(value, next);
    }

    public Link<T> getNext() {
        return next;
    }

    public void setNext(Link<T> next) {
        this.next = next;
    }
}
