package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Category;
import gb.xokyopo.servlets.dao.table.Product;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ServiceUtils {

    public Product productRepToProduct(ProductRep productRep) {
        Product outer = new Product();
        outer.setId(productRep.getId());
        outer.setName(productRep.getName());
        outer.setPrice(productRep.getPrice());
        return outer;
    }

    public ProductRep productToProductRep(Product product) {
        ProductRep outer = new ProductRep();
        outer.setId(product.getId());
        outer.setName(product.getName());
        outer.setPrice(product.getPrice());
        return outer;
    }

    public Category categoryRepToCategory(CategoryRep categoryRep) {
        Category outer = new Category();
        outer.setId(categoryRep.getId());
        outer.setName(categoryRep.getName());
        outer.setDescription(categoryRep.getDescription());
        return outer;
    }

    public CategoryRep categoryToCategoryRep(Category category) {
        CategoryRep outer = new CategoryRep();
        outer.setId(category.getId());
        outer.setName(category.getName());
        outer.setDescription(category.getDescription());
        return outer;
    }
}
