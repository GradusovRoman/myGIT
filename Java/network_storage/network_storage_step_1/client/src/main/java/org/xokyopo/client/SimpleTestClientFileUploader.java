package org.xokyopo.client;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.AuthorizationExecutor;
import org.xokyopo.clientservercommon.executors.FileListExecutor;
import org.xokyopo.clientservercommon.executors.FileOperationExecutor;
import org.xokyopo.clientservercommon.executors.FilePartExecutor;
import org.xokyopo.clientservercommon.network.netty.NettyClientConnection;
import org.xokyopo.clientservercommon.simples.MyHandlerFactory;

import java.util.concurrent.CountDownLatch;

public class SimpleTestClientFileUploader {
    private final String repository = "client_repository";
    private final String remoteUrl = "localhost";
    private final int remotePort = 8999;
    private Channel channel;
    public FilePartExecutor filePartExecutor;
    public FileListExecutor fileListExecutor;
    public AuthorizationExecutor authorizationExecutor;
    public FileOperationExecutor fileOperationExecutor;
    private NettyClientConnection nettyClientConnection;
    private CountDownLatch runAwait;
    private String userName;

    public SimpleTestClientFileUploader(CountDownLatch runAwait, String userName) {
        this.createExecutors();
        this.constructingServer();
        this.runAwait = runAwait;
        this.userName = userName;
        try {
            this.runTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void run() {
        Thread t = new Thread(()-> {
            try {
                this.nettyClientConnection.run(this.remoteUrl, this.remotePort);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void constructingServer() {
        this.nettyClientConnection = new NettyClientConnection(
                new MyHandlerFactory(
                        this::ifConnect,
                        this::ifDisconnect,
                        this.authorizationExecutor,
                        this.fileListExecutor,
                        this.fileOperationExecutor,
                        this.filePartExecutor
                )
        );
    }

    private void createExecutors() {
        this.authorizationExecutor = new AuthorizationExecutor(
                this::acceptAuth,
                null);

        this.fileListExecutor = new FileListExecutor(this::getRepository, null);

        this.fileOperationExecutor = new FileOperationExecutor(this::getRepository, null);

        this.filePartExecutor = new FilePartExecutor(
                this::getRepository,
                null,
                null,
                null
        );
    }

    private String getRepository(Channel channel) {
        return this.repository;
    }

    public void ifDisconnect(Channel channel) {
        this.channel = null;
    }

    public void ifConnect(Channel channel) {
        this.channel = channel;
        this.authorizationExecutor.sendLoginAndPass(this.userName, this.userName, channel);
    }

    public void acceptAuth(boolean isAuth) {
        this.runAwait.countDown();
    }

    private void runTest() throws Exception {
        this.run();
        this.runAwait.await();
        this.filePartExecutor.uploadFile("test.iso","","", this.channel);
    }

    public static void main(String[] args) {
        int clientCount = 200;
        CountDownLatch countDownLatch = new CountDownLatch(clientCount);
        for (int i = 0; i < clientCount; i++) {
            final int f = i;
            new Thread(()->new SimpleTestClientFileUploader(countDownLatch, ("client_" + f))).start();
        }
    }
}
