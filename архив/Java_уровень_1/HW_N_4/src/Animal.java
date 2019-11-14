/*
+5. Создать классы Собака и Кот с наследованием от класса Животное;
+6. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков);
7. У каждого животного есть ограничения на действия (бег: кот – 200 м., собака – 500 м.; прыжок: кот – 2 м., собака – 0.5 м.; плавание: кот не умеет плавать, собака – 10 м.);
+8. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true);
9. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой – 600 м.
 */
//прототип

public class Animal {
    private String name;
    private double maxRunLength;
    private double maxJumpHeight;
    private double maxSwimLength;

    public Animal(String _name, double _maxRunLength, double _maxJumpHeight, double _maxSwimLength){
        this.name = _name;
        this.maxRunLength = _maxRunLength;
        this.maxJumpHeight = _maxJumpHeight;
        this.maxSwimLength = _maxSwimLength;
    }

    public void set_name(String _name){
        this.name = _name;
    }

    public void set_naxRunLength(double _maxRunLength){
        this.maxRunLength = _maxRunLength;
    }

    public void set_naxJumpHeight(double _maxJumpHeight){
        this.maxJumpHeight = _maxJumpHeight;
    }

    public void set_naxSwimLength(double _maxSwimLength){
        this.maxSwimLength = _maxSwimLength;
    }

    public String get_name(){
        return this.name;
    }

    public double get_maxRunLength(){
        return this.maxRunLength;
    }

    public double get_maxJumpLength(){
        return this.maxJumpHeight;
    }

    public double get_maxSwimLength(){
        return this.maxSwimLength;
    }

    public boolean run(double _length){
        boolean result = (this.maxRunLength >= _length)? true: false;
        if(result){
            System.out.println("run: " + result);
        }else{
            System.out.println("Ваш " + this.name + " не умеет бегать на дистанцию " + _length);
        }
        return result;
    }

    public boolean swim(double _length){
        boolean result = (this.maxSwimLength >= _length)? true: false;
        if(result){
            System.out.println("swim: " + result);
        }else{
            System.out.println("Ваш " + this.name + " не умеет плавать на дистанцию " + _length);
        }
        return result;
    }

    public boolean jump(double _height){
        boolean result = (this.maxJumpHeight >= _height)? true: false;
        if(result){
            System.out.println("swim: " + result);
        }else{
            System.out.println("Ваш " + this.name + " не умеет прыгать на высоту " + _height);
        }
        return result;
    }
}
