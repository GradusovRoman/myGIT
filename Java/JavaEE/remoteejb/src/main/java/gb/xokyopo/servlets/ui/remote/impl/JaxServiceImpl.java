package gb.xokyopo.servlets.ui.remote.impl;

import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import java.util.List;

public interface JaxServiceImpl {
    void insertProduct(ProductRep productRep);
    void removeProduct(ProductRep productRep);
    ProductRep getProductById(int productId);
    ProductRep getProductByName(String name);
    List<ProductRep> getAllProducts();
    List<ProductRep>  getProductsByCategoryId(int categoryId);
    void insertCategory(CategoryRep categoryRep);
}
