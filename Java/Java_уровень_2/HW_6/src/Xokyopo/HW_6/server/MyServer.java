package Xokyopo.HW_6.Server;

import Xokyopo.HW_6.ConsoleManager.ConsoleManager;
import Xokyopo.HW_6.InternetConnection.InputOtput;
import Xokyopo.HW_6.InternetConnection.InternetConnection;
import java.io.IOException;

/*
поднять соединение с клиентом
отправить сообщение клиенту
постоянное ожидаение ввода
постоянный вывод сообщений при ожидании ввода
 */

public class MyServer extends ConsoleManager implements InputOtput {
    private InternetConnection internetConnection  = new InternetConnection(this);
    private final int PORT = 8183;
    private String name = "Сервер";

    public static void main(String[] arg) {
        MyServer myServer = new MyServer();
    }

    public MyServer() {
        try {
            this.internetConnection.startAsServer(this.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //this.readAllways(this, this.name + " :"); //не работает если использовать это с выходом из цикла
        //TODO не выходит после присылания ексит, ждет еще один ввод для завершения.
        while (this.internetConnection.isConnection()) {
            this.output(this.name + " :" + this.read());
        }
    }

    @Override
    public void input(String _string) {
        this.write(_string);
    }

    @Override
    public void output(String _string) {
        this.internetConnection.input(_string);
    }
}
