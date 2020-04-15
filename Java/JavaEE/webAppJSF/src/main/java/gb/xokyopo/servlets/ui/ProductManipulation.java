package gb.xokyopo.servlets.ui;


import gb.xokyopo.servlets.service.entity.Product;
import gb.xokyopo.servlets.service.ProductsService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ProductManipulation implements Serializable {
    private Product product;
    private ProductsService productsService;

    @Inject
    public ProductManipulation(ProductsService productsService) {
        this.productsService = productsService;
    }

    public String editingProduct(Product product) {
        this.product = product;
        return "/product.xhtml";
    }

    public String addProduct() {
        this.product = new Product();
        return "/product.xhtml";
    }

    public String saveChanges() {
        if (this.product.getId() > 0) {
            this.productsService.updateProduct(product);
        } else {
            this.productsService.addProduct(product);
        }
        return "/catalog.xhtml?faces-redirect=true";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String cancel() {
        return "/catalog.xhtml?faces-redirect=true";
    }
}
