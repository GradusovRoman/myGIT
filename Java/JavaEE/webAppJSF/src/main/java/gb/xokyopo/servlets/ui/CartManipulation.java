package gb.xokyopo.servlets.ui;

import gb.xokyopo.servlets.service.entity.Product;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Named
@SessionScoped
public class CartManipulation implements Serializable {
    private Map<Product, Integer> productList;

    public CartManipulation() {
        this.productList = new HashMap<>();
    }

    public Set<Map.Entry<Product, Integer>> getAll() {
        return this.productList.entrySet();
    }

    public void addProduct(Product product) {
        this.productList.put(product, this.productList.getOrDefault(product, 0) + 1);
    }

    public String deleteProduct(Product product) {
        this.productList.remove(product);
        return "/cart.xhtml";
    }

}
