package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Stateless(name = "CategoriesRepository")
public class CategoriesRepository implements Repository<Category> {
    @PersistenceContext(unitName = "postgres")
    protected EntityManager em;

    @Override
    @TransactionAttribute
    public boolean create(Category element){
        this.em.persist(element);
        return true;
    }

    @Override
    @TransactionAttribute
    public boolean update(Category element) {
        this.em.merge(element);
        return true;
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
    public List<Category> getAll() {
        Query query = this.em.createQuery("SELECT c FROM Category c", Category.class);
        return (List<Category>) query.getResultList();
    }

    @Override
    @TransactionAttribute
    public Category findById(int id) {
        return em.find(Category.class, id);
    }
}
