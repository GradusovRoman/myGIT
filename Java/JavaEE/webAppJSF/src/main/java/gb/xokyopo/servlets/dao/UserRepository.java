package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "UserRepository")
public class UserRepository implements Repository<User> {
    @PersistenceContext(unitName = "postgres")
    private EntityManager em;
    
    @Override
    @TransactionAttribute
    public boolean create(User element) {
        this.em.persist(element);
        return true;
    }

    @Override
    @TransactionAttribute
    public boolean update(User element) {
        this.em.merge(element);
        return true;
    }

    @Override
    @TransactionAttribute
    public boolean delete(int id) {
        this.em.remove(this.findById(id));
        return true;
    }

    @Override
    @TransactionAttribute
    public List<User> getAll() {
        return this.em.createQuery("SELECT c FROM User c", User.class).getResultList();
    }

    @Override
    @TransactionAttribute
    public User findById(int id) {
        return this.em.find(User.class, id);
    }
}
