package Xokyopo.HW_6.Client;

import Xokyopo.HW_6.ConsoleManager.ConsoleManager;
import Xokyopo.HW_6.InternetConnection.InputOtput;
import Xokyopo.HW_6.InternetConnection.InternetConnection;

import java.io.IOException;

/*
отправить сообщение серверу
запустить сеанс связи с сервером
постоянное ожидаение ввода
постоянный вывод сообщений
 */

public class MyClient extends ConsoleManager implements InputOtput {
    private InternetConnection internetConnection = new InternetConnection(this);;
    private final int PORT = 8183;
    private final String SERVERADDRES = "localhost";
    private String name = "Клиент";

    public static void main(String[] arg) {
        MyClient myClient = new MyClient();
    }

    public MyClient() {
        this.connection();
        this.run();
    }

    private void run() {
        do {
            String msg = this.name + " :" + this.read();
            this.internetConnection.sendMessageALL(msg);
        } while (this.internetConnection.isConnected());
    }

    private void connection() {
        try {
            internetConnection.startAsClient(this.SERVERADDRES, this.PORT);
        } catch (IOException e) {
            System.out.println("сервер не найден");
            this.connection();
            //e.printStackTrace();
        }
    }

    @Override
    public void input(String _string) {
        //Принимаем сообщение
        this.write(_string);
    }

}
