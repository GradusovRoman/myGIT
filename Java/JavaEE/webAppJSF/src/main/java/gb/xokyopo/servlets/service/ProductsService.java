package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.dao.ProductRepository;
import gb.xokyopo.servlets.dao.table.Product;
import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Named
@ApplicationScoped
public class ProductsService implements Serializable {
    private Random random = new Random();
    private final List<ProductRep> productRepList;
    private ProductRepository productRepository;
    private ServiceUtils serviceUtils;

    public ProductsService() {
        this.productRepList = new ArrayList<>();
    }

    public List<ProductRep> getAll() {
        if (this.productRepList.size() == 0) this.updateProductRepList();
        return this.productRepList;
    }

    public void addProduct(ProductRep productRep) {
        this.productRepository.create(this.serviceUtils.productRepToProduct(productRep));
        this.updateProductRepList();
    }

    public void updateProduct(ProductRep productRep) {
        this.productRepository.update(this.serviceUtils.productRepToProduct(productRep));
        this.updateProductRepList();
    }

    public void deleteProduct(ProductRep productRep) {
        this.productRepository.delete(productRep.getId());
        this.updateProductRepList();
    }

    private void updateProductRepList() {
        this.productRepList.clear();
        this.productRepository.getAll().forEach(prod->{
            this.productRepList.add(this.serviceUtils.productToProductRep(prod));
        });
    }

    @Inject
    public void setServiceUtils(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    @Inject
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
