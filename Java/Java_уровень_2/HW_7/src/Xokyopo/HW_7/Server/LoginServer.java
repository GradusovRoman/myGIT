package Xokyopo.HW_7.Server;

import Xokyopo.HW_7.MessageParser.MessageParser;
import Xokyopo.HW_7.Template.InputMessage;

import java.net.Socket;
import java.util.Map;

public class LoginServer extends Thread implements InputMessage {
    private Server server;
    private ServerInternetConnection serverInternetConnection;
    private String requestTimeOutText = "Превышено время ожидания";
    private long requestTimeOut = 5 * 60 * 1000; //5 минута (1 секунда = 1000)
    private boolean request = false;

    public LoginServer(Socket _socket, Server _server){
        this.server = _server;
        this.serverInternetConnection = new ServerInternetConnection(_socket, this);
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        this.sendAuthorizationRequest();
        long Start = System.currentTimeMillis();

        while(System.currentTimeMillis() - this.requestTimeOut <= Start || !this.request) {
        }
        if (!this.request) this.disconnectionClient(Server.messageParser.buildStringByTag(this.requestTimeOutText, MessageParser.Tag.ERRORTAG));
    }

    @Override
    public void inputMessage(String _msg) {
        Map<MessageParser.Tag, String> message = Server.messageParser.parseStringByTags(_msg);
        if (message.get(MessageParser.Tag.LOGINTAG) != null && message.get(MessageParser.Tag.PASSWORDTAG) != null) {
            String nickname = this.checkLoginAndPassword(message.get(MessageParser.Tag.LOGINTAG), message.get(MessageParser.Tag.PASSWORDTAG));
            if (nickname != null) {
                this.connectionClient(nickname);
            }
            else {
                sendAuthorizationRequest();
            }
        }
    }

    private String checkLoginAndPassword(String _login, String _password) {
        //TODO проверка на верность введенных данных (запрос к БД)
        //TODO проверка логина и пароля
        return _login;
    }

    private void connectionClient(String _name) {
        this.request = true;
        this.serverInternetConnection.sendMessage(Server.messageParser.buildStringByTag(_name, MessageParser.Tag.SETNIKNAMETAG));
        this.serverInternetConnection.setParent(this.server);
        this.serverInternetConnection.setClientName(_name);
        this.server.subscribe(this.serverInternetConnection);
    }

    private void disconnectionClient(String _msg) {
        this.serverInternetConnection.sendMessage(_msg);
        this.serverInternetConnection.disconnection();
    }

    private void sendAuthorizationRequest() {
        StringBuilder msg = new StringBuilder();
        msg.append(Server.messageParser.buildStringByTag("", MessageParser.Tag.LOGINTAG));
        msg.append(Server.messageParser.buildStringByTag("",  MessageParser.Tag.PASSWORDTAG));
        this.serverInternetConnection.sendMessage(msg.toString());
    }
}