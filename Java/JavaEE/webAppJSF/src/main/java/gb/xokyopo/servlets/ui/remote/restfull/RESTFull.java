package gb.xokyopo.servlets.ui.remote.restfull;

import gb.xokyopo.servlets.service.represantations.CategoryRep;
import gb.xokyopo.servlets.service.represantations.ProductRep;
import gb.xokyopo.servlets.ui.remote.realization.JaxServices;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/shop")
public class RESTFull extends JaxServices {

    @PUT
    @Override
    @Path("/insertProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertProduct(ProductRep productRep) {
        super.insertProduct(productRep);
    }

    @DELETE
    @Override
    @Path("/removeProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeProduct(ProductRep productRep) {
        super.removeProduct(productRep);
    }

    @POST
    @Override
    @Path("/getProductById/{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRep getProductById(@PathParam("id") int productId) {
        return super.getProductById(productId);
    }

    @POST
    @Override
    @Path("/{name}/name")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductRep getProductByName(@PathParam("name") String name) {
        return super.getProductByName(name);
    }

    @GET
    @Override
    @Path("/getAllProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductRep> getAllProducts() {
        return super.getAllProducts();
    }

    @POST
    @Override
    @Path("/getProductsByCategoryId/{id}/id")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductRep> getProductsByCategoryId(@PathParam("id") int categoryId) {
        return super.getProductsByCategoryId(categoryId);
    }

    @PUT
    @Override
    @Path("/insertCategory")
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertCategory(CategoryRep categoryRep) {
        super.insertCategory(categoryRep);
    }
}
