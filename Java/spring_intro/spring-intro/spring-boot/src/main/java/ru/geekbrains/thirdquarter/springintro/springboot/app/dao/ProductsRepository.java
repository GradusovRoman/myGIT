package ru.geekbrains.thirdquarter.springintro.springboot.app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Product;

@Repository
public interface ProductsRepository extends ARepository<Product, Long> {

    Page<Product> findAllByCostBetween(int min, int max, Pageable pageable);

    Page<Product> findAllByCostAfter(int min, Pageable pageable);
}
