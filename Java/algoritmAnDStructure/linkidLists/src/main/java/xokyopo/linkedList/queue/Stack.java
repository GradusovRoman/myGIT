package xokyopo.linkedList.queue;

import xokyopo.linkedList.lists.MyLinkedList;

public class Stack<T> {
    private MyLinkedList<T> linkedList;

    public Stack() {
        this.linkedList = new MyLinkedList<>();
    }

    public void put(T item) {
        this.linkedList.insert(item);
    }

    public T get(){
        return this.linkedList.delete();
    }

    public int size() {
        return this.linkedList.size();
    }

    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }
}
