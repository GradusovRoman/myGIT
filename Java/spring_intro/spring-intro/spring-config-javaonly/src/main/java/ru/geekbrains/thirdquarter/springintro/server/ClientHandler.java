package ru.geekbrains.thirdquarter.springintro.server;

import ru.geekbrains.thirdquarter.springintro.client.TextMessage;
import ru.geekbrains.thirdquarter.springintro.client.MessagePatterns;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Set;

public class ClientHandler {

    private final String login;
    private final Socket socket;
    private final DataInputStream inp;
    private final DataOutputStream out;
    private final Thread handleThread;
    private ChatServer chatServer;

    public ClientHandler(String login, Socket socket, ChatServer chatServer) throws IOException {
        this.login = login;
        this.socket = socket;
        this.inp = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        this.chatServer = chatServer;

        this.handleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        String text = inp.readUTF();
                        System.out.printf("Message from user %s: %s%n", login, text);

                        System.out.println("New message " + text);
                        TextMessage msg = MessagePatterns.parseTextMessageRegx(text, login);
                        if (msg != null) {
                            msg.swapUsers();
                            chatServer.sendMessage(msg);
                        } else if (text.equals(MessagePatterns.DISCONNECT)) {
                            System.out.printf("User %s is disconnected%n", login);
                            chatServer.unsubscribe(login);
                            return;
                        } else if (text.equals(MessagePatterns.USER_LIST_TAG)) {
                            System.out.printf("Sending user list to %s%n", login);
                            sendUserList(chatServer.getUserList());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        chatServer.unsubscribe(login);
                        break;
                    }
                }
            }
        });
        this.chatServer = chatServer;
        this.handleThread.start();
    }

    public String getLogin() {
        return login;
    }

    public void sendMessage(String userFrom, String msg) throws IOException {
        if (socket.isConnected()) {
            out.writeUTF(String.format(MessagePatterns.MESSAGE_SEND_PATTERN, userFrom, msg));
        }
    }

    public void sendConnectedMessage(String login) throws IOException {
        if (socket.isConnected()) {
            out.writeUTF(String.format(MessagePatterns.CONNECTED_SEND, login));
        }
    }

    public void sendDisconnectedMessage(String login) throws IOException {
        if (socket.isConnected()) {
            out.writeUTF(String.format(MessagePatterns.DISCONNECT_SEND, login));
        }
    }

    public void sendUserList(Set<String> users) throws IOException {
        if (socket.isConnected()) {
            out.writeUTF(String.format(MessagePatterns.USER_LIST_RESPONSE, String.join(" ", users)));
        }
    }
}
