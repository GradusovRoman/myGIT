package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.dao.table.Product;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
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
    @SuppressWarnings("unchecked")
    @TransactionAttribute
    public List<Product> getAll() {
        Query query = em.createQuery("SELECT c FROM Product c", Product.class);
        return (List<Product>) query.getResultList();
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
