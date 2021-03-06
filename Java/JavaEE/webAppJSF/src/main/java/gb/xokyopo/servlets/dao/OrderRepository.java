package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.Orders;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "OrderRepository")
public class OrderRepository implements Repository<Orders> {
    @PersistenceContext(unitName = "postgres")
    private EntityManager em;

    @Override
    @TransactionAttribute
    public boolean create(Orders element) {
        em.persist(element);
        return true;
    }

    @Override
    @TransactionAttribute
    public boolean update(Orders element) {
        em.merge(element);
        return false;
    }

    @Override
    @TransactionAttribute
    public boolean delete(int elementID) {
        this.em.remove(this.findById(elementID));
        return true;
    }

    @Override
    @TransactionAttribute
    public List<Orders> getAll() {
        return this.em.createQuery("SELECT c FROM Orders c", Orders.class).getResultList();
    }

    @Override
    @TransactionAttribute
    public Orders findById(int id) {
        return em.find(Orders.class, id);
    }
}
