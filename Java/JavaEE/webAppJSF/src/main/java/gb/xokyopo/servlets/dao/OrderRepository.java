package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.interfaces.Repository;
import gb.xokyopo.servlets.dao.table.Orders;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class OrderRepository implements Repository<Orders> {
    @PersistenceContext(unitName = "postgres")
    private EntityManager em;

    @Override
    @Transactional
    public boolean create(Orders element) {
        em.persist(element);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Orders element) {
        em.merge(element);
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int elementID) {
        this.em.remove(this.findById(elementID));
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Orders> getAll() {
        Query query = em.createQuery("SELECT c FROM Orders c", Orders.class);
        return (List<Orders>) query.getResultList();
    }

    @Override
    @Transactional
    public Orders findById(int id) {
        return em.find(Orders.class, id);
    }
}
