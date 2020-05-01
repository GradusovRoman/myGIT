package gb.xokyopo.remote.service;

import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.remote.impl.EjbRemoteImpl;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Stateless(name = "RemoteService")
public class RemoteService implements EjbRemoteImpl {
    private EjbRemoteImpl ejbRemote;

    public RemoteService() throws IOException, NamingException {
        Properties env = new Properties();
        env.load(RemoteService.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        Context context = new InitialContext(env);
        this.ejbRemote = (EjbRemoteImpl) context.lookup("ejb:/webAppJSF/EjbRemote!gb.xokyopo.servlets.ui.remote.impl.EjbRemoteImpl");
    }

    @Override
    public void removeCategory(CategoryRep categoryRep) {
        this.ejbRemote.removeCategory(categoryRep);
    }

    @Override
    public List<CategoryRep> getAllCategory() {
        return this.ejbRemote.getAllCategory();
    }

    @Override
    public CategoryRep getCategoryById(int categoryId) {
        return this.ejbRemote.getCategoryById(categoryId);
    }

    @Override
    public void insertProduct(ProductRep productRep) {
        this.ejbRemote.insertProduct(productRep);
    }

    @Override
    public void removeProduct(ProductRep productRep) {
        this.ejbRemote.removeProduct(productRep);
    }

    @Override
    public ProductRep getProductById(int productId) {
        return this.ejbRemote.getProductById(productId);
    }

    @Override
    public ProductRep getProductByName(String name) {
        return this.ejbRemote.getProductByName(name);
    }

    @Override
    public List<ProductRep> getAllProducts() {
        return this.ejbRemote.getAllProducts();
    }

    @Override
    public List<ProductRep> getProductsByCategoryId(int categoryId) {
        return this.ejbRemote.getProductsByCategoryId(categoryId);
    }

    @Override
    public void insertCategory(CategoryRep categoryRep) {
        this.ejbRemote.insertCategory(categoryRep);
    }

    @Override
    public void updateCategory(CategoryRep categoryRep) {
        this.ejbRemote.updateCategory(categoryRep);
    }

    @Override
    public void updateProduct(ProductRep productRep) {
        this.ejbRemote.updateProduct(productRep);
    }
}
