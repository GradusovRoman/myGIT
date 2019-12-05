package Xokyopo.HW_6.InternetConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class InternetConnection {
    private InputOtput parent;
    private Vector<NewConnect> newConnectList = new Vector<>();

    public InternetConnection(InputOtput _parent) {
        this.parent = _parent;
    }

    public void startAsServer(int _port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(_port);
        System.out.println(this.getSystemInfo());

        final InternetConnection internetConnection = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        NewConnect newConnect = new NewConnect(serverSocket.accept(), internetConnection);
                        newConnect.sendMessage(getSystemInfo());
                        subscribe(newConnect);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void startAsClient(String _host, int _port) throws IOException {
        this.subscribe(new NewConnect(new Socket(_host, _port), this));
    }


    protected void broadcastMsg(String _string) {
        parent.input(_string);
    }

    public void sendMessageALL(String _string) {
        while (this.newConnectList.iterator().hasNext()) {
            NewConnect newConnection = this.newConnectList.iterator().next();

            if (newConnection.isConnection() && !_string.contains(SystemCommand.EXIT.getText())) {
                newConnection.sendMessage(_string);
            } else {
                newConnection.disconnection();
                this.unsubscribe(newConnection);
            }
        }
    }

    public void subscribe(NewConnect _newConnect) {
        this.newConnectList.add(_newConnect);
    }

    public void unsubscribe(NewConnect _newConnect) {
        this.newConnectList.remove(_newConnect);
    }

    private String getSystemInfo() {
        String msg = String.format("\t\t*Системная информация*\n %s \n %s", SystemCommand.EXIT.getInfo(), SystemCommand.PRIVATE.getInfo());
        return msg;
    }

    public boolean isConnected() {
        return this.newConnectList.size() > 0 ;
    }

    public void disconnect() {
        for (NewConnect newConnect: this.newConnectList) {
            newConnect.disconnection();
        }
    }

}
