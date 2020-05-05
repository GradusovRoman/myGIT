package gb.xokyopo.servlets.ui.local;

import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.OrderRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Named
@SessionScoped
public class CartManipulation implements Serializable {
    @EJB(beanName = "OrdersService")
    private ServiceImpl<OrderRep> orderRepService;

    private OrderRep orderRep;

    public CartManipulation() {
        this.orderRep = new OrderRep();
        this.orderRep.setProductRepIntegerMap(new HashMap<>());
    }

    public Set<Map.Entry<ProductRep, Integer>> getAll() {
        return this.orderRep.getProductRepIntegerSet();
    }

    public void addProduct(ProductRep productRep) {
        this.orderRep.getProductRepIntegerMap().put(
                productRep, this.orderRep.getProductRepIntegerMap().getOrDefault(productRep, 0) + 1
        );
    }

    public String deleteProduct(ProductRep productRep) {
        this.orderRep.getProductRepIntegerMap().remove(productRep);
        return "/cart.xhtml?faces-redirect=true";
    }

    public int getSum() {
        return this.orderRep.getFullPrice();
    }

    public String createOrder() {
        if (this.orderRep.getProductRepIntegerMap().size() > 0) {
            this.orderRepService.add(this.orderRep);
            this.orderRep = new OrderRep();
            this.orderRep.setProductRepIntegerMap(new HashMap<>());
        }
        return "/cart.xhtml?faces-redirect=true";
    }
}
