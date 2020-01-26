package Xokyopo.hw_6.chat_server.Server.UI;

import Xokyopo.hw_6.chat_server.MessageParser.Tag;
import Xokyopo.hw_6.chat_server.Server.DAO.DataBase.DataBase_old;
import Xokyopo.hw_6.chat_server.Server.Domain.Server_old;
import Xokyopo.hw_6.chat_server.Template.InputMessage;

import java.net.Socket;
import java.util.Map;

public class LoginServer_old extends Thread implements InputMessage {
    private Server_old serverOld;
    private ServerInternetConnection serverInternetConnection;
    private String requestTimeOutText = "Превышено время ожидания";
    private long requestTimeOut = 5 * 60 * 1000; //5 минута (1 секунда = 1000)
    private boolean request = false;

    public LoginServer_old(Socket _socket, Server_old _serverOld){
        this.serverOld = _serverOld;
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
        if (!this.request) this.disconnectionClient(Server_old.messageParser.buildStringByTag(this.requestTimeOutText, Tag.ERRORTAG));
    }

    @Override
    public void inputMessage(String _msg) {
        Map<Tag, String> message = Server_old.messageParser.parseStringByTags(_msg);
        if (message.get(Tag.LOGINTAG) != null && message.get(Tag.PASSWORDTAG) != null) {
            String nickname = this.checkLoginAndPassword(message.get(Tag.LOGINTAG), message.get(Tag.PASSWORDTAG));
            if (nickname != null) {
                this.connectionClient(nickname);
            }
            else {
                this.serverOld.printServerInfo(String.format("не удачная попытка подлкючения с логином = %s ; и паролем = %s",message.get(Tag.LOGINTAG), message.get(Tag.PASSWORDTAG)));
                sendAuthorizationRequest();
            }
        }
    }

    private String checkLoginAndPassword(String _login, String _password) {
        String nickname = DataBase_old.getNickName(_login, _password);
//        if (nickname != null && this.serverOld.getClientByName(nickname) == null) {
//            return nickname;
//        }
        return null;
    }

    private void connectionClient(String _name) {
        this.request = true;
        String blackList = DataBase_old.getBlackList(_name);
        if (blackList != null) {
            this.serverInternetConnection.changeBlackList(Server_old.messageParser.stringsArrayToArrayOfString(blackList));
        }
        this.serverInternetConnection.sendMessage(Server_old.messageParser.buildStringByTag(_name, Tag.SETNIKNAMETAG));
        this.serverInternetConnection.setParent(this.serverOld);
        this.serverInternetConnection.setClientName(_name);
        this.serverOld.subscribe(this.serverInternetConnection);
    }

    private void disconnectionClient(String _msg) {
        this.serverInternetConnection.sendMessage(_msg);
        this.serverInternetConnection.disconnection();
    }

    private void sendAuthorizationRequest() {
        StringBuilder msg = new StringBuilder();
        msg.append(Server_old.messageParser.buildStringByTag("", Tag.LOGINTAG));
        msg.append(Server_old.messageParser.buildStringByTag("",  Tag.PASSWORDTAG));
        this.serverInternetConnection.sendMessage(msg.toString());
    }
}