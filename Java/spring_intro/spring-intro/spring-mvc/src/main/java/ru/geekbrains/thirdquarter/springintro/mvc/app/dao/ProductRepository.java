package ru.geekbrains.thirdquarter.springintro.mvc.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByCostBetween(int min, int max, Pageable pageable);

    Page<Product> findAllByCostAfter(int min, Pageable pageable);
}
