package Xokyopo.hw_6.chat_server.Template;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class InternetConnection extends Thread{
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private InputMessage parent;
    private boolean connection = false;
    private boolean waitRequest = false;
    private String exitText = "\\disconnect\\"; // думаю лучше такую строку делать генерировь случайным набором символов.

    public InternetConnection(Socket _socket, InputMessage _parent) {
        this.parent = _parent;
        this.socket = _socket;
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
                if (msg.equals(this.exitText)) {
                    if (!this.waitRequest) {
                        sendMessage(this.exitText);
                    }
                    break;
                } else {
                    this.sendMessageToParent(msg);
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

    public void sendMessage(String _msg) {
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
            this.sendMessage(this.exitText);
        }
    }

    public boolean isConnection() {
        return this.connection;
    }

    public void setParent(InputMessage _parent) {
        this.parent = _parent;
    }

    protected void sendMessageToParent(String _msg) {
        this.parent.inputMessage(_msg);
    }
}
