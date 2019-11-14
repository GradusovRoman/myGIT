package Xokyopo.hw_1.team.players;

import java.util.Random;

public class NameGenerator {
    private String[] name = {"Иван", "Федор", "Фродо", "Петр", "Кристофер", "Робин", "Андрей", "Василий"};
    private String[] soname = {"Васильевич", "Бэгинс", "Сумкин", "Петрович", "Печкин", "Девяткин"};
    private Random random = new Random();

    public String getRandomName() {
        return (this.name[this.random.nextInt(this.name.length)] + " " + this.soname[this.random.nextInt(this.soname.length)]);
    }
}
