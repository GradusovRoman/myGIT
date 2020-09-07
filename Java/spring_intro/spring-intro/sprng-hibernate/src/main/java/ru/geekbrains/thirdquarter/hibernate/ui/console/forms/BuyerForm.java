package ru.geekbrains.thirdquarter.hibernate.ui.console.forms;

import java.util.Arrays;
import java.util.Scanner;

import ru.geekbrains.thirdquarter.hibernate.domain.entitys.Buyer;

public class BuyerForm implements EntityForm<Buyer> {
    private static final String START_TEXT = "Выберите действие:";

    private enum Command {
        SHOWINFO, SHOW_PRODUCTS, EXIT
    }

    @Override
    public boolean edit(Buyer element) {
        while (true) {
            System.out.println(START_TEXT);
            Arrays.stream(Command.values()).forEach(System.out::println);
            String input = getInput();
            if (input.equals(Command.EXIT.toString())) {
                break;
            } else if (input.equals(Command.SHOWINFO.toString())) {
                System.out.println(element);
            } else if (input.equals(Command.SHOW_PRODUCTS.toString())) {
                System.out.println("Продукты покупателя");
                element.getProducts().forEach(System.out::println);
            } else {
                System.out.println("Неверная команда ["
                        + input
                        + "]");
            }
        }
        return false;
    }

    private final String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n<");
        return scanner.nextLine();
    }
}
