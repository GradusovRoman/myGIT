package xokyopo.linkedList.lists;

import xokyopo.linkedList.lists.entitys.Findable;
import xokyopo.linkedList.lists.entitys.Link;

import java.util.EmptyStackException;

public class MyLinkedList<T> {
    protected Link<T> last;
    protected int size;

    public interface Action<K> {
        void accept(K element);
    }

    //нужно что бы избавиться от Unchecked Cast
    public static class Buffer<T> {
        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() <= 0;
    }

    //выбрасывает исключение если елемент не задан
    protected void requireNotNullObject(Object element) {
        if (element == null) throw new NullPointerException("element can't be null");
    }

    //выбрасывает исключение если список пуст
    protected void requireNotEmptyList() {
        if (this.isEmpty()) throw new EmptyStackException();
    }

    //выбрасывает исключение если индекс некоректен
    protected void checkingValidIndex(int index) {
        if (this.size() <= index || index < 0) throw new IndexOutOfBoundsException();
    }

    protected void requireNotBelowZero(int index) {
        if (index < 0) throw new IndexOutOfBoundsException("index can't be below zero");
    }

    public void insert(T element) {
        this.requireNotNullObject(element);
        this.last = new Link<>(element, this.last);
        this.size++;
    }

    protected Link<T> creatingLink(T value) {
        return new Link<>(value);
    }

    protected Link<T> creatingLink(T value, Link<T> precedingLink) {
        return new Link<>(value, precedingLink);
    }

    public void insert(T element, int index) {
        this.requireNotNullObject(element);

        if (size <= index) {
            this.last = this.creatingLink(element, this.last);
        } else {
                Link<T> previousLink = this.getLinkByIndex(Math.max(index, 0));
                previousLink.setPreceding(this.creatingLink(element, previousLink.getPreceding()));
        }
        this.size++;
    }

    public T delete() {
        this.requireNotEmptyList();

        Link<T> buffer = this.last;
        this.last = buffer.getPreceding();
        this.size--;
        return buffer.getValue();
    }

    public T deleteByIndex(int index) {
        this.requireNotEmptyList();
        this.checkingValidIndex(index);
        Link<T> buffer;

        if (size - 1 == index) {
            buffer = this.last;
            this.last = buffer.getPreceding();
        } else {
            Link<T> previousLink = this.getLinkByIndex(index + 1);
            buffer = previousLink.getPreceding();
            previousLink.setPreceding(buffer.getPreceding());
        }

        this.size--;
        return buffer.getValue();
    }

    protected Link<T> getLinkByIndex(int index) {
        final int[] count = new int[]{size - index};
        final Buffer<Link<T>> currentLink = new Buffer<>();

        this.forEachLink(link -> {
            count[0]--;
            if (count[0] == 0) {
                currentLink.setValue(link);
                return;
            }
        });
        return currentLink.getValue();
    }

    //думаю правильно считать индексы с хвоста (потому что в противном
    // случае при каждом новом добавлении элемента они будут смещаться, что
    // в принцыпе не коректное поведение для самой концепции индекса)

    public int getIndexOfElement(T element) {
        this.requireNotEmptyList();
        this.requireNotNullObject(element);

        final int[] count = new int[2];
        count[0] = this.size;
        count[1] = -1;
        this.forEach(fElement ->{
            count[0]--;
            if(fElement.equals(element)) {
                count[1] = count[0];
                return;
            }
        } );

        return count[1];
    }

    //поиск элемента по пользовательским параметрам
    public T find(Findable<T> findable) {
        this.requireNotNullObject(findable);
        final Buffer<Link<T>> findingLink = new Buffer<>();

        this.forEachLink(link->{
            if (findable.find(link.getValue())) {
                findingLink.setValue(link);
                return;
            }
        });

        return findingLink.getValue().getValue();
    }

    public void forEach(Action<T> action) {
        this.forEachLink(link->{
            action.accept(link.getValue());
        });
    }

    protected void forEachLink(Action<Link<T>> action) {
        Link<T> currentLink = this.last;
        while (currentLink != null) {
            action.accept(currentLink);
            currentLink = currentLink.getPreceding();
        }
    }

    public ListIterator<T> getIterator() {
        return new ListIterator<T>(this);
    }

    protected Link<T> getLast() {
        return this.last;
    }
}
