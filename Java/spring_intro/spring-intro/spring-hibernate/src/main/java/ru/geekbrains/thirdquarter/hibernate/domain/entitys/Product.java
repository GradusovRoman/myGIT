package ru.geekbrains.thirdquarter.hibernate.domain.entitys;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int cost;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "products")
    private List<Buyer> buyers;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public Product setId(long id) {
        this.id = id;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setCost(int cost) {
        this.cost = cost;
        return this;
    }

    public Product setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
        return this;
    }

    @Override
    public String toString() {
        return String.format("{id = %s; name = %s; cost = %s; BuyersCount = %s}", id, name, cost, buyers.size());
    }
}
