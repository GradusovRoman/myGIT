package gb.xokyopo.servlets.service.impl;

import java.util.List;

public interface ServiceImpl<T> {
    List<T> getAll();
    void add(T element);
    void update(T element);
    void delete(int elementId);
    T getById(int elementId);
}
