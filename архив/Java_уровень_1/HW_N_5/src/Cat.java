/*
1. Класс кота из прошлого ДЗ расширить функционалом потребления пищи. У каждого кота есть аппетит, т.е. количество еды, которое он съедает за один раз;
2. Кот должен есть из миски.
3. Метод из первого пункта ДЗ должен взаимодействовать с миской, т.е., конкретный кот ест из конкретной миски, уменьшая объём еды в ней;
5. Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось поесть (хватило еды), сытость = true;
Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы);
6. Создать массив котов и одну тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль;
 */

//Создам новый класс, для тренировки и для интереса смотрящего (хотя понимаю что тут мы должны были импортировать класс из прошлого ДЗ и наследованием создать новый с дополнительными функциями.)

/*
коты буду иметь разый обьем желудка
У котов будет уровень который будет повышаться после каждой кормешки если он наелся.
 */
import java.util.Random;

public class Cat {
    private String name;
    private int lvl = 0;
    private int maxFullnes = 100;
    private int fullnes = 0;
    private Random random = new Random();

    public Cat(String _name){
        this.name = _name;
        new_maxFullnes();
    }

    private void new_maxFullnes(){
        this.maxFullnes += random.nextInt(this.maxFullnes/2);
    }

    public String get_name(){
        return this.name;
    }

    public int gen_lvl(){
        return this.lvl;
    }

    public int get_maxFullnes(){
        return this.maxFullnes;
    }

    public int get_fullnes(){
        return this.fullnes;
    }

    public void set_name(String _name){
        this.name = _name;
    }

    public void set_lvl(int _lvl){
        this.lvl = _lvl;
    }

    public void set_maxFullnes(int _maxFullnes){
        this.fullnes = _maxFullnes;
    }

    public void set_fullnes(int _fullnes){
        this.fullnes = _fullnes;
    }

    public void info(){
        System.out.printf("Кот \nИмя \t\t = \t %s \nУровень \t = \t %d \nмахСытость \t = \t %d \nСытость \t = \t %d \n", this.name, this.lvl ,this.maxFullnes, this.fullnes);
    }

    //теперь логика кормления вариант 1
    public boolean eating(Bowl _bowl){
        boolean resolve = false;
        //тут кот есть из миски, все что там есть
        this.fullnes += _bowl.get_eat(this.maxFullnes - this.fullnes);
        //определяем получил ли он уровень и выводим оповещение
        if (_bowl.get_value() == 0){
            System.out.printf("Кот %s не смог поесть и ушел голодный\n", this.name);
        }
        resolve = islvlUp();
        return resolve;
    }

    ///теперь логика кормлениявариант 2
    //так же потребуется значение голода
    public int get_hunger(){
        return (this.maxFullnes - this.fullnes);
    }

    public boolean eating(int _value){
        boolean resolve = false;
        //тут кот есть из миски, все что там есть
        this.fullnes += _value;
        if (_value == 0){
            System.out.printf("Кот %s не смог поесть и ушел голодный\n", this.name);
        }
        //определяем получил ли он уровень и выводим оповещение
        resolve = islvlUp();
        return resolve;
    }

    //наш кот поел и получил новый ровень.
    public boolean islvlUp(){
        boolean resolve = false;
        if (this.maxFullnes == this.fullnes){
            this.lvl++;
            this.new_maxFullnes();
            resolve = true;
            System.out.printf("Кот %s наелся и получил %d уровень теперь его желудок вмещает %d еды \n", this.name, this.lvl, this.maxFullnes);
        }
        return resolve;
    }
    //конец
}