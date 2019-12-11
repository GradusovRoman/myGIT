package Xokyopo.HW_7.Server;

import Xokyopo.HW_7.MessageParser.MessageParser;
import Xokyopo.HW_7.Template.InputMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

public class Server implements InputMessage {
    private final int port = 8183;
    public static final MessageParser messageParser = new MessageParser();
    private Vector<ServerInternetConnection> clientsLIst = new Vector<>();


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

    public void subscribe(ServerInternetConnection _client) {
        //добавить клиента
        if (_client != null) {
            this.clientsLIst.add(_client);
            this.serverUpdateClientList(_client.getClientName(), true);
        }
    }

    private void unsubscribe(ServerInternetConnection _client) {
        //удаляем клиента
        if (_client != null) {
            this.clientsLIst.remove(_client);
            disconnect(_client);
            this.serverUpdateClientList(_client.getClientName(), false);
        }
    }

    private void disconnect(ServerInternetConnection _client) {
        //Удаление клиента
        _client.disconnection();
    }

    @Override
    public void inputMessage(String _msg) {
        Map<MessageParser.Tag, String> message = messageParser.parseStringByTags(_msg);

        if (message.get(MessageParser.Tag.SERVERCOMANDTAG) != null) {
            //TODO секция для команд серверу (все которые не определены пока что)
            if (message.get(MessageParser.Tag.SERVERCOMANDTAG).contains("\\quit\\")){
                this.sendMessagePrivate(message.get(MessageParser.Tag.ID), message.get(MessageParser.Tag.SERVERCOMANDTAG));
                this.unsubscribe(this.getClientByName(message.get(MessageParser.Tag.NICKNAMETAG)));
            }
        } else if (message.get(MessageParser.Tag.BLACKLISTTAG) != null) {
            this.addInBlackList(message.get(MessageParser.Tag.NICKNAMETAG), message.get(MessageParser.Tag.BLACKLISTTAG));
        } else if (message.get(MessageParser.Tag.PRIVATETAG) != null) {
            //операция c приватным сообщением
            this.sendMessagePrivate(message.get(MessageParser.Tag.PRIVATETAG), _msg);
        } else if (message.get(MessageParser.Tag.MESSAGETAG) != null) {
            //отправка сообщения всем
            this.sendMessageAll(_msg);
        }
    }

    private void sendMessage(ServerInternetConnection _client, String _msg) {
        if (_client.isConnection()) {
            if (_client.isNameInBlackLIst(Server.messageParser.getValueInStringByTag(_msg, MessageParser.Tag.NICKNAMETAG))) {
                _client.sendMessage(_msg);
            }
        }
        else {
            unsubscribe(_client);
        }
    }

    private void sendMessageAll(String _msg) {
        //Рассылка сообщений всем чатам
        Vector<ServerInternetConnection> c = new Vector<>();
        c.addAll(this.clientsLIst);
        for (ServerInternetConnection client : c) {
            this.sendMessage(client, _msg);
        }
    }

    private void sendMessagePrivate(final String _to, final String _msg) {
        //Отсылка приватного сообщения
        ServerInternetConnection to = this.getClientByName(_to);
        if (to != null) {
            this.sendMessage(to, _msg);
        }
    }

    private void serverUpdateClientList(String _name, boolean _connected) {
        //Сообщаем об изменении списка, и высылаем сам список.
        StringBuffer msg = new StringBuffer();
        msg.append(messageParser.buildStringByTag(_name + ((_connected)? " подключился к чату" : "  отключился от чата" ), MessageParser.Tag.SERVERINFOTAG));
        //TODO вывод системного сообщения
        this.printServerInfo(this.messageParser.getValueInStringByTag(msg.toString(), MessageParser.Tag.SERVERINFOTAG));
        //закончили вывод
        msg.append(messageParser.buildStringByTag(Arrays.toString(getClientList()), MessageParser.Tag.CLIENTLISTTAG));
        this.sendMessageAll(msg.toString());
    }

    private ServerInternetConnection getClientByName(String _name) {
        Vector<ServerInternetConnection> clients = new Vector(this.clientsLIst);
        for (ServerInternetConnection client : clients) {
            if (client.getClientName().equals(_name)) return client;
        }
        return null;
    }

    private String[] getClientList() {
        Vector<ServerInternetConnection> clients = new Vector(this.clientsLIst);
        String[] list = new String[clients.size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = clients.get(i).getClientName();
        }
        return list;
    }

    private void printServerInfo(String _msg) {
        //TODO распечатываем сообщения для сервера вообще и для отладки
        System.out.println(_msg);
    }

    private void addInBlackList(String _clientName, String _banName) {
        ServerInternetConnection client = this.getClientByName(_clientName);
        if (client != null) {
            //TODO добавляем бан в базу данных еще
            client.changeBlackList(_banName);
        }
    }
}
