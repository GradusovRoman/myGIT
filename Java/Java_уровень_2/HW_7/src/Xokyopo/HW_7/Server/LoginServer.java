package Xokyopo.HW_7.Server;

import Xokyopo.HW_7.Template.InputMessage;
import Xokyopo.HW_7.Template.InternetConnection;

import java.net.Socket;

public class LoginServer extends Thread implements InputMessage {
    private Server server;
    private String authorizationText = "auht";
    private InternetConnection internetConnection;
    private String authorizationFalseText = "соеденение разорвано так как логин и паролль некоректны";
    private String requestTimeOutText = "Превышено время ожидания";
    private String splitText = "\u2795";
    private long requestTimeOut = 5 * 60 * 1000; //5 минута (1 секунда = 1000)
    private boolean request = false;

    public LoginServer(Socket _socket, Server _server){
        this.server = _server;
        internetConnection = new InternetConnection(_socket, this);
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        this.sendAuthorizationRequest();
        long Start = System.currentTimeMillis();

        while(System.currentTimeMillis() - this.requestTimeOut <= Start || !this.request) {
        }
        if (!this.request) this.disconnectionClient(this.requestTimeOutText);
    }

    @Override
    public void inputMessage(String _msg) {
        this.request = true;
        String[] authorization = _msg.split(this.splitText);
        if (_msg.contains(this.authorizationText) && (authorization.length == 3) && checkLoginAndPassword(authorization[1] , authorization[2])) {
            this.connectionClient(authorization);
        } else {
            this.disconnectionClient(this.authorizationFalseText);
        }
    }

    private boolean checkLoginAndPassword(String _login, String _password) {
        //TODO проверка логина и пароля
        return true;
    }

    private void connectionClient(String[] _authorization) {
        this.internetConnection.sendMessage(this.authorizationText + this.splitText + "OK");
        this.internetConnection.setParent(this.server);
        this.server.subscribe(_authorization[1], this.internetConnection);
    }

    private void disconnectionClient(String _msg) {
        this.internetConnection.sendMessage(_msg);
        this.internetConnection.disconnection();
    }

    private void sendAuthorizationRequest() {
        this.internetConnection.sendMessage(this.authorizationText + this.splitText);
    }
}