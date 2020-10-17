package ru.geekbrains.thirdquarter.springintro.springboot.app.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.thirdquarter.springintro.springboot.app.dao.ProductsRepository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Product;

import java.util.Optional;

@Service
public class ProductsService extends AService<ProductsRepository, Product, Long> {
    @Override
    protected Product createEmptyEntity() {
        return new Product();
    }

    public Page<Product> getAllByPriceFilter(Integer min, Integer max, Pageable pageable) {
        if (max == null) return this.repository.findAllByCostAfter(Optional.ofNullable(min).orElse(0), pageable);

        return this.repository.findAllByCostBetween(Optional.ofNullable(min).orElse(0), max, pageable);
    }

}
