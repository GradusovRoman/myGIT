package Xokyopo.HW_6.InternetConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class InternetConnection implements InputOtput {
    private InputOtput parent;
    private boolean isConnection = false;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Socket socket;
    private String exitText = "\\exit";

    public InternetConnection(InputOtput _parent) {
        this.parent = _parent;
    }

    public void startAsServer(int _port) throws IOException {
        //TODO многопоток ???
        ServerSocket serverSocket = new ServerSocket(_port);
        this.isConnection = true;
        System.out.println("Сервер запущен запущен");
        this.printSystemInfo();
        //TODO это вывести в многопоток нужно, но требуется контроль открытых соединений.
        this.socket = serverSocket.accept();
        this.initializeInputOutputStream(socket);
        this.waitInput();

    }

    public void startAsClient(String _host, int _port) throws IOException {
        this.socket = new Socket(_host, _port);
        this.initializeInputOutputStream(this.socket);
        this.isConnection = true;
        System.out.println("Клиент запущен");
        this.waitInput();
        this.printSystemInfo();
    }

    private void initializeInputOutputStream(Socket _socket) throws IOException {
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void input(String _string) {
        //TODO метод записывает данные в поток (а как в многопотоке???)
        if (this.isConnection) {
            try {
                this.dataOutputStream.writeUTF(_string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("this.isConnection = " + this.isConnection);
        }
    }

    @Override
    public void output(String _string) {
        //метод передает данные родителю на дальнейшую обработку.
        this.parent.input(_string);
    }

    private void waitInput() {
        //создаем отдельный поток для входящего соединения.
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    readInputSream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void readInputSream() throws IOException {
        String msg = "";
        try {
            while (!msg.contains(this.exitText)) {
                msg = this.dataInputStream.readUTF();
                this.output(msg);
            }
        } catch (IOException e) {
            closeConnection();
            e.printStackTrace();
        }finally {
            this.input(msg);
            closeConnection();
        }
    }

    private void closeConnection() throws IOException {
        System.out.println("закрываем сокет");
        this.dataOutputStream.close();
        this.dataInputStream.close();
        this.socket.close();
        this.isConnection = false;
    }

    public boolean isConnection() {
        //Установлено ли соединение.
        return this.isConnection;
    }

    private void printSystemInfo() {
        System.out.println("\t\t*Информация*\nДля выхода наберите \t" + exitText);
    }
}
