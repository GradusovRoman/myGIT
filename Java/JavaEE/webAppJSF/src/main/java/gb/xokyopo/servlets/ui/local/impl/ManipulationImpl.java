package gb.xokyopo.servlets.ui.local.impl;

import java.util.List;

public interface ManipulationImpl<T> {
    String update(T element);
    String add();
    String delete(T element);
    String save();
    T getElement();
    void setElement(T element);
    String cancel();

    List<T> getAll();
}
