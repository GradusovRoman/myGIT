package gb.xokyopo.servlets.ui;

import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.service.ProductsService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class CatalogManipulation implements Serializable {
    private ProductsService productsService;

    @Inject
    public CatalogManipulation(ProductsService productsService) {
        this.productsService = productsService;
    }

    public List<ProductRep> getAll() {
        return this.productsService.getAll();
    }
}
