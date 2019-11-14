/*
+1. Создать класс «Сотрудник» с полями: ФИО, должность, email, телефон, зарплата, возраст;
+2. Конструктор класса должен заполнять эти поля при создании объекта;
+3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
*/

public class worker {
    private String name;
    private String post;
    private String email;
    private String foneNumber;
    private int pay;
    private int age;

    public worker(String _name, String _post, String _email, String _foneNumber, int _pay, int _age){
        this.name = _name;
        this.post = _post;
        this.email = _email;
        this.foneNumber = _foneNumber;
        this.pay = _pay;
        this.age = _age;
    }

    public void getWorker(){
        System.out.println("name \t\t= " + this.name + "\npost \t\t= " + this.post + "\nemail \t\t= " +
                this.email + "\nfoneNumber \t= " + this.foneNumber + "\npay \t\t= " + this.pay + "\nage \t\t= " + this.age);
    }

    public String getName(){
        return this.name;
    }

    public String getPost(){
        return this.post;
    }

    public String getEmail(){
        return this.email;
    }

    public String getFoneNumber(){
        return this.foneNumber;
    }

    public int getPay(){
        return this.pay;
    }

    public int getAge(){
        return this.age;
    }

}
