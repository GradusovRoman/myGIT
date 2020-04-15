package gb.xokyopo.servlets.service;

import gb.xokyopo.servlets.service.entity.Product;

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
    private List<Product> productList;

    public ProductsService() {
        this.productList = this.getProducts(10);
    }

    private String getRandomName() {
        String[] names = {"перчатки", "варежки", "штаны", "кросовки", "носки", "кепки", "шорты", "футболки", "туфли", "тапки"};
        String[] colors = {"зеленые", "красные", "розовые", "синие", "черные", "коричнивые"};
        return colors[random.nextInt(colors.length - 1)] + " " + names[this.random.nextInt(names.length - 1)];
    }

    public List<Product> getProducts(int counts) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < counts; i++) {
            productList.add(new Product(i + 1, this.getRandomName(), this.random.nextInt(counts * 100)));
        }

        return productList;
    }

    public List<Product> getAll() {
        return this.productList;
    }

    public void addProduct(Product product) {
        int[] count = new int[]{0};
        this.productList.forEach(eproduct->{
            if (eproduct.getId() > count[0]) {
                count[0] = eproduct.getId();
            }
        });
        product.setId(count[0] + 1);
        this.productList.add(product);
    }

    public void updateProduct(Product product) {
        this.productList.forEach(eproduct->{
            if(eproduct.getId() == product.getId()) {
                eproduct.setName(product.getName());
                eproduct.setPrice(product.getPrice());
            }
        });
    }

    public void deleteProduct(Product product) {
        for(Product eproduct : this.productList) {
            if (eproduct.getId() == product.getId()) {
                this.productList.remove(eproduct);
                return;
            }
        }
    }
}
