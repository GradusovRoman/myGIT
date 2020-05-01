package gb.xokyopo.remote.ui;


import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.remote.impl.EjbRemoteImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CatalogManipulation implements Serializable {
    @EJB(beanName = "RemoteService")
    private EjbRemoteImpl productsService;

    public List<ProductRep> getAll() {
        return this.productsService.getAllProducts();
    }
}
