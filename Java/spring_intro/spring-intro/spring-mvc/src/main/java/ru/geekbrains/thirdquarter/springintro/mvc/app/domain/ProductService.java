package ru.geekbrains.thirdquarter.springintro.mvc.app.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.geekbrains.thirdquarter.springintro.mvc.app.dao.Repository;
import ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities.Product;

import java.util.List;

@Component
public class ProductService {
    private Repository<Product> repository;

    @Autowired
    public ProductService(Repository<Product> repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return this.repository.getAll();
    }

    public Product getById(long id) {
        return  this.repository.getById(id);
    }

    public void delById(long id) {
        this.repository.delete(id);
    }

    public void create(Product product) {
        this.repository.create(product);
    }

    public void update(Product product) {
        this.repository.update(product);
    }
}
