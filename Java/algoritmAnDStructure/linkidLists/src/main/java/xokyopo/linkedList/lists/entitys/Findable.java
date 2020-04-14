package xokyopo.linkedList.lists.entitys;

@FunctionalInterface
public interface Findable<T> {
    boolean find(T element);
}
