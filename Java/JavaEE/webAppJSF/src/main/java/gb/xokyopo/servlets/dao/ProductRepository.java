package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.entity.template.AbstractProduct;
import gb.xokyopo.servlets.dao.entity.template.AddProductException;

import java.util.List;

public interface ProductRepository {
    boolean add(AbstractProduct abstractProduct) throws DAOException;
    boolean delete(AbstractProduct abstractProduct) throws DAOException;
    <T extends AbstractProduct> List<T> getAll(T abstractProduct) throws DAOException, AddProductException;
    boolean update(AbstractProduct abstractProduct) throws DAOException;
}
