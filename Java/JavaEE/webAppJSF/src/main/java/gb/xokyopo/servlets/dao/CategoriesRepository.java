package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.table.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class CategoriesRepository {
    private final EntityManager em = Persistence.createEntityManagerFactory("mysql").createEntityManager();

    @Transactional
    public boolean createCategory(Category category) {
        this.em.persist(category);
        return true;
    }

    @Transactional
    public boolean updateCategory(Category category) {
        this.em.refresh(category);
        return true;
    }

    public boolean deleteCategory(Category category) {
        this.em.remove(category);
        return true;
    }

    @SuppressWarnings("unchecked")
    public List<Category> getAll() {
        Query query = this.em.createQuery("SELECT c FROM c", Category.class);
        return query.getResultList();
    }
}
