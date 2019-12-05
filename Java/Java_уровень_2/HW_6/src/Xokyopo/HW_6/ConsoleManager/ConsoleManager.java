package Xokyopo.HW_6.ConsoleManager;

import java.util.Scanner;

public class ConsoleManager {
    //реализует функции работы в консоли
    //получить ввод с консоли
    //расписать сообщение в консоль

    public void write(String... _msg) {
        for (int i = 0; i < _msg.length; i ++) {
            System.out.println(_msg[i]);
        }
    }

    public String read() {
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.nextLine();
        return msg;
    }

}
