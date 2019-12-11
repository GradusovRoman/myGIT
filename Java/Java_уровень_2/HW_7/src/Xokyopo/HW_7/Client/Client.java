package Xokyopo.HW_7.Client;

import Xokyopo.HW_7.Client.Gui.ClientGui;
import Xokyopo.HW_7.MessageParser.MessageParser;
import Xokyopo.HW_7.Template.InputMessage;
import Xokyopo.HW_7.Template.InternetConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

//TODO передача сообщений в графический интерфейс.

public class Client extends Application implements InputMessage {
    private int port = 8183;
    private String url = "localhost";
    private InternetConnection internetConnection;
    private String nickname = "";
    private static final MessageParser messageParser = new MessageParser();
    private ClientGui clientGui = new ClientGui(this);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.authorization();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public void connection() {
        try {
            Socket socket = new Socket(this.url, this.port);
            this.internetConnection = new InternetConnection(socket, this);
        } catch (IOException e) {
            //TODO сервер не найден
            System.out.println("сервер не найден");
            this.connection();
        }
    }


    @Override
    public void inputMessage(String _msg) {
        //TODO обработка входящих сообщений
        Map<MessageParser.Tag, String> message = messageParser.parseStringByTags(_msg);
        if (message.get(MessageParser.Tag.LOGINTAG) != null && message.get(MessageParser.Tag.PASSWORDTAG) !=null) {
            this.authorization();
        } else if(message.get(MessageParser.Tag.SETNIKNAMETAG) != null) {
            this.setNickname(message.get(MessageParser.Tag.SETNIKNAMETAG));
        } else {
            if (message.get(MessageParser.Tag.CLIENTLISTTAG) != null) {
                this.setClientList(message.get(MessageParser.Tag.CLIENTLISTTAG));
            }

            if (message.get(MessageParser.Tag.MESSAGETAG) != null) {
                this.printingMessage(_msg);
                //TODO печатаем сообщения
            }

            if (message.get(MessageParser.Tag.SERVERINFOTAG) != null) {
                //TODO печать системных сообщений
                this.printingMessage(_msg);
            }
        }
    }

    public void setNickname(String _name) {
        //TODO приняты учетные данные
        this.nickname = _name;
        //this.clientGui.setChatGuiEnable();
        System.out.println(_name);
    }

    private void authorization() {
        //TODO процесс выторизации
        this.clientGui.setLoginGuiEnable();
    }

    private void printingMessage(String _message) {
        //TODO вывод сообщений на экран
    }

    private void setClientList(String _clientsList) {

    }

    public void sendLoginAndPassword(String _login, String _password) {
        StringBuilder msg = new StringBuilder();
        msg.append(messageParser.buildStringByTag(_login, MessageParser.Tag.LOGINTAG));
        msg.append(messageParser.buildStringByTag(_password, MessageParser.Tag.PASSWORDTAG));
        this.sendMessage(msg.toString());
    }

    private void sendMessage(String _msg) {
        if (this.internetConnection.isConnection()) {
            //TODO подписываем пакеты.
            this.internetConnection.sendMessage(_msg + messageParser.buildStringByTag(this.nickname, MessageParser.Tag.NICKNAMETAG));
        } else {
            //TODO отвалилися интернет.
            this.connection();
        }
    }

    public void sendMessagePrivate(String _to, String _msg) {
        StringBuilder msg = new StringBuilder();
        msg.append(messageParser.buildStringByTag(_to, MessageParser.Tag.PRIVATETAG));
        msg.append(messageParser.buildStringByTag(_msg, MessageParser.Tag.MESSAGETAG));
        this.sendMessage(msg.toString());
    }

    public void addClientToBlackList(String _name) {
        StringBuilder msg = new StringBuilder();
        msg.append(messageParser.buildStringByTag(_name, MessageParser.Tag.BLACKLISTTAG));
        this.sendMessage(msg.toString());
    }

    public void sedMessageAll(String _msg) {
        this.sendMessage(messageParser.buildStringByTag(_msg, MessageParser.Tag.MESSAGETAG));
    }
}

