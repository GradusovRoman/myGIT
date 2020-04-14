package xokyopo.stackqueuedeque.myrealization;

public class Stack <T>{
    protected T[] arr;
    protected static final int DEFAULT_CAPACITY = 10;
    protected int size;

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public Stack(int capacity) {
        this.setCapacity(capacity);
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    protected boolean isFull() {
        return this.getCapacity() == this.size;
    }

    public void setCapacity(int capacity) {
        if (capacity > 0 && this.size < capacity) {
            this.resizeArrByCapacity(capacity);
        } else {
            throw new IllegalArgumentException("capacity is too low");
        }
    }


    protected synchronized void resizeArrByCapacity(int capacity) {
        if (this.arr == null || this.size == 0) {
            this.arr = this.createNeaArr(capacity);
        } else {
            T[] buffer = this.createNeaArr(capacity);
            System.arraycopy(this.arr, 0, buffer, 0, this.getSize());
            this.arr = buffer;
        }

    }

    @SuppressWarnings("unchecked")
    protected T[] createNeaArr(int length) {
        return (T[]) new Object[length];
    }

    protected void increasedCapacity() {
        this.setCapacity(this.getCapacity() * 3 / 2);
    }

    public synchronized void put(T element) {
        if (this.isFull()) this.increasedCapacity();
        this.arr[size++] = element;
    }

    public synchronized T get() {
        if (this.isEmpty()) throw new ArrayStoreException("storage is empty");
        T buffer = this.arr[--size];
        this.arr[size] = null;
        return buffer;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return this.arr.length;
    }
}

