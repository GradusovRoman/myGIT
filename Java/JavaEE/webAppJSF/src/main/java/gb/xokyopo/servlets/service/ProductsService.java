package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Product;
import gb.xokyopo.servlets.service.impl.ProductServiceImpl;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;
import java.util.stream.Collectors;


@Stateless(name = "ProductsService")
public class ProductsService implements ProductServiceImpl {
    @EJB(beanName = "ServiceUtils")
    public ServiceUtils serviceUtils;

    @Override
    public List<ProductRep> getAll() {
        return this.serviceUtils.getProductRepository().getAll().stream()
                .map(this.serviceUtils::productToProductRep)
                .collect(Collectors.toList());
    }

    @Override
    public void add(ProductRep element) {
        this.serviceUtils.getProductRepository().create(
                this.serviceUtils.productRepToProduct(element, new Product())
        );
    }

    @Override
    public void update(ProductRep element) {
        this.serviceUtils.getProductRepository().update(
                this.serviceUtils.productRepToProduct(element, this.serviceUtils.getProductRepository().findById(element.getId()))
        );
    }

    @Override
    public void delete(ProductRep element) {
        this.serviceUtils.getProductRepository().delete(element.getId());
    }

    @Override
    public ProductRep getById(int elementId) {
        return this.serviceUtils.productToProductRep(this.serviceUtils.getProductRepository().findById(elementId));
    }

    @Override
    public List<ProductRep> getByCategoryId(int categoryId) {
        return this.serviceUtils.getCategoriesRepository().findById(categoryId).getProductList().stream()
                .map(this.serviceUtils::productToProductRep)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRep getByName(String name) {
//        return this.serviceUtils.productToProductRep(this.serviceUtils.getProductRepository().getByName(name));
        return null;
    }

}
