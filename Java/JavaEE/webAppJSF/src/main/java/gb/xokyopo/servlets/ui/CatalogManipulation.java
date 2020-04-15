package gb.xokyopo.servlets.ui;

import gb.xokyopo.servlets.service.entity.Product;
import gb.xokyopo.servlets.service.ProductsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class CatalogManipulation implements Serializable {
    private ProductsService productsService;

    @Inject
    public CatalogManipulation(ProductsService productsService) {
        this.productsService = productsService;
    }

    public List<Product> getAll() {
        return this.productsService.getAll();
    }

    public String deleteProduct(Product product) {
        this.productsService.deleteProduct(product);
        return "/catalog.xhtml";
    }
}
