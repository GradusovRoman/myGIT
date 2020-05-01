package gb.xokyopo.servlets.service.impl;

import gb.xokyopo.servlets.service.represantations.ProductRep;

import java.util.List;

public interface ProductServiceImpl extends ServiceImpl<ProductRep> {
    List<ProductRep> getByCategoryId(int categoryId);
    public ProductRep getByName(String name);
}
