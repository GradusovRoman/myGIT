package ru.geekbrains.thirdquarter.hibernate;

import ru.geekbrains.thirdquarter.hibernate.dao.EntitysRepository;
import ru.geekbrains.thirdquarter.hibernate.domain.entitys.Buyer;
import ru.geekbrains.thirdquarter.hibernate.domain.entitys.Product;
import ru.geekbrains.thirdquarter.hibernate.ui.console.EntitysManager;
import ru.geekbrains.thirdquarter.hibernate.ui.console.forms.BuyerForm;
import ru.geekbrains.thirdquarter.hibernate.ui.console.forms.ProductForm;

import java.util.Scanner;

public class ConsoleUI {
    private static final String EXIT_TEXT = "/q";
    private final EntitysManager<Buyer> buyersManager;
    private final EntitysManager<Product> productsManager;

    public ConsoleUI() {
//        EntitysRepository<Buyer> ber = new EntitysRepository<>(Buyer.class);
//        EntitysRepository<Product> per = new EntitysRepository<>(Product.class);

        buyersManager = new EntitysManager<>(new EntitysRepository<>(Buyer.class), new BuyerForm());
        productsManager = new EntitysManager<>(new EntitysRepository<>(Product.class), new ProductForm());

//        for (int i = 0; i < 10; i++) {
//            Product p = new Product();
//            p.setName("product_"
//                    + i).setCost(i);
//            Buyer b = new Buyer();
//            b.setName("buyer_"
//                    + i);
//            ber.add(b);
//            per.add(p);
//        }
//
//        Buyer b = ber.getById(1);
//
//        b.setProducts(per.getAll());
//
//        ber.update(b);
    }

    public void run() {
        String input;
        while (true) {
            System.out.print("\nПриложение для работы с сущьностями(для выхода наберите "
                    + EXIT_TEXT
                    + ")\n"
                    + "Выберите тип сущьностей с которыми хотите работать:\n"
                    + "Buyer\n"
                    + "Product\n"
                    + "<");
            input = getUserInput();

            if (input.equals(EXIT_TEXT)) {
                break;
            } else if (input.equals("Product")) {
                productsManager.open();
            } else if (input.equals("Buyer")) {
                buyersManager.open();
            } else {
                System.out.println("Некоректный ввод");
            }
        }
        System.out.println("see you later");
    }

    private String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
