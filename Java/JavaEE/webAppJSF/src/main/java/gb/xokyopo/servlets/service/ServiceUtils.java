package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.CategoriesRepository;
import gb.xokyopo.servlets.dao.OrderRepository;
import gb.xokyopo.servlets.dao.ProductRepository;
import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.dao.table.Product;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ServiceUtils {
    private CategoriesRepository categoriesRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

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

    public CategoriesRepository getCategoriesRepository() {
        return categoriesRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    @Inject
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Inject
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Inject
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
