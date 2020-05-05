package gb.xokyopo.servlets.ui.local;

import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.OrderRep;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrdersManipulation implements Serializable {
    @EJB(beanName = "OrdersService")
    private ServiceImpl<OrderRep> orderRepService;

    public String delete(OrderRep element) {
        this.orderRepService.delete(element);
        return "/orderslist.xhtml?faces-redirect=true";
    }

    public List<OrderRep> getAll() {
        return this.orderRepService.getAll();
    }
}
