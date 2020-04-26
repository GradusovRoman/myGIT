package gb.xokyopo.servlets.ui;

import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.service.ProductsService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ProductManipulation implements Serializable {
    private ProductRep productRep;
    private final ProductsService productsService;

    @Inject
    public ProductManipulation(ProductsService productsService) {
        this.productsService = productsService;
    }

    public String editingProduct(ProductRep productRep) {
        this.productRep = productRep;
        return "/product.xhtml";
    }

    public String addProduct() {
        this.productRep = new ProductRep();
        this.productRep.setCategoryRep(new CategoryRep());
        return "/product.xhtml";
    }

    public String deleteProduct(ProductRep productRep) {
        this.productsService.deleteProduct(productRep);
        return "/catalog.xhtml";
    }

    public String saveChanges() {
        if (!this.productRep.getName().equals("") && this.productRep.getPrice() >= 0) {
            if (this.productRep.getId() > 0) {
                this.productsService.updateProduct(productRep);
            } else {
                this.productsService.addProduct(productRep);
            }
            return "/catalog.xhtml?faces-redirect=true";
        }
        return "/product.xhtml";
    }

    public ProductRep getProductRep() {
        return productRep;
    }

    public void setProductRep(ProductRep productRep) {
        this.productRep = productRep;
    }

    public String cancel() {
        return "/catalog.xhtml?faces-redirect=true";
    }


}
