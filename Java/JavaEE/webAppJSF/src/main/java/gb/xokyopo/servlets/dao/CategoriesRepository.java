package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.interfaces.Repository;
import gb.xokyopo.servlets.dao.table.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.Transactional;

import java.util.List;

@Named
@ApplicationScoped
public class CategoriesRepository implements Repository<Category> {
    @PersistenceContext(unitName = "postgres")
    protected EntityManager em;

    @Override
    @Transactional
    public boolean create(Category element){
        this.em.persist(element);
        return true;
    }

    @Override
    @Transactional
    public boolean update(Category element) {
        Category updatedCategory = this.findById(element.getId());
        updatedCategory.update(element);
        this.em.merge(updatedCategory);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        this.em.remove(this.findById(id));
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<Category> getAll() {
        Query query = this.em.createQuery("SELECT c FROM Category c", Category.class);
        return (List<Category>) query.getResultList();
    }

    @Override
    @Transactional
    public Category findById(int id) {
        return em.find(Category.class, id);
    }
}
