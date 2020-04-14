package xokyopo.linkedList.queue;

import xokyopo.linkedList.lists.MyDualLinkList;

public class Queue<T> {
    private MyDualLinkList<T> linkList;

    public Queue() {
        this.linkList = new MyDualLinkList<>();
    }

    public void put(T item) {
        this.linkList.insertFirst(item);
    }

    public T get() {
        return this.linkList.deleteLast();
    }

    public int size() {
        return this.linkList.size();
    }

    public boolean isEmpty() {
        return this.linkList.isEmpty();
    }
}
