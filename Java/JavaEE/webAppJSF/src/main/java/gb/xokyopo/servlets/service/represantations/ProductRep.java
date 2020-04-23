package gb.xokyopo.servlets.service.represantations;

public class ProductRep {
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
}
