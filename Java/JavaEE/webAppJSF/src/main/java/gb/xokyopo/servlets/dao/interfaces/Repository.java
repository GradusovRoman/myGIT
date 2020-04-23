package gb.xokyopo.servlets.dao.interfaces;

import javax.transaction.Transactional;
import java.util.List;

public interface Repository<T>{
    boolean create(T element);
    boolean update(T element);
    boolean delete(int id);
    List<T> getAll();
    T findById(int id);
}
