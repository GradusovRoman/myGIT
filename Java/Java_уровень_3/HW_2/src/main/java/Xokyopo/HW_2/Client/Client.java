package Xokyopo.HW_2.Client;

import Xokyopo.HW_2.Client.Gui.ClientGui;
import Xokyopo.HW_2.MessageParser.MessageParser;
import Xokyopo.HW_2.MessageParser.Tag;
import Xokyopo.HW_2.Template.InputMessage;
import Xokyopo.HW_2.Template.InternetConnection;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

//TODO передача сообщений в графический интерфейс.

public class Client extends Application implements InputMessage {
    private int port = 8183;
    private String url = "localhost";
    private InternetConnection internetConnection;
    private String nickname = "";
    public static final MessageParser messageParser = new MessageParser();
    private ClientGui clientGui = new ClientGui(this);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.connection();
        //TODO ошибка в Maven не видит fxml
        //this.clientGui.setChatGuiEnable();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //TODO для решение проблемы отписки клиента (internetConnection знает о состоянии подключения)
        String msg = Client.messageParser.buildStringByTag("\\quit\\", Tag.SERVERCOMANDTAG);
        this.sedMessageAll(msg);
        this.internetConnection.disconnection();
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
        if (messageParser.isContainsTag(_msg, Tag.LOGINTAG) && messageParser.isContainsTag(_msg, Tag.PASSWORDTAG)) {
            authorization();
        } else if(messageParser.isContainsTag(_msg, Tag.SETNIKNAMETAG)) {
            setNickname(messageParser.getValueInStringByTag(_msg, Tag.SETNIKNAMETAG));
        } else {
            printingMessage(_msg);
        }
            }
        });
    }

    public void setNickname(String _name) {
        //TODO приняты учетные данные
        this.nickname = _name;
        this.clientGui.setChatGuiEnable();
        this.clientGui.setTitleText(_name);
    }

    private void authorization() {
        //TODO процесс авторизации
        clientGui.setLoginGuiEnable();
    }

    private void printingMessage(String _message) {
        //TODO вывод сообщений на экран
        this.clientGui.getCurrentController().inputMessage(_message);
    }

    public void sendLoginAndPassword(String _login, String _password) {
        StringBuilder msg = new StringBuilder();
        msg.append(messageParser.buildStringByTag(_login, Tag.LOGINTAG));
        msg.append(messageParser.buildStringByTag(_password, Tag.PASSWORDTAG));
        this.sendMessage(msg.toString());
    }

    private void sendMessage(String _msg) {
        if (this.internetConnection.isConnection()) {
            //TODO подписываем пакеты.
            this.internetConnection.sendMessage(_msg + messageParser.buildStringByTag(this.nickname, Tag.NICKNAMETAG));
        } else {
            //TODO отвалилися интернет.
            this.waitInseconds(20);
            this.connection();
        }
    }

    public void sendMessagePrivate(String _to, String _msg) {
        StringBuilder msg = new StringBuilder();
        msg.append(messageParser.buildStringByTag(_to, Tag.PRIVATETAG));
        msg.append(messageParser.buildStringByTag(_msg, Tag.MESSAGETAG));
        this.sendMessage(msg.toString());
    }

    public void addClientToBlackList(String _name) {
        StringBuilder msg = new StringBuilder();
        msg.append(messageParser.buildStringByTag(_name, Tag.BLACKLISTTAG));
        this.sendMessage(msg.toString());
    }

    public void sedMessageAll(String _msg) {
        this.sendMessage(messageParser.buildStringByTag(_msg, Tag.MESSAGETAG));
    }

    private void waitInseconds(long _time) {
        long start = System.currentTimeMillis();
        while ((start + _time * 1000) <= System.currentTimeMillis()) {

        }
    }
}

