package xokyopo.linkedList.lists;

import xokyopo.linkedList.lists.entitys.Link;

public class ListIterator<T> {
    private MyLinkedList<T> myLinkedList;
    private Link<T> first;
    private Link<T> currentLink;
    private Link<T> previousLink;
    private int count;

    public ListIterator( MyLinkedList<T> myLinkedList) {
        this.myLinkedList = myLinkedList;
        this.reset();
    }

    public void reset() {
        this.first = this.myLinkedList.getLast();
        this.currentLink = first;
        this.previousLink = null;
        this.count = 0;
    }

    public boolean atEnd() {
        return this.currentLink == null;
    }

    public T getCurrent() {
        return this.currentLink.getValue();
    }

    public void nextLink() {
        if (this.currentLink != null) {
            this.previousLink = this.currentLink;
            this.currentLink = this.currentLink.getPreceding();
            this.count++;
        }
    }

    public void deleteCurrent() {
        this.currentLink = this.currentLink.getPreceding();
        this.myLinkedList.deleteByIndex(this.myLinkedList.size - this.count - 1);
        if (count == 0) this.first = this.myLinkedList.getLast();
    }

    //после
    public void insertAfter(T element) {
        this.myLinkedList.insert(element, this.myLinkedList.size() - this.count - 1);
    }

    //перед
    public void insertBefore(T element) {
        this.myLinkedList.insert(element, this.myLinkedList.size() - this.count);
        if(count == 0) {
            this.first = this.myLinkedList.getLast();
            this.currentLink = this.first.getPreceding();
            this.previousLink = this.first;
        } else {
            this.previousLink = this.previousLink.getPreceding();
        }
        this.count++;
    }

}
