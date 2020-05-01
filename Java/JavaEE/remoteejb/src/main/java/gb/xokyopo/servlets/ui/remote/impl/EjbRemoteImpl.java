package gb.xokyopo.servlets.ui.remote.impl;

import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import java.util.List;

public interface EjbRemoteImpl extends JaxServiceImpl {
    void removeCategory(CategoryRep categoryRep);
    List<CategoryRep> getAllCategory();
    CategoryRep getCategoryById(int categoryId);
    void updateCategory(CategoryRep categoryRep);
    void updateProduct(ProductRep productRep);
}
