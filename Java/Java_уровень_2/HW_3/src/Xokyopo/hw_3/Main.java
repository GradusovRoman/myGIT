package Xokyopo.hw_3;

/*
1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся). Найти и вывести список уникальных слов, из которых состоит массив
(дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. В этот телефонный справочник с помощью метода add() можно
 добавлять записи. С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов, тогда при запросе
 такой фамилии должны выводиться все телефоны.
 Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес), делать
 взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать просто из метода main(), прописывая add() и get().
 */

import Xokyopo.hw_3.PhoneBook.PhoneBook;
import Xokyopo.hw_3.UniqueArrayCounter.UniqueArrayCounter;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        arrayChecker();
        phoneBookCheсker();
    }

    //метод для проверки домашнего задания с массивом (раз тема коллекции буду использовать колекции)
    public static void arrayChecker() {
        int maxNumberOfElement = 10;
        System.out.println("Задание №1: массив");
        UniqueArrayCounter arr = new UniqueArrayCounter(getRandomArray(maxNumberOfElement)); //для работы генерируем массив со случайными именами.

        arr.printingArr(false);
        System.out.println("С количеством повторений");
        arr.printingArr(true);
    }

    //Метод для проверки работоспособности телефонного справочника
    public static void phoneBookCheсker() {
        System.out.println("Задание №2: телефонный справочник");
        int maxMember = 10;                     //Количество которое хотим добавить
        int maxNumbers = 3;                     //максимальное количество номеров для одного пользователя при генерации
        PhoneBook phoneBook = new PhoneBook();

        //добавляем пользователей
        for (int i = 0; i < maxMember; i++) {
            phoneBook.add(getRandomName(), getRandomArrayNumber(maxNumbers));
        }

        //выводим содержимое справочника
        phoneBook.printingAllBook();
        System.out.println("");

        //Ищем в телефонном справочнике
        phoneBook.getAndPrinting(getRandomName());
    }







    //TODO для автозаплнения а то постоянно ругаются на то что ее делаю в отдельном классе называя это хардкодом, вот теперь подписываю.
    //автоматизация для удобства проверки и тестов
    public static int getRandomIntByRange(int _range) {
        //генерирует случайное число
        return new Random().nextInt(_range);
    }

    public static String getRandomName() {
        //генерирует случайное имя
        String[] name = {"Василий", "Федор", "Иван", "Костя", "Евгений", "Жора", "Володя"};
        return name[getRandomIntByRange(name.length)];
    }

    public static String getRandomNumber() {
        //генерирует случайный номер
        String[] numbers = {"+7-904-34-53", "+7-911-34-53", "+7-605-34-53", "+7-345-38-53", "+7-812-34-52", "+7-100-34-53", "+7-999-34-59"};
        return numbers[getRandomIntByRange(numbers.length)];
    }

    public static String[] getRandomArrayNumber(int _maxNumbers) {
        //генерирует случайный список номеров
        String[] numbers = new String[getRandomIntByRange(_maxNumbers)];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = getRandomNumber();
        }
        return numbers;
    }

    public static String[] getRandomArray(int _count) {
        String[] arr = new String[_count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = getRandomName();
        }
        return arr;
    }

}
