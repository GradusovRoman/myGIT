package gb.xokyopo.servlets.ui.local;

import gb.xokyopo.servlets.service.impl.ProductServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.local.impl.ManipulationImpl;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductManipulation implements Serializable, ManipulationImpl<ProductRep> {
    private ProductRep productRep;
    @EJB(beanName = "ProductsService")
    private ProductServiceImpl productsService;

    @Override
    public String cancel() {
        return "/catalog.xhtml?faces-redirect=true";
    }

    @Override
    public String update(ProductRep element) {
        this.productRep = element;
        return "/product.xhtml?faces-redirect=true";
    }

    @Override
    public String add() {
        this.productRep = new ProductRep();
        this.productRep.setCategoryRep(new CategoryRep());
        return "/product.xhtml?faces-redirect=true";
    }

    @Override
    public String delete(ProductRep element) {
        this.productsService.delete(element);
        return "/catalog.xhtml?faces-redirect=true";
    }

    @Override
    public String save() {
        if (!this.productRep.getName().equals("") && this.productRep.getPrice() >= 0) {
            if (this.productRep.getId() > 0) {
                this.productsService.update(productRep);
            } else {
                this.productsService.add(productRep);
            }
            return "/catalog.xhtml?faces-redirect=true";
        }
        return "/product.xhtml?faces-redirect=true";
    }

    @Override
    public ProductRep getElement() {
        return this.productRep;
    }

    @Override
    public void setElement(ProductRep element) {
        this.productRep = element;
    }

    @Override
    public List<ProductRep> getAll() {
        return this.productsService.getAll();
    }
}
