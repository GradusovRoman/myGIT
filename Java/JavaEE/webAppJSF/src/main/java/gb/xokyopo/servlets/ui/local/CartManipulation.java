package gb.xokyopo.servlets.ui.local;

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
    private Map<ProductRep, Integer> productMap;

    public CartManipulation() {
        this.productMap = new HashMap<>();
    }

    public Set<Map.Entry<ProductRep, Integer>> getAll() {
        return this.productMap.entrySet();
    }

    public void addProduct(ProductRep productRep) {
        this.productMap.put(productRep, this.productMap.getOrDefault(productRep, 0) + 1);
    }

    public String deleteProduct(ProductRep productRep) {
        this.productMap.remove(productRep);
        return "/cart.xhtml";
    }

    public int getSum() {
        int[] count = {0};
        this.productMap.forEach((productRep, integer) -> {
            count[0] += productRep.getPrice() * integer;
        });
        return count[0];
    }
}
