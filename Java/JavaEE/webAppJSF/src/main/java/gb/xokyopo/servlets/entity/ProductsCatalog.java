package gb.xokyopo.servlets.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsCatalog {
    private static ProductsCatalog productsCatalog;
    private Random random = new Random();

    public ProductsCatalog() {

    }

    private String getRandomName() {
        String[] names = {"перчатки", "варежки", "штаны", "кросовки", "носки", "кепки", "шорты", "футболки", "туфли", "тапки"};
        String[] colors = {"зеленые", "красные", "розовые", "синие", "черные", "коричнивые"};
        return colors[random.nextInt(colors.length - 1)] + " " + names[this.random.nextInt(names.length - 1)];
    }

    public static ProductsCatalog getProductsCatalog() {
        if (ProductsCatalog.productsCatalog == null) ProductsCatalog.productsCatalog = new ProductsCatalog();
        return ProductsCatalog.productsCatalog;
    }

    public List<Product> getProducts(int counts) {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < counts; i++) {
            productList.add(new Product(i + 1, this.getRandomName(), this.random.nextInt(counts * 100)));
        }

        return productList;
    }
}
