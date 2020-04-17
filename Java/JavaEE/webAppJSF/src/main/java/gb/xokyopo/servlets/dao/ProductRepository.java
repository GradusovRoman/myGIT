package gb.xokyopo.servlets.dao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Named
@ApplicationScoped
public class ProductRepository {
    private EntityManager em = Persistence.createEntityManagerFactory("mysql").createEntityManager();
}
