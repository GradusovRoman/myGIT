package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.service.represantations.ProductRep;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Named
@ApplicationScoped
public class ProductsService implements Serializable {
    private Random random = new Random();
    private List<ProductRep> productRepList;

    public ProductsService() {
        this.productRepList = this.getProducts(10);
    }

    private String getRandomName() {
        String[] names = {"перчатки", "варежки", "штаны", "кросовки", "носки", "кепки", "шорты", "футболки", "туфли", "тапки"};
        String[] colors = {"зеленые", "красные", "розовые", "синие", "черные", "коричнивые"};
        return colors[random.nextInt(colors.length - 1)] + " " + names[this.random.nextInt(names.length - 1)];
    }

    public List<ProductRep> getProducts(int counts) {
        List<ProductRep> productRepList = new ArrayList<>();
        for (int i = 0; i < counts; i++) {
            productRepList.add(new ProductRep(i + 1, this.getRandomName(), this.random.nextInt(counts * 100)));
        }

        return productRepList;
    }

    public List<ProductRep> getAll() {
        return this.productRepList;
    }

    public void addProduct(ProductRep productRep) {
        int[] count = new int[]{0};
        this.productRepList.forEach(eproduct->{
            if (eproduct.getId() > count[0]) {
                count[0] = eproduct.getId();
            }
        });
        productRep.setId(count[0] + 1);
        this.productRepList.add(productRep);
    }

    public void updateProduct(ProductRep productRep) {
        this.productRepList.forEach(eproduct->{
            if(eproduct.getId() == productRep.getId()) {
                eproduct.setName(productRep.getName());
                eproduct.setPrice(productRep.getPrice());
            }
        });
    }

    public void deleteProduct(ProductRep productRep) {
        for(ProductRep eproduct : this.productRepList) {
            if (eproduct.getId() == productRep.getId()) {
                this.productRepList.remove(eproduct);
                return;
            }
        }
    }
}
