package gb.xokyopo.servlets.ui;

import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Named
@SessionScoped
public class CartManipulation implements Serializable {
    private Map<ProductRep, Integer> productList;

    public CartManipulation() {
        this.productList = new HashMap<>();
    }

    public Set<Map.Entry<ProductRep, Integer>> getAll() {
        return this.productList.entrySet();
    }

    public void addProduct(ProductRep productRep) {
        this.productList.put(productRep, this.productList.getOrDefault(productRep, 0) + 1);
    }

    public String deleteProduct(ProductRep productRep) {
        this.productList.remove(productRep);
        return "/cart.xhtml";
    }

}
