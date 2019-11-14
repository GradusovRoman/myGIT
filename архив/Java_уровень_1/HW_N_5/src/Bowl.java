/*
2. Кот должен есть из миски. Создайте такую сущность, которая будет обладать объёмом и едой в ней, а также методами наполнения и получения информации о количестве еды;
3. Метод из первого пункта ДЗ должен взаимодействовать с миской, т.е., конкретный кот ест из конкретной миски, уменьшая объём еды в ней;
4. Предусмотрите проверку, при которой в миске не может получиться отрицательного количества еды (например, в миске 10 единиц еды, а кот пытается съесть 15);
7. Когда еда в тарелке кончается, нужно оповещать об этом и наполнять её едой.
 */

import java.util.Random;

public class Bowl {
    //объем миски
    private int max_value = 500;
    //сколько в миски еды
    private int value;
    // сколько раз мы наполняли миску после ее опустошения
    private int count = 1;
    private int max_count = 3;
    private Random random = new Random();

    public Bowl(){
        this.get_new_max_value();
        this.value = this.max_value;
    }

    private void get_new_max_value(){
        this.max_value +=random.nextInt(this.max_value/2);
    }

    public int get_value(){
        return this.value;
    }

    public int get_max_value(){
        return this.max_value;
    }

    public void set_value(int _value){
        this.value = _value;
    }

    public void set_max_value(int _max_value){
        this.max_value = _max_value;
    }

    public void info(){
        System.out.printf("Объем миски\t%s\nОбъем корма\t%s\n", this.max_value, this.count);
    }

    //дальше логика для кормления
    /*
    возвращает количество еды которую съел кот
    и уменьшает на это же значение объем еды в миске
     */

    public int get_eat(int _value){
        int resolve = 0;
        if (_value <= this.value){
            resolve = _value;
            this.value -= _value;
        }else{
            resolve = this.value;
            this.value = 0;
            System.out.println("В миске закончилась еда");
        }
        return resolve;
    }

    //теперь логика для наполнения
    /*
    Если количесто вводимой еды меньше или равно обьему миски то все норм
    Если пользователь попытается засыпать больше то он ее рассыпит и по этому не наполнит миску полностью (ну так же интересно.)
     */

    public void set_eat(int _value){
        this.count++;
        new_bowl();
        if (_value <= this.max_value - this.value){
            this.value +=_value;
            System.out.printf("Вы пополнили миску на %d единиц корма и теперь у вас %d\n", _value, this.value);
        }else{
            String[] msg = {"лежащее дерево","мышь","едущий велосипед","развязанные шнурки"};
            this.value += random.nextInt(this.max_value - this.value);
            System.out.printf("Вы сильно спешили ил магазина и споткнулись о %s тем самым рассыпав корм, и теперь в вашей миске всего %s единиц корма из %s\n", msg[random.nextInt(msg.length)], this.value, this.max_value);
        }
    }

    private void new_bowl(){
        boolean resolve = false;
        if (this.count == this.max_count){
            this.count = 1;
            this.get_new_max_value();
            System.out.println("Вы смогли купить новую миску теперь она вмещает корма на " + this.max_value + " единиц");
        }
    }

//конец клвсса
}
