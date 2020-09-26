package ru.geekbrains.thirdquarter.hibernate.dao;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class EntitysRepository<T> {
    private final Class<T> templateClass;
    private final EntityManagerFactory entityManagerFactory;

    public EntitysRepository(Class<T> templateClass) {
        this.templateClass = templateClass;
        this.entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void add(T entity) {
        this.execute((em) -> em.persist(entity));
    }

    public void update(T entity) {
        this.execute((em) -> em.merge(entity));
    }

    public void delete(T entity) {
        this.execute((em) -> em.remove(entity));
    }

    public T getById(long id) {
        return Optional
                .of(this.returnExecuteCommand((em) -> em.find(this.getTemplateClass(), id)))
                .orElseThrow(() -> new IllegalArgumentException("entity not found"));
    }

    public List<T> getAll() {
        return this.returnExecuteCommand((em) -> em.createQuery(
                String.format("FROM %s", this.getTemplateClass().getSimpleName()),
                this.getTemplateClass()).getResultList());
    }

    public Class<T> getTemplateClass() {
        return templateClass;
    }

    private void execute(EntityManagerCommand command) {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        command.exec(em);
        em.getTransaction().commit();
        em.close();
    }

    private <K> K returnExecuteCommand(EntityManagerReturnCommand<K> command) {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        K result;
        em.getTransaction().begin();
        result = command.exec(em);
        em.getTransaction().commit();
        em.close();
        return result;
    }
}
