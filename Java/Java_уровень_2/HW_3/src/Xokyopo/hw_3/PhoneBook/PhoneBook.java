package Xokyopo.hw_3.PhoneBook;

import java.util.*;

public class PhoneBook {
    private TreeMap<String, HashSet<String>> phoneBook = new TreeMap<>();

    //метод гет должет что то возвращать а не печть, так что для распечатки будет другой метод!!!!
    public String get(String _name) {
        return this.phoneBook.get(_name).toString();
    }

    public void add(String _name, String... _foneNumber) {
        if (this.phoneBook.get(_name) == null) {
            this.phoneBook.put(_name, new HashSet<>(Arrays.asList(_foneNumber)));
        } else {
            this.phoneBook.get(_name).addAll(new HashSet<>(Arrays.asList(_foneNumber)));
        }
    }

    public void printingAllBook() {
        System.out.println("Телефонный справочник");
        for (Map.Entry elementOfBook:this.phoneBook.entrySet()) {
            System.out.println("Имя: " + elementOfBook.getKey() + "\t т.номра: " + elementOfBook.getValue());
        }
    }

    public String getAndPrinting(String _name) {
        System.out.println("результат поиска пользователя: " + _name);
        System.out.println((this.phoneBook.get(_name) != null) ? "Имя: " + _name + "\nТелефонные номера: " + this.phoneBook.get(_name) : "пользователь не найден");
        return this.phoneBook.get(_name).toString();
    }
}
