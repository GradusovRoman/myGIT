package gb.xokyopo.servlets.dao.table;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    private List<OrderRow> orderRowList;

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderRow> getOrderRowList() {
        return orderRowList;
    }

    public void setOrderRowList(List<OrderRow> orderRowList) {
        this.orderRowList = orderRowList;
    }
}
