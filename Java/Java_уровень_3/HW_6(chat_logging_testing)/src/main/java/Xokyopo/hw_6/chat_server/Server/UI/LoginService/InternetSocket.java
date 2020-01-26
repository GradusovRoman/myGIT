package Xokyopo.hw_6.chat_server.Server.UI.LoginService;


import Xokyopo.hw_6.chat_server.Server.UI.LoginService.IntertenConnection.InternetInputConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class InternetSocket extends Thread {
    ServerSocket socket;
    //TODO "InternetSocket" эта штука будет ждать конекта и пересылать всех в основную обработку, кроме как соединения она ничего делать не будет.

    public InternetSocket(int port) throws IOException {
        //TODO "InternetSocket" для ожидания нужно передать сокет в класс который будет ждать?
        this.socket = new ServerSocket(port);
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                InternetInputConnection internetConnection = new InternetInputConnection(socket.accept(), this::inputMessage, null);

                //TODO "InternetSocket" вот мы получили соединение с клиентом и че с ним делать дальше будем?
            } catch (IOException e) {
                //TODO обработка ошибки получения сокета
                System.err.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }

    private void inputMessage(String msg) {
        //входящее сообщение
    }

}