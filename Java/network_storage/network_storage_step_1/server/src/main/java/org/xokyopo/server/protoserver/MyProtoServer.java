package org.xokyopo.server.protoserver;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.network.netty.NettyServerConnection;
import org.xokyopo.clientservercommon.protocol.MyProtocolHandlerFactory;
import org.xokyopo.clientservercommon.protocol.executors.PStringExecutor;
import org.xokyopo.server.dao.DataBaseManager;

public class MyProtoServer {
    private final String repository = "server_repository";
    private final int serverPort = 8999;

    private NettyServerConnection nettyServerConnection;

    public MyProtoServer() {
        this.constructingServer();
    }

    public void run() throws InterruptedException {
        try {
            DataBaseManager.connection();
            nettyServerConnection.run(this.serverPort);
        } finally {
            DataBaseManager.disconnection();
        }
    }

    private void constructingServer() {
        this.nettyServerConnection = new NettyServerConnection(
                new MyProtocolHandlerFactory(
                        this::ifConnect,
                        this::ifDisconnect,
                        new PStringExecutor((msg, ch)->System.out.println("<<<" + msg))
                )
        );
    }

    public void ifConnect(Channel channel) {
        System.out.println("Подключился пользователь:\t");
    }

    public void ifDisconnect(Channel channel) {
        System.out.println("Отключился пользователь:\t");
    }

    public static void main(String[] args) {
        MyProtoServer myServer = new MyProtoServer();
        try {
            System.out.println("запускаю сервер");
            myServer.run();
        } catch (InterruptedException e) {
            System.out.println("Server start error");
            e.printStackTrace();
        }
    }
}
