package gb.xokyopo.servlets.ui.remote.impl;

import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import java.util.List;

/*
+ a. Добавлять товар в БД;
+ b. Удалять товар из БД;
+ d. Получить товар по Id;
+ f. Получить все товары;

+ e. Получить товар по имени; (поиска по имени нет у меня еще)

+ g. Получить товары по Id категории.
+ c. Добавлять категорию товара;
 */
public interface JaxServiceImpl {
    void insertProduct(ProductRep productRep);
    void removeProduct(ProductRep productRep);
    ProductRep getProductById(int productId);
    ProductRep getProductByName(String name);
    List<ProductRep> getAllProducts();
    List<ProductRep>  getProductsByCategoryId(int categoryId);
    void insertCategory(CategoryRep categoryRep);
}
