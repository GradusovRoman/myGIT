package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.table.Product;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


@Named
@ApplicationScoped
public class ProductsService implements Serializable {
    private ServiceUtils serviceUtils;

    public List<ProductRep> getAll() {
        return this.serviceUtils.getProductRepository().getAll()
                .stream()
                .map(this.serviceUtils::productToProductRep)
                .collect(Collectors.toList());
    }

    public void addProduct(ProductRep productRep) {
        this.serviceUtils.getProductRepository().create(
                this.serviceUtils.productRepToProduct(productRep, new Product())
        );
    }

    public void updateProduct(ProductRep productRep) {
        this.serviceUtils.getProductRepository().update(
                this.serviceUtils.productRepToProduct(productRep, this.getProductById(productRep.getId()))
        );
    }

    public void deleteProduct(ProductRep productRep) {
        this.serviceUtils.getProductRepository().delete(productRep.getId());
    }

    public Product getProductById(int id) {
        return this.serviceUtils.getProductRepository().findById(id);
    }

    @Inject
    public void setServiceUtils(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

}
