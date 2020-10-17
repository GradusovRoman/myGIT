package ru.geekbrains.thirdquarter.springintro.springboot.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface ARepository<E, I extends Number> extends JpaRepository<E, I>, PagingAndSortingRepository<E, I> {
}
