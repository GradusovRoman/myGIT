/*
1. Класс кота из прошлого ДЗ расширить функционалом потребления пищи. У каждого кота есть аппетит, т.е. количество еды, которое он съедает за один раз;
2. Кот должен есть из миски. Создайте такую сущность, которая будет обладать объёмом и едой в ней, а также методами наполнения и получения информации о количестве еды;
3. Метод из первого пункта ДЗ должен взаимодействовать с миской, т.е., конкретный кот ест из конкретной миски, уменьшая объём еды в ней;
4. Предусмотрите проверку, при которой в миске не может получиться отрицательного количества еды (например, в миске 10 единиц еды, а кот пытается съесть 15);
5. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось поесть (хватило еды), сытость = true;
Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы);
6. Создать массив котов и одну тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль;
7. Когда еда в тарелке кончается, нужно оповещать об этом и наполнять её едой.
 */

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int cat_count;
    public static int min_cat_count = 3; // от 1 до cats_name - 1
    public static Random random = new Random();
    public static String[] cats_name = {"Барсик","Коржик","Шутник","Пират","Клоун","Джек","Роджер","Пушок","Петруша","Джокер"};
    public static Cat[] cats_arr;
    public static Bowl bowl;
    public static Scanner scanner;
    public static String exit_text= "Yes";

    public static void main(String[] args) {
        bowl = new Bowl();
        //сначала создадим  кучу котов
        get_new_cat();
        //вот наши коты
        cat_info();

        System.out.println("Игра начинается");
        start_game();

        System.out.println("Игра закончена");
        cat_info();
	// write your code here
    }

    public static void get_new_cat(){
        //проверяем правильно ли задано минимальное количество котов
        if (cats_name.length > min_cat_count && min_cat_count > 0) {
            cat_count = min_cat_count + random.nextInt(cats_name.length - min_cat_count);
            cats_arr = new Cat[cat_count];
            for (int i = 0; i < cat_count; i++){
                cats_arr[i] = new Cat(cats_name[i]);
            }
        }else{
            System.err.println("Ошибка в определении min_cat_count");
        }
    }

    public static void cat_info(){
        for (Cat cat:  cats_arr){
            cat.info();
            System.out.println("");
        }
    }

    public static void start_game(){
        boolean exit = false;
        do{
            //кормим котов пока есть корм
            for (Cat cat: cats_arr){
                //можно и так, но 2 вариант правильней, классы не должны зависить друг от друга
                //cat.eating(bowl);
                cat.eating(bowl.get_eat(cat.get_hunger()));
            }
            //миску можно заполнить только в конце хода
            bowl.set_eat(set_eat());
            exit = is_ens();
        }while(!exit);
    }

    //просим пользователя указат ьсколько корма он хочет положить.
    public static int set_eat(){
        scanner = new Scanner(System.in);
        String msg ="Введите положительное значение что бы наполнить миску: ";
        int eat = -1;
        do{
            System.out.print(msg);
            eat = scanner.nextInt();
            System.out.println("");
        }while(eat <= 0);
        return eat;
    }

    public static boolean is_ens(){
        scanner = new Scanner(System.in);
        String msg ="Если хотите выйти наберите Yes: ";
        String exit = "No";
        System.out.print(msg);
        exit = scanner.nextLine();
        System.out.println("");
        return exit_text.equals(exit);
    }
    //конец
}
