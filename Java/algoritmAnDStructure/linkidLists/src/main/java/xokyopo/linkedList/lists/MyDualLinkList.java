package xokyopo.linkedList.lists;

import xokyopo.linkedList.lists.entitys.DualLink;
import xokyopo.linkedList.lists.entitys.Link;

public class MyDualLinkList <T> extends MyLinkedList<T> {
    protected DualLink<T> first;

    @Override
    public void insert(T element) {
        this.insertLast(element);
    }

    @Override
    public void insert(T element, int index) {
        this.requireNotNullObject(element);
        this.requireNotBelowZero(index);

        DualLink<T> newLink;
        if (size <= index) {
            newLink = (DualLink<T>) this.creatingLink(element, this.last);
            ((DualLink<T>)this.last).setNext(newLink);
            this.last = newLink;
        } else {
            Link<T> previousLink = this.getLinkByIndex(index);
            newLink = (DualLink<T>) this.creatingLink(element, previousLink.getPreceding());
            newLink.setNext(previousLink);
            previousLink.setPreceding(newLink);
        }
        this.size++;
    }

    @Override
    public T deleteByIndex(int index) {
        this.requireNotEmptyList();
        this.checkingValidIndex(index);
        DualLink<T> buffer;

        if (size - 1 == index) {
            buffer = (DualLink<T>) this.last;
            this.last = buffer.getPreceding();
            ((DualLink<T>) this.last).setNext(null);
        } else {
            DualLink<T> previousLink = (DualLink<T>) this.getLinkByIndex(index + 1);
            buffer = (DualLink<T>) previousLink.getPreceding();
            previousLink.setPreceding(buffer.getPreceding());
            previousLink.setNext(buffer.getNext());
        }

        this.size--;
        return buffer.getValue();
    }

    @Override
    public T delete() {
        return this.deleteLast();
    }

    @Override
    protected Link<T> creatingLink(T value) {
        return new DualLink<>(value);
    }

    @Override
    protected Link<T> creatingLink(T value, Link<T> precedingLink) {
        return new DualLink<>(value, precedingLink);
    }

    public void insertLast(T element) {
        this.requireNotNullObject(element);
        if (this.isEmpty()) {
            this.first = (DualLink<T>) this.creatingLink(element);
            this.last = this.first;
        } else {
            DualLink<T> newLink = (DualLink<T>) this.creatingLink(element);
            ((DualLink<T>)this.last).setNext(newLink);
            newLink.setPreceding(this.last);
            this.last = newLink;
        }
        this.size++;
    }

    public void insertFirst(T element) {
        this.requireNotNullObject(element);

        if (this.isEmpty()) {
            this.first = (DualLink<T>) this.creatingLink(element);
            this.last = this.first;
        } else {
            DualLink<T> newLink = (DualLink<T>) this.creatingLink(element);
            newLink.setNext(this.first);
            this.first.setPreceding(newLink);
            this.first = newLink;
        }

        this.size++;
    }

    public T deleteLast() {
        this.requireNotEmptyList();
        DualLink<T> buffer = (DualLink<T>) this.last;
        this.last = buffer.getPreceding();
        if (this.last != null) ((DualLink<T>) this.last).setNext(null);
        this.size--;
        return buffer.getValue();
    }

    public T deleteFirst() {
        this.requireNotEmptyList();

        DualLink<T> buffer = this.first;
        this.first = (DualLink<T>) this.first.getNext();
        if (this.first != null) this.first.setPreceding(null);
        this.size--;
        return buffer.getValue();
    }

}
