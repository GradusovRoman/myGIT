package xokyopo.stackqueuedeque.myrealization;

public class Deque<T> extends Queue<T>{

    public Deque() {
    }

    public Deque(int capacity) {
        super(capacity);
    }

    @Override
    public synchronized void put(T element) {
        this.putLast(element);
    }

    @Override
    public synchronized T get() {
        return getLast();
    }

    public synchronized T getFirst() {
        return super.get();
    }

    public synchronized void putFirst(T element) {
        if (this.isFull()) this.increasedCapacity();
        this.first = (this.first == 0)? this.arr.length - 1: --this.first;
        this.arr[first] = element;
        this.size++;
    }

    public synchronized T getLast() {
        if (this.isEmpty()) throw new ArrayStoreException("storage is empty");
        int last = (this.first + --this.size) % this.arr.length;
        T buffer = this.arr[last];
        this.arr[last] = null;
        return buffer;
    }

    public synchronized void putLast(T element) {
        super.put(element);
    }
}
