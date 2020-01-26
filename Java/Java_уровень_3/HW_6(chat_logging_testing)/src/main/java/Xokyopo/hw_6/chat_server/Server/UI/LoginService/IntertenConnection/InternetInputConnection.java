package Xokyopo.hw_6.chat_server.Server.UI.LoginService.IntertenConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class InternetInputConnection extends Thread {
    //TODO "InternetInputConnection" вход выход сообщений
    private final Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private boolean connection = false;
    private boolean waitRequest = false;
    private Input input;
    private String exitText = "\\disconnect\\"; // думаю лучше такую строку делать генерировь случайным набором символов.

    public InternetInputConnection(Socket _socket, Input input, String _exitText) {
        this.input = input;
        this.socket = _socket;
        if (_exitText != null ) {
            if (!_exitText.equals("")) this.exitText = _exitText;
        }

        try {
            this.dataInputStream = new DataInputStream(this.socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
            this.connection = true;
            this.setDaemon(true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (this.connection) {
                String msg = this.dataInputStream.readUTF();
                synchronized (this.socket) {
                    if (msg.equals(this.exitText)) {
                        if (!this.waitRequest) {
                            this.send(this.exitText);
                        }
                        break;
                    } else {
                        //TODO проброс сообщения наружу
                        this.input.send(msg);
                    }
                }
            }
        } catch (IOException e) {
            //так как ошибка тут означает обрыв соеденения то закроем сокеты.
            this.closeConnection();
            //e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    public void send(String _msg) {
        try {
            this.dataOutputStream.writeUTF(_msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void closeConnection() {
        this.connection = false;
        try {
            this.dataOutputStream.close();
            this.dataInputStream.close();
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnection() {
        if (this.connection) {
            this.waitRequest = true;
            this.send(this.exitText);
        }
    }

    public boolean isConnection() {
        return this.connection;
    }

    public void setInput(Input input) {
        synchronized (this.socket) {
            this.input = input;
        }
    }
}

