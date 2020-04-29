package gb.xokyopo.servlets.ui.remote.realization;

import gb.xokyopo.servlets.service.CategoryService;
import gb.xokyopo.servlets.service.ProductsService;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.remote.impl.JaxServiceImpl;

import javax.inject.Inject;
import java.util.List;

public class JaxServices implements JaxServiceImpl {
    private ProductsService productsService;
    private CategoryService categoryService;

    @Inject
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Inject
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

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
