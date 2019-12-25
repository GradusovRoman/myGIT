package client_server_app;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final int port = 8181;
    private final String url = "localhost";

    public void runClient() {
        try (Socket socket = new Socket(this.url, this.port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                objectOutputStream.writeObject(this.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SerialObject getMessage() {
        System.out.println("введите сообщение: ");
        Scanner scanner = new Scanner(System.in);
        return new SerialObject(scanner.nextLine());
    }
}
