package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.CategoriesRepository;
import gb.xokyopo.servlets.dao.OrderRepository;
import gb.xokyopo.servlets.dao.ProductRepository;
import gb.xokyopo.servlets.dao.impl.Repository;
import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.dao.table.Orders;
import gb.xokyopo.servlets.dao.table.Product;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Stateless(name = "ServiceUtils")
public class ServiceUtils {
    @EJB(beanName = "CategoriesRepository")
    private Repository<Category> categoriesRepository;
    @EJB(beanName = "ProductRepository")
    private Repository<Product> productRepository;
    @EJB(beanName = "OrderRepository")
    private Repository<Orders> orderRepository;

    public Product productRepToProduct(ProductRep productRep, Product product) {
        product.setId(productRep.getId());
        product.setName(productRep.getName());
        product.setPrice(productRep.getPrice());
        product.setCategory(this.categoriesRepository.findById(productRep.getCategoryRep().getId()));
        return product;
    }

    public ProductRep productToProductRep(Product product) {
        ProductRep outer = new ProductRep();
        outer.setId(product.getId());
        outer.setName(product.getName());
        outer.setPrice(product.getPrice());
        outer.setCategoryRep(this.categoryToCategoryRep(product.getCategory()));
        return outer;
    }

    public Category categoryRepToCategory(CategoryRep categoryRep, Category category) {
        category.setId(categoryRep.getId());
        category.setName(categoryRep.getName());
        category.setDescription(categoryRep.getDescription());
        return category;
    }

    public CategoryRep categoryToCategoryRep(Category category) {
        CategoryRep outer = new CategoryRep();
        outer.setId(category.getId());
        outer.setName(category.getName());
        outer.setDescription(category.getDescription());
        return outer;
    }

    public Repository<Category> getCategoriesRepository() {
        return categoriesRepository;
    }

    public Repository<Product> getProductRepository() {
        return productRepository;
    }

    public Repository<Orders> getOrderRepository() {
        return orderRepository;
    }
}
