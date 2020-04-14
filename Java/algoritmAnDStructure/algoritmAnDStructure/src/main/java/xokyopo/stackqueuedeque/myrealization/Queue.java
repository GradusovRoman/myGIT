package xokyopo.stackqueuedeque.myrealization;

public class Queue <T> extends Stack<T>{
    protected int first;

    public Queue() {
    }

    public Queue(int capacity) {
        super(capacity);
    }

    @Override
    protected synchronized void resizeArrByCapacity(int capacity) {
        if (this.arr == null || this.size == 0) {
            this.arr = this.createNeaArr(capacity);
        } else {
            T[] buffer = this.createNeaArr(capacity);

            for (int i = 0; i < this.size; i++) {
                buffer[i] = this.arr[(this.first + i) % arr.length];
            }

            this.first = 0;
            this.arr = buffer;
        }

    }

    public synchronized void put(T element) {
        if (this.isFull()) this.increasedCapacity();
        this.arr[(this.first + size) % this.arr.length] = element;
        this.size++;
    }

    public synchronized T get() {
        if (this.isEmpty()) throw new ArrayStoreException("storage is empty");
        this.first = this.first % arr.length;
        T buffer = this.arr[this.first];
        this.arr[this.first] = null;
        this.first++;
        this.size--;
        return buffer;
    }
}
