package ru.geekbrains.thirdquarter.hibernate.dao;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EntityManagerCommand {
    void exec(EntityManager entityManager);
}
