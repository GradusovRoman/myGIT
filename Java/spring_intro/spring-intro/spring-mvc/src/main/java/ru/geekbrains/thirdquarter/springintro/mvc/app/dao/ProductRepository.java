package ru.geekbrains.thirdquarter.springintro.mvc.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(int min, int max);
    List<Product> findAllByCostAfter(int min);
}
