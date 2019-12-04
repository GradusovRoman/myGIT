package Xokyopo.HW_6.ConsoleManager;

import Xokyopo.HW_6.InternetConnection.InputOtput;

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

    public void readAllways(InputOtput _inputOtput, String _label) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    _inputOtput.output(_label + read());
                }
            }
        });
        //thread.setDaemon(true);
        thread.start();
    }
}
