package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Product;
import gb.xokyopo.servlets.service.impl.ServiceImpl;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;


@Named
@ApplicationScoped
public class ProductsService implements ServiceImpl<ProductRep> {
    private ServiceUtils serviceUtils;

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

    @Inject
    public void setServiceUtils(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    public List<ProductRep> getByCategoryId(int categoryId) {
        return this.serviceUtils.getProductRepository().getByCategoryID(categoryId).stream()
                .map(this.serviceUtils::productToProductRep)
                .collect(Collectors.toList());
    }

    public ProductRep getByName(String name) {
        return this.serviceUtils.productToProductRep(this.serviceUtils.getProductRepository().getByName(name));
    }

}
