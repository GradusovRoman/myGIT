package gb.xokyopo.servlets.ui.remote.ejbremote;

import gb.xokyopo.servlets.service.impl.ProductServiceImpl;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.remote.impl.EjbRemoteImpl;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(EjbRemoteImpl.class)
public class EjbRemote implements EjbRemoteImpl {
    @EJB(beanName = "ProductsService")
    private ProductServiceImpl productService;
    @EJB(beanName = "CategoryService")
    private ServiceImpl<CategoryRep> categoryService;

    @Override
    public void removeCategory(CategoryRep categoryRep) {
        this.categoryService.delete(categoryRep);
    }

    @Override
    public List<CategoryRep> getAllCategory() {
        return this.categoryService.getAll();
    }

    @Override
    public CategoryRep getCategoryById(int categoryId) {
        return this.categoryService.getById(categoryId);
    }

    @Override
    public void insertProduct(ProductRep productRep) {
        this.productService.add(productRep);
    }

    @Override
    public void removeProduct(ProductRep productRep) {
        this.productService.delete(productRep);
    }

    @Override
    public ProductRep getProductById(int productId) {
        return this.productService.getById(productId);
    }

    @Override
    public ProductRep getProductByName(String name) {
        return this.productService.getByName(name);
    }

    @Override
    public List<ProductRep> getAllProducts() {
        return this.productService.getAll();
    }

    @Override
    public List<ProductRep> getProductsByCategoryId(int categoryId) {
        return this.productService.getByCategoryId(categoryId);
    }

    @Override
    public void insertCategory(CategoryRep categoryRep) {
        this.categoryService.add(categoryRep);
    }

    @Override
    public void updateCategory(CategoryRep categoryRep) {
        this.categoryService.update(categoryRep);
    }

    @Override
    public void updateProduct(ProductRep productRep) {
        this.updateProduct(productRep);
    }
}
