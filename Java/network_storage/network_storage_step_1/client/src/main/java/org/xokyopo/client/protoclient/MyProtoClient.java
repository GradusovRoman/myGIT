package org.xokyopo.client.protoclient;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.network.netty.NettyClientConnection;
import org.xokyopo.clientservercommon.protocol.MyProtocolHandlerFactory;
import org.xokyopo.clientservercommon.protocol.executors.PStringExecutor;

import java.util.Scanner;

public class MyProtoClient {
    private final String hostName ="";
    private final int port = 8999;
    private NettyClientConnection nettyClientConnection;
    private Channel channel;
    private PStringExecutor PStringExecutor;

    public MyProtoClient() {
        this.createExecutors();
        this.connectionConfig();
    }

    private void connectionConfig() {
        this.nettyClientConnection = new NettyClientConnection(
                new MyProtocolHandlerFactory(
                        this::ifConnect,
                        this::ifDisconnect,
                        this.PStringExecutor
                )
        );
    }

    private void createExecutors() {
        this.PStringExecutor = new PStringExecutor(null);
    }

    private void run() {
        Thread thread = new Thread(()->{
            try {
                this.nettyClientConnection.run(this.hostName, this.port);
            } catch (InterruptedException e) {
                System.out.println("MyProtoClient.run");
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void ifConnect(Channel channel) {
        this.channel = channel;
        System.out.println("Подключились к серверу");
    }

    public void ifDisconnect(Channel channel) {
        this.channel = null;
        System.out.println("Отключились к серверу");
    }

    public String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void sendMessage(String msg) {
        this.PStringExecutor.send(msg, this.channel);
    }

    public static void main(String[] args) {
        MyProtoClient myProtoClient = new MyProtoClient();
        myProtoClient.run();

        while (true) {
            myProtoClient.sendMessage(myProtoClient.getUserInput());
        }
    }
}
