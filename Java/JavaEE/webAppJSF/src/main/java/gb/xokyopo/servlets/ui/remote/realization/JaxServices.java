package gb.xokyopo.servlets.ui.remote.realization;

import gb.xokyopo.servlets.service.impl.ProductServiceImpl;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.remote.impl.JaxServiceImpl;

import javax.ejb.EJB;
import java.util.List;

public class JaxServices implements JaxServiceImpl {
    @EJB(beanName = "ProductsService")
    private ProductServiceImpl productsService;
    @EJB(beanName = "CategoryService")
    private ServiceImpl<CategoryRep> categoryService;

    @Override
    public void insertProduct(ProductRep productRep) {
        this.productsService.add(productRep);
    }

    @Override
    public void removeProduct(ProductRep productRep) {
        this.productsService.delete(productRep);
    }

    @Override
    public ProductRep getProductById(int productId) {
        return this.productsService.getById(productId);
    }

    @Override
    public ProductRep getProductByName(String name) {
        return this.productsService.getByName(name);
    }

    @Override
    public List<ProductRep> getAllProducts() {
        return this.productsService.getAll();
    }

    @Override
    public List<ProductRep>  getProductsByCategoryId(int categoryId) {
        return this.productsService.getByCategoryId(categoryId);
    }

    @Override
    public void insertCategory(CategoryRep categoryRep) {
        this.categoryService.add(categoryRep);
    }
}
