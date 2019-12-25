package client_server_app;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port = 8181;

    public void runServer() {
        try (ServerSocket socket = new ServerSocket(this.port)) {
            Socket sSocket = socket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(sSocket.getInputStream());
            while (true) {
              try {
                  SerialObject msg= (SerialObject)objectInputStream.readObject();
                  printObject(msg);
              }catch (ClassNotFoundException e) {
              }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printObject(SerialObject msg) {
        System.err.println("сервер >" +msg.getText());
    }
}
