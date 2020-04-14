package xokyopo.linkedList.lists.rewrite;

import xokyopo.linkedList.lists.entitys.Findable;

import java.util.EmptyStackException;

public abstract class MyLinkedList2<T, K extends Link2<T>> {
    protected K first;
    protected int size;

    public interface Action<K> {
        void accept(K element);
    }

    //нужно что бы избавиться от Unchecked Cast
    public static class Buffer<Item> {
        private Item value;

        public Item getValue() {
            return value;
        }

        public void setValue(Item value) {
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

    abstract protected K creatingLink(T value);

    protected void insertLink(K previousLink, K insertingLink) {
        if (previousLink == null) {
            this.first = insertingLink;
        } else {
            insertingLink.setNext(previousLink.getNext());
            previousLink.setNext(insertingLink);
        }
        this.size++;
    }


    @SuppressWarnings("unchecked")
    protected void deleteLink(K previousLink, K deletingLink) {
        if (previousLink == null) {
            this.first = (K)deletingLink.getNext();
        } else {
            previousLink.setNext(deletingLink.getNext());
        }
        this.size--;
    }


    public void insert(T element) {
        this.requireNotNullObject(element);
        this.insertLink(null, this.creatingLink(element));
    }

    public void insert(T element, int index) {
        this.requireNotNullObject(element);

        if (index <= 0) {
            this.insert(element);
        } else {
            this.insertLink(this.getLinkByIndex(Math.min(index, size - 1)),this.creatingLink(element));
        }
    }

    public T delete() {
        this.requireNotEmptyList();
        T result = this.first.getValue();
        this.deleteLink(null, this.first);
        return result;
    }

    @SuppressWarnings("unchecked")
    public T deleteByIndex(int index) {
        this.requireNotEmptyList();
        this.checkingValidIndex(index);

        if (index == 0) {
            return this.delete();
        } else {
            T value;
            K previousLink = this.getLinkByIndex(index - 1);
            value = previousLink.getNext().getValue();
            this.deleteLink(previousLink, (K)previousLink.getNext());
            return value;
        }
    }

    protected K getLinkByIndex(int index) {
        int[] count = new int[2];
        count[1] = index;
        Buffer<K> buffer = new Buffer<>();
        this.forEachLink(link->{
            if(count[0] == count[1]) {
                buffer.setValue(link);
                return;
            }
        });
        return buffer.getValue();
    }

    //думаю правильно считать индексы с хвоста (потому что в противном
    // случае при каждом новом добавлении элемента они будут смещаться, что
    // в принцыпе не коректное поведение для самой концепции индекса)

    public int getIndexOfElement(T element) {
        //TODO
        return 0;
    }

    //поиск элемента по пользовательским параметрам
    public T find(Findable<T> findable) {
        this.forEach(element -> {
            if(findable.find(element)) return;
        });
        return null;
    }

    public void forEach(Action<T> action) {
        this.forEachLink(link->{
            action.accept(link.getValue());
        });
    }

    //uncast
    @SuppressWarnings("unchecked")
    protected void forEachLink(Action<K> action) {
        K currentLink = this.first;
        while (currentLink != null) {
            action.accept(currentLink);
            currentLink = (K) currentLink.getNext();
        }
    }

}
