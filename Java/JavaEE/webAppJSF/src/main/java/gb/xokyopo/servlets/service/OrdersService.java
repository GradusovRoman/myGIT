package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Orders;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.OrderRep;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless(name = "OrdersService")
public class OrdersService implements ServiceImpl<OrderRep> {
    @EJB(beanName = "ServiceUtils")
    private ServiceUtils serviceUtils;

    @Override
    public List<OrderRep> getAll() {
        return this.serviceUtils.getOrderRepository().getAll().stream()
                .map(this.serviceUtils::ordersToOrderRep)
                .collect(Collectors.toList());
    }

    @Override
    public void add(OrderRep element) {
        this.serviceUtils.getOrderRepository().create(this.serviceUtils.orderRepToOrders(element, new Orders()));
    }

    @Override
    public void update(OrderRep element) {
        this.serviceUtils.getOrderRepository().update(
                this.serviceUtils.orderRepToOrders(element, this.serviceUtils.getOrderRepository().findById(element.getId())));
    }

    @Override
    public void delete(OrderRep element) {
        this.serviceUtils.getOrderRepository().delete(element.getId());
    }

    @Override
    public OrderRep getById(int elementId) {
        return this.serviceUtils.ordersToOrderRep(this.serviceUtils.getOrderRepository().findById(elementId));
    }
}
