package gb.xokyopo.servlets.dao;


import gb.xokyopo.servlets.dao.interfaces.Repository;
import gb.xokyopo.servlets.dao.table.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class ProductRepository implements Repository<Product> {
    @PersistenceContext(unitName = "postgres")
    protected EntityManager em;

    @Override
    @Transactional
    public boolean create(Product element) {
        em.persist(element);
        return false;
    }

    @Override
    @Transactional
    public boolean update(Product element) {
        Product updateProduct = this.findById(element.getId());
        updateProduct.update(element);
        em.merge(updateProduct);
        return false;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        em.remove(this.findById(id));
        return true;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Product> getAll() {
        Query query = em.createQuery("SELECT c FROM Product c ", Product.class);
        return (List<Product>) query.getResultList();
    }

    @Override
    @Transactional
    public Product findById(int id) {
        return em.find(Product.class, id);
    }
}
