package xokyopo.stackqueuedeque.myrealization;

public class PriorityQueue <T extends Comparable<T>> extends Stack<T>{
    public PriorityQueue() {
    }

    public PriorityQueue(int capacity) {
        super(capacity);
    }

    @Override
    public synchronized void put(T element) {
        super.put(element);
        this.sorting();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected T[] createNeaArr(int length) {
        return (T[]) new Comparable[length];
    }


    private synchronized void sorting() {
        for(int i = size - 1; i > 0 && this.arr[i].compareTo(this.arr[i - 1]) < 0; i--) {
            this.swap(i, i - 1);
        }
    }

    private synchronized void swap(int firstIndex, int lastIndex) {
        T buffer = this.arr[firstIndex];
        this.arr[firstIndex] = this.arr[lastIndex];
        this.arr[lastIndex] = buffer;
    }
}
