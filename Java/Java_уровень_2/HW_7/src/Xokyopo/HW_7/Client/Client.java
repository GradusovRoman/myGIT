package Xokyopo.HW_7.Client;

import Xokyopo.HW_7.Template.InputMessage;
import Xokyopo.HW_7.Template.InternetConnection;

import java.io.IOException;
import java.net.Socket;

//TODO передача сообщений в графический интерфейс.

public class Client implements InputMessage {
    private int port = 8183;
    private String url = "localhost";
    private InternetConnection internetConnection;

    public static void main(String[] args) {
        Client client = new Client();
    }

    public Client() {
        connection();
    }

    private void connection() {
        try {
            Socket socket = new Socket(this.url, this.port);
            this.internetConnection = new InternetConnection(socket, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void inputMessage(String _msg) {
        this.parsemessageByComand(_msg);
    }

    private void parsemessageByComand(String _msg) {
        //TODO развор сообщения на команды.
        //TODO выполнение этих команд
    }
}
