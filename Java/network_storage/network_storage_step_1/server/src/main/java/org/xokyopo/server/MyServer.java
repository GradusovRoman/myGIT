package org.xokyopo.server;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.FileListMessageExecutor;
import org.xokyopo.clientservercommon.executors.FileOperationExecutor;
import org.xokyopo.clientservercommon.executors.FilePartExecutor;
import org.xokyopo.clientservercommon.simples.MyHandlerFactory;
import org.xokyopo.clientservercommon.network.netty.NettyServerConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyServer {
    private final String repository = "server_repository";

    public MyServer() {
        NettyServerConnection nettyServerConnection = new NettyServerConnection(
                new MyHandlerFactory(this::ifConnect, this::ifDisconnect,
                        new StringExecutor(),
                        new FileListMessageExecutor(this::getServerRepository, null),
                        new FileOperationExecutor(this::getServerRepository, null),
                        new FilePartExecutor(this::getServerRepository)
                )
        );
        try {
            nettyServerConnection.run(8999);
        } catch (Exception e) {
            System.out.println("MyServer.MyServer()");
            e.printStackTrace();
        }
    }

    private String getServerRepository(Channel channel) {
        Path path = Paths.get(this.repository);
        if (!path.toFile().exists()) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return this.repository;
    }

    public void ifConnect(Channel channel) {
        System.out.println("Подключился пользователь");
    }

    public void ifDisconnect(Channel channel) {
        System.out.println("Отключился пользователь");
    }

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
    }
}
