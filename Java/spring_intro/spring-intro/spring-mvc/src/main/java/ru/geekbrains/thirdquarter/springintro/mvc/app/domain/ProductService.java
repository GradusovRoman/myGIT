package ru.geekbrains.thirdquarter.springintro.mvc.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.thirdquarter.springintro.mvc.app.dao.ProductRepository;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public List<Product> getAllByPriceFilter(Integer min, Integer max) {
        if (max == null) return this.repository.findAllByCostAfter(Optional.of(min).orElse(0));
        return this.repository.findAllByCostBetween(Optional.of(min).orElse(0), max);
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
