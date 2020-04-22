package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.table.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;

import java.util.List;

@Named
@ApplicationScoped
public class CategoriesRepository {
    @PersistenceContext(unitName = "postgres")
    protected EntityManager em;

    @Inject
    protected UserTransaction utx;

    @Transactional
    public boolean createCategory(Category category){
        System.out.println("======================================================");
        System.out.println("создаю сущьность");
        this.em.persist(category);
        System.out.println("======================================================");
        return true;
    }

    @Transactional
    public boolean updateCategory(Category category) {
        System.out.println("======================================================");
        System.out.println("изменяю сущьность");
        Category updatedCategory = em.find(Category.class, category.getId());
        updatedCategory.setName(category.getName());
        updatedCategory.setDescription(category.getDescription());
        this.em.refresh(updatedCategory);
        System.out.println("======================================================");
        return true;
    }

    @Transactional
    public boolean deleteCategory(Category category) {
        this.em.remove(category);
        return true;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Category> getAll() {
        Query query = this.em.createQuery("SELECT c FROM Category c", Category.class);
        return (List<Category>) query.getResultList();
    }
}
