package org.xokyopo.hashtabel.hashset;

import java.util.LinkedList;

public class HashSet<T> implements HashSetInterface<T> {
    private LinkedList<T>[] arr;
    private int size;
    private final int DEFAULT_CAPACITY = 10;

    public HashSet() {
        this.init(DEFAULT_CAPACITY);
    }

    public HashSet(int capacity) {
        if (capacity > this.DEFAULT_CAPACITY) {
            this.init(capacity);
        } else {
            this.init(this.DEFAULT_CAPACITY);
        }
    }

    private void init(int length) {
        this.arr = this.creatArrWithList(length);
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = new LinkedList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private LinkedList<T>[] creatArrWithList(int length) {
        return new LinkedList[length];
    }

    @SuppressWarnings("unchecked")
    private T[] createArWithObject(int length) {
        return (T[])new Object[length];
    }

    private int getKeyByHash(T key) {
        return (key.hashCode() & 0x7fffffff) % this.arr.length;
    }

    private LinkedList<T> getListByKey(int key) {
        return this.arr[key];
    }

    private T getObjectByHash(LinkedList<T> list, T object) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).hashCode() == object.hashCode()) {
                return list.get(i);
            }
        }
        return null;
    }

    private LinkedList<T> getLIstByObject(T object) {
        return this.getListByKey(this.getKeyByHash(object));
    }

    private void requiredNotNullObject(Object object) {
        if (object == null) throw new NullPointerException("Object can't be null");
    }

    @Override
    public final void put(T object) {
        this.requiredNotNullObject(object);
        LinkedList<T> currentList = this.getLIstByObject(object);
        T element = this.getObjectByHash(currentList, object);
        if (element == null) this.size++;
        currentList.remove(element);
        currentList.add(object);
    }

    @Override
    public final T get(T object) {
        this.requiredNotNullObject(object);
        return this.getObjectByHash(this.getLIstByObject(object), object);
    }

    @Override
    public final void del(T object) {
        this.requiredNotNullObject(object);
        LinkedList<T> currentList = this.getLIstByObject(object);
        T element = this.getObjectByHash(currentList, object);
        if (element != null) {
            currentList.remove(element);
            this.size--;
        }
    }

    @Override
    public final T[] values() {
        if (!this.isEmpty()) {
            T[] outer = this.createArWithObject(this.size);
            int currentIndex = 0;
            for (int i = 0; i < this.arr.length; i++) {
                int length = this.arr[i].size();
                this.filingArr(this.arr[i].toArray(this.createArWithObject(0)), outer, currentIndex, length);
                currentIndex += length;
            }
            return outer;
        }
        return null;
    }

    private void filingArr(T[] src, T[] dest, int destPos, int length) {
        for (int i = 0; i < length; i++) {
            dest[i + destPos] = src[i];
        }
    }

    @Override
    public final int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size <= 0;
    }
}
