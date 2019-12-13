package Xokyopo.HW_7.Server;

import Xokyopo.HW_7.MessageParser.MessageParser;
import Xokyopo.HW_7.MessageParser.Tag;
import Xokyopo.HW_7.Template.InputMessage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.*;

public class Server extends Application implements InputMessage {
    private final int port = 8183;
    public static final MessageParser messageParser = new MessageParser();
    private Vector<ServerInternetConnection> clientsLIst = new Vector<>();
    private ServerController serverController = new ServerController(this);
    private String serverFXMLPath = "/server.fxml";


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataBase.connection();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(this.serverFXMLPath));
        fxmlLoader.setController(this.serverController);
        primaryStage.setScene(new Scene(fxmlLoader.load()));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataBase.disconnection();
    }

    public void connection() {
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
        Map<Tag, String> message = messageParser.parseStringByTags(_msg);

        if (message.get(Tag.SERVERCOMANDTAG) != null) {
            //TODO секция для команд серверу (все которые не определены пока что)
            if (message.get(Tag.SERVERCOMANDTAG).contains("\\quit\\")){
                this.sendMessagePrivate(message.get(Tag.ID), message.get(Tag.SERVERCOMANDTAG));
                this.unsubscribe(this.getClientByName(message.get(Tag.NICKNAMETAG)));
            }
        } else if (message.get(Tag.BLACKLISTTAG) != null) {
            this.addInBlackList(message.get(Tag.NICKNAMETAG), message.get(Tag.BLACKLISTTAG));
        } else if (message.get(Tag.PRIVATETAG) != null) {
            //операция c приватным сообщением
            this.sendMessagePrivate(message.get(Tag.PRIVATETAG), _msg);
        } else if (message.get(Tag.MESSAGETAG) != null) {
            //отправка сообщения всем
            this.sendMessageAll(_msg);
        }
    }

    private void sendMessage(ServerInternetConnection _client, String _msg) {
        if (_client.isConnection()) {
            if (!_client.isNameInBlackLIst(Server.messageParser.getValueInStringByTag(_msg, Tag.NICKNAMETAG))) {
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
        String msg = _name + ((_connected)? " подключился к чату" : "  отключился от чата");
        this.printServerInfo(msg);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                serverController.updateClientList(Server.messageParser.stringsArrayToArrayOfString(getClientList()));
                sendClientsListAll();
            }
        });
        this.sendMessageAll(messageParser.buildStringByTag(msg, Tag.SERVERINFOTAG));
    }

    public ServerInternetConnection getClientByName(String _name) {
        Vector<ServerInternetConnection> clients = new Vector(this.clientsLIst);
        for (ServerInternetConnection client : clients) {
            if (client.getClientName().equals(_name)) return client;
        }
        return null;
    }

    private String getClientList() {
        Vector<ServerInternetConnection> clients = new Vector(this.clientsLIst);
        String[] list = new String[clients.size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = clients.get(i).getClientName();
        }
        return Arrays.toString(list);
    }

    private void printServerInfo(String _msg) {
        this.serverController.printLog(_msg);
    }

    private void addInBlackList(String _clientName, String _banName) {
        ServerInternetConnection client = this.getClientByName(_clientName);
        if (client != null) {
            client.changeBlackList(_banName);
            DataBase.addClientToBlackList(_clientName, _banName);
        }
        //TODO заглушка: высылаем всем измененные списки и сообщение
        this.sendClientsListAll();
    }

    private void sendClientsListAll() {
        //Рассылка сообщений всем чатам
        Vector<ServerInternetConnection> c = new Vector<>();
        c.addAll(this.clientsLIst);
        for (ServerInternetConnection client : c) {
            String blackLIst = this.getClientLIstWithBlockedClients(getClientList(), Arrays.toString(client.getBlackList()));
            this.sendMessage(client, Server.messageParser.buildStringByTag(blackLIst, Tag.CLIENTLISTTAG));
        }
    }

    private String getClientLIstWithBlockedClients(String _clientList, String _blackList) {
        String[] s = Server.messageParser.stringsArrayToArrayOfString(_blackList);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(Server.messageParser.stringsArrayToArrayOfString(_clientList)));
        for (int i = 0; i < s.length; i++) {
            if (list.contains(s[i])) {
                int indexInList = list.indexOf(s[i]);
                String name = list.get(indexInList);
                list.remove(indexInList);
                list.add("(mute)" + name);
            }
        }
        return Arrays.toString(list.toArray(new String[0]));
    }

}
