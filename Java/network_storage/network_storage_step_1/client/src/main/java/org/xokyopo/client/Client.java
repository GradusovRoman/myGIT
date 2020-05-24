package org.xokyopo.client;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.FileListMessageExecutor;
import org.xokyopo.clientservercommon.executors.FileOperationExecutor;
import org.xokyopo.clientservercommon.executors.FilePartExecutor;
import org.xokyopo.clientservercommon.executors.messages.FileListMessage;
import org.xokyopo.clientservercommon.executors.messages.FileOperationMessage;
import org.xokyopo.clientservercommon.simples.MyHandlerFactory;
import org.xokyopo.clientservercommon.executors.messages.StringMessage;
import org.xokyopo.clientservercommon.simples.entitys.Message;
import org.xokyopo.clientservercommon.network.netty.NettyClientConnection;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Client {
    private final String repository = "client_repository";
    private Channel channel;
    public FilePartExecutor filePartExecutor;
    private CountDownLatch countDownLatch;

    public Client() {
        this.filePartExecutor = new FilePartExecutor(this::getClientRepository);
        this.countDownLatch = new CountDownLatch(1);
        NettyClientConnection clientConnection = new NettyClientConnection(
                new MyHandlerFactory(this::ifConnect, this::ifDisconnect,
                        new StringExecutor(),
                        new FileListMessageExecutor(this::getClientRepository, (listFile)->{
                            listFile.forEach(e->System.out.println("name: " + e.getName() + "\tsize:" + e.getSize()));
                        }),
                        new FileOperationExecutor(this::getClientRepository, System.out::println),
                        this.filePartExecutor
                )
        );

        Thread t = new Thread(()-> {
            try {
                clientConnection.run("localhost", 8999);
            } catch (Exception e) {
                System.out.println("Client.Client()");
                e.printStackTrace();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    public void ifConnect(Channel channel) {
        if (this.countDownLatch != null) this.countDownLatch.countDown();
        this.channel = channel;
    }

    public void ifDisconnect(Channel channel) {
        this.channel = null;
    }

    public void awaitRun() {
        if (this.countDownLatch != null) {
            try {
                this.countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String msg) {
        if (channel != null) {
            this.channel.writeAndFlush(new StringMessage(Message.Type.REQUEST, msg));
            System.out.println("сообщение отправлено->>");
        }
    }

    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String msg = scanner.nextLine();
            if (msg.equals("\\q")) {
                System.out.println("выход");
                this.channel.close();
                break;
            } else if (msg.contains("\\dir")) {
                String[] arg = msg.split("\\s");
                this.channel.writeAndFlush(new FileListMessage(Message.Type.REQUEST, null, (arg.length > 1) ? arg[1] : ""));
            } else if (msg.contains("\\cp")) {
                String[] arg = msg.split("\\s");
                if (arg.length == 3) {
                    this.channel.writeAndFlush(new FileOperationMessage(Message.Type.REQUEST, FileOperationMessage.OType.COPY, arg[1], arg[2]));
                }
            } else if(msg.contains("\\mv")) {
                String[] arg = msg.split("\\s");
                if (arg.length == 3) {

                    this.channel.writeAndFlush(new FileOperationMessage(Message.Type.REQUEST, FileOperationMessage.OType.MOVE, arg[1], arg[2]));
                }
            } else if (msg.contains("\\del")) {
                String[] arg = msg.split("\\s");
                if (arg.length == 2) {
                    this.channel.writeAndFlush(new FileOperationMessage(Message.Type.REQUEST, FileOperationMessage.OType.DELETE, arg[1], ""));
                }
            } else if(msg.contains("\\upl")) {
                String[] arg = msg.split("\\s");
                System.out.println("пытаюсь отправить файл: " + arg[1]);
                if (arg.length == 2) {
                    this.filePartExecutor.uploadFile(arg[1],this.channel);
                }
            } else if(msg.contains("\\lod")) {
                String[] arg = msg.split("\\s");
                System.out.println("пытаюсь получить файл: " + arg[1]);
                if (arg.length == 2) {
                    this.filePartExecutor.loadFile(arg[1],  this.channel);
                }
            }else {
                this.send(msg);
            }
        }
    }

    private String getClientRepository(Channel channel) {
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

    public static void main(String[] args) {
        Client client = new Client();
        client.awaitRun();
        System.out.println("соединние установлено");
        client.getUserInput();
    }
}
