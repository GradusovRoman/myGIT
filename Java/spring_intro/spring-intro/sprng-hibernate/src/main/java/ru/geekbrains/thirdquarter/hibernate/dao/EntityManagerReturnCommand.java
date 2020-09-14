package ru.geekbrains.thirdquarter.hibernate.dao;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EntityManagerReturnCommand<T> {
    T exec(EntityManager entityManager);
}
