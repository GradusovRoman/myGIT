package gb.xokyopo.servlets.dao;

import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.Group;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "GroupRepository")
public class GroupRepository implements Repository<Group> {
    @PersistenceContext(unitName = "postgres")
    private EntityManager em;

    @Override
    @TransactionAttribute
    public boolean create(Group element) {
        this.em.persist(element);
        return true;
    }

    @Override
    @TransactionAttribute
    public boolean update(Group element) {
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
    public List<Group> getAll() {
        return this.em.createQuery("SELECT c FROM Group c", Group.class).getResultList();
    }

    @Override
    @TransactionAttribute
    public Group findById(int id) {
        return this.em.find(Group.class, id);
    }
}
