package org.xokyopo.clientservercommon.executors;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.impl.IChannelRootDir;
import org.xokyopo.clientservercommon.executors.messages.FilePartMessage;
import org.xokyopo.clientservercommon.network.impl.MessageExecutor;
import org.xokyopo.clientservercommon.simples.entitys.Message;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class FilePartExecutor implements MessageExecutor {
    private final IChannelRootDir channelRootDir;
    private final int partSize = 800000;
    private long start;

    public FilePartExecutor(IChannelRootDir channelRootDir) {
        this.channelRootDir = (channelRootDir == null)? (Ch)->"." : channelRootDir;
    }

    @Override
    public Class<? extends Message> getInputMessageClass() {
        return FilePartMessage.class;
    }

    @Override
    public boolean isLongTimeOperation() {
        return true;
    }

    @Override
    public void execute(Message message, Channel channel) {
        //TODO обработка входных данных
        FilePartMessage msg = (FilePartMessage) message;
        String workingDir = this.channelRootDir.getRootDir(channel);
        //todo если директория???
        try {
            if (msg.getType().equals(Message.Type.RESPONSE)) {
                    this.writeFile(workingDir, msg);
                    System.out.println("получено " + msg.getCurrentPart() + " из " + msg.getNumberOfPart() + " частей файл: \t" + msg.getFileName());
                    if (msg.getCurrentPart() < msg.getNumberOfPart()) {
                        if (msg.getCurrentPart() == 0) this.start = System.currentTimeMillis();
                        this.requestingNextPart(msg.getFileName(), msg.getFilePath(), msg.getCurrentPart() + 1, channel);
                    } else {
                        long stop = System.currentTimeMillis();
                        float fileLength = (long) msg.getNumberOfPart() * (long) this.partSize / 1048576.0f;
                        System.out.println("получен файл: \t" + msg.getFileName() + " размером ~ " + fileLength + "Mb за " + (stop - this.start) + "ms");
                        //TODO CallBack если все получено
                    }
            } else if (msg.getType().equals(Message.Type.REQUEST)) {
                this.sendFile(workingDir, msg, channel);
            }
        } catch (Exception e) {
            System.out.println("FilePartExecutor.execute.writeFile");
            e.printStackTrace();
        }
    }

    private void writeFile(String workingDir, FilePartMessage msg) throws Exception {
        Path path = Paths.get(workingDir, msg.getFileName());
        if (!path.getParent().toFile().exists()) Files.createDirectories(path.getParent());
        this.saveFilePart(path, msg.getCurrentPart(), this.convertToPrimitive(msg.getFilePart()));
    }

    public void loadFile(String fileName, Channel channel) {
        this.requestingNextPart(fileName, "", 0, channel);
    }

    public void uploadFile(String fileName, Channel channel) {
        try {
            this.sendFilePart(Paths.get(fileName), 0 , channel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestingNextPart(String fileName, String filePath, int part, Channel channel) {
        channel.writeAndFlush(
                new FilePartMessage(
                        Message.Type.REQUEST,
                        fileName,
                        filePath,
                        part,
                        0,
                        null
                )
        );
    }

    private void sendFile(String workingDir, FilePartMessage msg, Channel channel) throws Exception {
        Path path = (msg.getFilePath().equals("")) ? Paths.get(workingDir, msg.getFileName()): Paths.get(msg.getFilePath());
        this.sendFilePart(path, msg.getCurrentPart(), channel);
    }

    private void sendFilePart(Path pathToFile, int filePart, Channel channel) throws Exception {
        File file = pathToFile.toFile();
        if (file.exists() && file.isFile()) {
                channel.writeAndFlush(
                        new FilePartMessage(
                                Message.Type.RESPONSE,
                                file.getName(),
                                file.getCanonicalPath(),
                                filePart,
                                (int)(file.length()/this.partSize),
                                this.getFilePart(file, this.partSize, filePart)
                        )
                );
            System.out.println("отправил-> " + filePart + " из " + (int)(file.length()/this.partSize));
        }
        //todo а если нет такого файла?
    }

    private Byte[] getFilePart(File file, int byteLength, int part) throws Exception {
        //TODO проверка на соответсвие запроса части и длинне файла.
        byte[] bytes = new byte[byteLength];
        RandomAccessFile rFile = new RandomAccessFile(file, "r");
        rFile.seek((long)byteLength * (long)part);
        int len = rFile.read(bytes);
        //TODO конвертер byte[] to Byte[].
        return this.convertToObject(bytes, len);
    }

    private void saveFilePart(Path path, int part , byte[] arr) throws Exception {
        if (path.toFile().exists()) {
            long awaitingLength = ((long) part * (long) this.partSize);
            if (path.toFile().length() == awaitingLength) {
                Files.write(path, arr, APPEND);
            }
        } else if (part == 0) {
            Files.write(path, arr, CREATE);
        } else {
            System.out.println("до сюда не должно было дойти writeFile");
            throw new RuntimeException("до сюда не должно было дойти writeFile");
        }
    }

    private Byte[] convertToObject(byte[] arr, int length) {
        Byte[] bytes = new Byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = arr[i];
        }
        return bytes;
    }

    private byte[] convertToPrimitive(Byte[] arr) {
        byte[] bytes = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            bytes[i] = arr[i];
        }
        return bytes;
    }
}
