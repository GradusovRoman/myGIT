package ru.geekbrains.thirdquarter.springintro.mvc.app.domain.entities;

import ru.geekbrains.thirdquarter.springintro.mvc.app.dao.Entity;

public class Product extends Entity {
    private String title;
    private int cost;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
