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
        try {
            internetConnection.startAsClient(this.SERVERADDRES, this.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //this.readAllways(this, this.name + " :"); //не работает если использовать это с выходом из цикла
        //TODO не выходит после присылания ексит, ждет еще один ввод для завершения.
        while (this.internetConnection.isConnection()) {
            this.output(this.name + " :" + this.read());
        }
        System.out.println("выход");
    }

    @Override
    public void input(String _string) {
        //Принимаем сообщение
        this.write(_string);
    }

    @Override
    public void output(String _string) {
        //Отправляем сообщение
        this.internetConnection.input(_string);
    }

}
