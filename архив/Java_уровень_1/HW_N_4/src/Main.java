

/*
+1. Создать класс «Сотрудник» с полями: ФИО, должность, email, телефон, зарплата, возраст;
+2. Конструктор класса должен заполнять эти поля при создании объекта;
+3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;

+4. Создать массив из 5 сотрудников:
Пример:
Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
persArray[0] = new Person("Ivanov Ivan", "Engineer", " ivivan@mailbox.com ", "892312312", 30000,
30); // потом для каждой ячейки массива задаем объект
persArray[1] = new Person(...);
...
persArray[4] = new Person(...);
С помощью цикла вывести информацию только о сотрудниках старше 40 лет;

+5. Создать классы Собака и Кот с наследованием от класса Животное;
+6. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков);
+7. У каждого животного есть ограничения на действия (бег: кот – 200 м., собака – 500 м.; прыжок: кот – 2 м., собака – 0.5 м.; плавание: кот не умеет плавать, собака – 10 м.);
+8. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true);
+9. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой – 600 м.
*/

public class Main {
    private static worker[] persArr;


    public static void main(String[] args) {
        goWorker();

        goAnimals();

    }

    public static void goWorker(){
        int ageMoreThen = 40;
        persArr = new worker[5];
        persArr[0] = new worker("Ivanov Ivan", "Engineer", "ivivan@ilbox.com", "89231231215", 300000, 30);
        persArr[1] = new worker("Paran Ivan", "Space Comander", "palivan@list.com", "89238531215", 600000, 43);
        persArr[2] = new worker("Svarnik Maxim", "Space Engineer", "svaxim@yandex.com", "89275231215", 100000, 33);
        persArr[3] = new worker("Kurasaki Soma", "Space Cook", "kusoma@gog.net", "892312921755", 80000, 30);
        persArr[4] = new worker("Mendeleev Roman", "Biologist", "romanmen@mail.ru", "89825401215", 150000, 45);

        System.out.println("сотрудники чей возраст болше " + ageMoreThen + " лет.");

        for (worker i : persArr){
            if(i.getAge() > ageMoreThen) {
                i.getWorker();
                System.out.println();
            }
        }
    }

    public static void goAnimals(){
        Cat Cat1 = new Cat("Роджер");
        Cat Cat2 = new Cat("Блэк");
        Dog Dog1 = new Dog("Кусарегама");
        Dog Dog2 = new Dog("Тюфяк");

        //9. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой – 600 м.
        // я так понимаю нужно было что бы просто была возможность поменять стандартные значение.

        Cat1.set_naxRunLength(1000);
        Dog2.set_naxSwimLength(5000);

        //тут просто проверка встроенных методов
        System.out.println("Играемся с животными");
        Cat1.jump(10);
        Cat2.swim(100);
        Dog1.run(50);
        Dog2.jump(150);
    }
}
