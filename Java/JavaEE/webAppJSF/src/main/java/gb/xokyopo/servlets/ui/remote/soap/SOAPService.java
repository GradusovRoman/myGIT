package gb.xokyopo.servlets.ui.remote.soap;

import gb.xokyopo.servlets.service.CategoryService;
import gb.xokyopo.servlets.service.ProductsService;
import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.remote.realization.JaxServices;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService
public class SOAPService extends JaxServices {

    @Override
    @WebMethod
    public void insertProduct(ProductRep productRep) {
        super.insertProduct(productRep);
    }

    @Override
    @WebMethod
    public void removeProduct(ProductRep productRep) {
        super.removeProduct(productRep);
    }

    @Override
    @WebMethod
    public ProductRep getProductById(int productId) {
        return super.getProductById(productId);
    }

    @Override
    @WebMethod
    public ProductRep getProductByName(String name) {
        return super.getProductByName(name);
    }

    @Override
    @WebMethod
    public List<ProductRep> getAllProducts() {
        return super.getAllProducts();
    }

    @Override
    @WebMethod
    public List<ProductRep> getProductsByCategoryId(int categoryId) {
        return super.getProductsByCategoryId(categoryId);
    }

    @Override
    @WebMethod
    public void insertCategory(CategoryRep categoryRep) {
        super.insertCategory(categoryRep);
    }
}
