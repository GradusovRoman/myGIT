package Xokyopo.HW_7.Server;

import Xokyopo.HW_7.Template.Client;
import Xokyopo.HW_7.Template.InputMessage;
import Xokyopo.HW_7.Template.InternetConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

//TODO обрыв соединения
//TODO клиент отключился от сети (как обрабатывать будем?)
public class Server implements InputMessage {
    private final int port = 8183;
    private String PrivateSplitText = "\\P";
    //private Map<String, InternetConnection> clientLIst = Collections.synchronizedMap(new HashMap<>());
    private Vector<Client> clientLIst = new Vector<>();
    //TODO создание соединения.
    //TODO Управление клиентами

    public static void main(String[] args) {
        Server server = new Server();
    }

    public Server() {
        this.connection();
    }

    private void connection() {
        try {
            ServerSocket socket = new ServerSocket(this.port);
            while (true) {
                new LoginServer(socket.accept(), this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String _name, InternetConnection _client) {
        //добавить клиента
        System.out.println("подключен " + _name);
        this.clientLIst.add(new Client(_name, _client));
        this.serverUpdateClientList(_name, true);
    }

    private void unsubscribe(String _name) {
        //удаляем клиента
        System.out.println("отключен " + _name);
        Client client = this.getClientByName(_name);
        disconnect(client.getInternetConnection());
        this.clientLIst.remove(client);
        this.serverUpdateClientList(_name, false);
    }

    private void disconnect(InternetConnection _internetConnection) {
        //Удаление клиента
        _internetConnection.disconnection();
    }

    @Override
    public void inputMessage(String _msg) {
        Message message = new Message(_msg);
        if (_msg.contains("\\quit\\")) {
            this.sendPrivateMessage("Сервер" , message.getAuthorName(), message.getMessage());
            this.unsubscribe(message.getAuthorName());
        } else if (_msg.contains(":\\P ")) {
            this.sendPrivateMessage(message.getAuthorName(), message.getPrivateTarget(), message.getMessage());
        } else {
            this.sendMessage( message.getAuthorName(), message.getMessage());
        }
    }

    private void sendMessage(final String _from, final String _msg) {
        //Рассылка сообщений всем чатам
//        for (Map.Entry<String, InternetConnection> client : clientLIst.entrySet()) {
//            if (client.getValue().isConnection()) {
//                client.getValue().sendMessage(String.format("%s:%s\n", _from, _msg));
//            }
//            else {
//                unsubscribe(client.getKey());
//            }
//        }
        Vector<Client> c = new Vector<>();
        c.addAll(this.clientLIst);
        for (Client client : c) {
            if (client.getInternetConnection().isConnection()) {
                client.getInternetConnection().sendMessage(String.format("%s:%s\n", _from, _msg));
            }
            else {
                unsubscribe(client.getName());
            }
        }
    }

    private void sendPrivateMessage(final String _from, final String _to, final String _msg) {
        //Отсылка приватного сообщения
        InternetConnection to = this.getClientByName(_to).getInternetConnection();
        if (to != null) {
            if (to.isConnection()) {
                to.sendMessage(String.format("private from %s:%s\n", _from, _msg));
            }
            else {
                unsubscribe(_to);
            }
        }
    }

    private void serverUpdateClientList(String _name, boolean _connected) {
        this.sendMessage("Сервер", _name + ((_connected)? " подключился к чату" : "  отключился от чата" ));
        //высылаем список клиентов
        this.sendMessage("Сервер", Arrays.toString(getClientList()));
    }

    private Client getClientByName(String _name) {
        Vector<Client> c = new Vector<>();
        c.addAll(this.clientLIst);
        for (Client client : c) {
            if (client.getName().equals(_name)) return client;
        }
        return null;
    }

    private String[] getClientList() {
        Vector<Client> clients = new Vector(this.clientLIst);
        String[] list = new String[clients.size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = clients.get(i).getName();
        }
        return list;
    }
}
