package ru.geekbrains.thirdquarter.springintro.springboot.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.thirdquarter.springintro.springboot.app.dao.ProductRepository;
import ru.geekbrains.thirdquarter.springintro.springboot.app.domain.entities.Product;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Page<Product> getAll(Pageable pageable) {
        return this.repository.findAll(pageable);
    }


    public Page<Product> getAllByPriceFilter(Integer min, Integer max, Pageable pageable) {
        if (max == null) return this.repository.findAllByCostAfter(Optional.ofNullable(min).orElse(0), pageable);

        return this.repository.findAllByCostBetween(Optional.ofNullable(min).orElse(0), max, pageable);
    }

    public Product getById(long id) {
        return this.repository.findById(id).get();
    }

    public void delById(long id) {
        this.repository.deleteById(id);
    }

    public void save(Product product) {
        this.repository.save(product);
    }

}
