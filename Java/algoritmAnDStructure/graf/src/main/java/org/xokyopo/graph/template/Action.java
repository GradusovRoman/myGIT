package org.xokyopo.graph.template;

public interface Action<T> {
    void accept(T element);
}
