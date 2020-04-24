package gb.xokyopo.servlets.ui;


import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.service.CategoryService;
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
    private int categoryId;
    private final ProductsService productsService;
    private final CategoryService categoryService;

    @Inject
    public ProductManipulation(ProductsService productsService, CategoryService categoryService) {
        this.productsService = productsService;
        this.categoryService = categoryService;
    }

    public String editingProduct(ProductRep productRep) {
        this.productRep = productRep;
        return "/product.xhtml";
    }

    public String addProduct() {
        this.productRep = new ProductRep();
        return "/product.xhtml";
    }

    public String deleteProduct(ProductRep productRep) {
        this.productsService.deleteProduct(productRep);
        return "/catalog.xhtml";
    }

    public String saveChanges() {
        if (!this.productRep.getName().equals("") && this.productRep.getPrice() <=0) {
            if (this.productRep.getId() > 0) {
                this.productRep.setCategoryRep(this.categoryService.getById(this.categoryId));
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
