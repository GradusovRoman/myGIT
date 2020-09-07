package ru.geekbrains.thirdquarter.hibernate.ui.console;

import java.util.Arrays;
import java.util.Scanner;

import ru.geekbrains.thirdquarter.hibernate.dao.EntitysRepository;
import ru.geekbrains.thirdquarter.hibernate.ui.console.forms.EntityForm;

public class EntitysManager<T> {
    private static final String START_TEXT = "Выберите действие:";
    private final EntitysRepository<T> repository;
    private final EntityForm<T> form;

    private enum Command {
        SHOWALL, DEL, EDIT, EXIT
    }

    public EntitysManager(EntitysRepository<T> repository, EntityForm<T> form) {
        this.repository = repository;
        this.form = form;
    }

    public void open() {
        while (true) {
            System.out.println(START_TEXT);
            Arrays.stream(Command.values()).forEach(System.out::println);
            String input = this.getInput();
            if (input.equals(Command.SHOWALL.toString())) {
                this.showAll();
            } else if (input.equals(Command.DEL.toString())) {
                this.del();
            } else if (input.equals(Command.EDIT.toString())) {
                this.edit();
            } else if (input.equals(Command.EXIT.toString())) {
                break;
            } else {
                System.out.println("Неизвестная команда ["
                        + input
                        + "] повторите все заново");
            }
        }

    }

    private void showAll() {
        repository.getAll().forEach(System.out::println);
    }

    private T get() {
        while (true) {
            System.out.printf("введите ID элемента или %s для выхода%n", Command.EXIT);
            String input = this.getInput();
            if (input.equals(Command.EXIT.toString())) {
                break;
            } else {
                try {
                    long id = Long.parseLong(input);
                    return repository.getById(id);
                } catch (NumberFormatException e) {
                    System.out.println("ID введен некоректно");
                } catch (IllegalArgumentException e) {
                    System.out.println("Объекта с таким ID несуществует");
                }
            }
        }
        throw new IllegalArgumentException(Command.EXIT.toString());
    }

    private void del() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("Удаление объекта");
            try {
                repository.delete(this.get());
            } catch (IllegalArgumentException e) {
                repeat = false;
            }
        }
    }

    private void edit() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("Редактирование объекта");
            try {
                T entity = this.get();
                if (form.edit(entity)) {
                    repository.update(entity);
                }
            } catch (IllegalArgumentException e) {
                repeat = false;
            }
        }

    }

    private String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n<");
        return scanner.nextLine();
    }

}
