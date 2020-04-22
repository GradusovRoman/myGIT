package gb.xokyopo.servlets.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
public class ProductRepository {
    @PersistenceContext(unitName = "postgres")
    protected EntityManager em;
}
