package gb.xokyopo.servlets.service.represantations;

import java.io.Serializable;

public class ProductRep implements Serializable {
    private int id;
    private String name;
    private int price;
    private CategoryRep categoryRep;

    public ProductRep() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CategoryRep getCategoryRep() {
        return categoryRep;
    }

    public void setCategoryRep(CategoryRep categoryRep) {
        this.categoryRep = categoryRep;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ProductRep) {
            return this.id == ((ProductRep) obj).getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ("" + id).hashCode();
    }
}
