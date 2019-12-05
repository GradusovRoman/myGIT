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
        this.connection();
        this.run();
    }

    private void connection () {
        try {
            this.internetConnection.startAsServer(this.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run() {
        while (true) {
            String msg = this.name + " :" + this.read();
            if (this.internetConnection.isConnected()) {
                this.internetConnection.sendMessageALL(msg);
            }
        }
    }

    @Override
    public void input(String _string) {
        this.write(_string);
        this.internetConnection.sendMessageALL(_string);
    }

}
