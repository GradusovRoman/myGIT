package ru.geekbrains.thirdquarter.hibernate.domain.entitys;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "buyers")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> products;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Buyer setId(long id) {
        this.id = id;
        return this;
    }

    public Buyer setName(String name) {
        this.name = name;
        return this;
    }

    public Buyer setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    @Override
    public String toString() {
        return String.format("{id = %s; name = %s; productsCount = %s}", id, name, products.size());
    }
}
