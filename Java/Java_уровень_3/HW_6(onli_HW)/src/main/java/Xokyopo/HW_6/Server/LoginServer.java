package Xokyopo.HW_6.Server;

import Xokyopo.HW_6.MessageParser.Tag;
import Xokyopo.HW_6.Template.InputMessage;

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
        if (!this.request) this.disconnectionClient(Server.messageParser.buildStringByTag(this.requestTimeOutText, Tag.ERRORTAG));
    }

    @Override
    public void inputMessage(String _msg) {
        Map<Tag, String> message = Server.messageParser.parseStringByTags(_msg);
        if (message.get(Tag.LOGINTAG) != null && message.get(Tag.PASSWORDTAG) != null) {
            String nickname = this.checkLoginAndPassword(message.get(Tag.LOGINTAG), message.get(Tag.PASSWORDTAG));
            if (nickname != null) {
                this.connectionClient(nickname);
            }
            else {
                this.server.printServerInfo(String.format("не удачная попытка подлкючения с логином = %s ; и паролем = %s",message.get(Tag.LOGINTAG), message.get(Tag.PASSWORDTAG)));
                sendAuthorizationRequest();
            }
        }
    }

    private String checkLoginAndPassword(String _login, String _password) {
        String nickname = DataBase.getNickName(_login, _password);
        if (nickname != null && this.server.getClientByName(nickname) == null) {
            return nickname;
        }
        return null;
    }

    private void connectionClient(String _name) {
        this.request = true;
        String blackList = DataBase.getBlackList(_name);
        if (blackList != null) {
            this.serverInternetConnection.changeBlackList(Server.messageParser.stringsArrayToArrayOfString(blackList));
        }
        this.serverInternetConnection.sendMessage(Server.messageParser.buildStringByTag(_name, Tag.SETNIKNAMETAG));
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
        msg.append(Server.messageParser.buildStringByTag("", Tag.LOGINTAG));
        msg.append(Server.messageParser.buildStringByTag("",  Tag.PASSWORDTAG));
        this.serverInternetConnection.sendMessage(msg.toString());
    }
}