package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.Product;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "ProductRepository")
public class ProductRepository implements Repository<Product> {
    @PersistenceContext(unitName = "postgres")
    protected EntityManager em;

    @Override
    @TransactionAttribute
    public boolean create(Product element) {
        em.persist(element);
        return false;
    }

    @Override
    @TransactionAttribute
    public boolean update(Product element) {
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
    public List<Product> getAll() {
        return this.em.createQuery("SELECT c FROM Product c", Product.class).getResultList();
    }

    @Override
    @TransactionAttribute
    public Product findById(int id) {
        return em.find(Product.class, id);
    }

    @TransactionAttribute
    public Product getByName(String name) {
        //TODO поиск по имени.
        return new Product();
    }
}
