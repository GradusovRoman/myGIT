package org.xokyopo.clientservercommon.executors;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.impl.IChannelRootDir;
import org.xokyopo.clientservercommon.executors.messages.FileOperationMessage;
import org.xokyopo.clientservercommon.network.impl.Callback;
import org.xokyopo.clientservercommon.network.impl.MessageExecutor;
import org.xokyopo.clientservercommon.simples.entitys.Message;
import org.xokyopo.clientservercommon.executors.messages.FileOperationMessage.OType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperationExecutor implements MessageExecutor {
    private final IChannelRootDir channelRootDir;
    private final Callback<String> stringCallback;

    public FileOperationExecutor(IChannelRootDir channelRootDir, Callback<String> stringCallback) {
        this.channelRootDir = (channelRootDir == null)? (Ch)->"." : channelRootDir;
        this.stringCallback = stringCallback;
    }

    @Override
    public Class<? extends Message> getInputMessageClass() {
        return FileOperationMessage.class;
    }

    @Override
    public boolean isLongTimeOperation() {
        return false;
    }

    @Override
    public void execute(Message message, Channel channel) {
        FileOperationMessage msg = (FileOperationMessage) message;
        if (message.getType().equals(Message.Type.REQUEST)) {
            String workingDir =  this.channelRootDir.getRootDir(channel);
            Path targetFile =  Paths.get(workingDir, msg.getFileName());
            Path newFile = Paths.get(workingDir, msg.getNewFileName());
            try {
                if (msg.getOType().equals(OType.DELETE)) {
                    Files.delete(targetFile);
                } else if (msg.getOType().equals(OType.COPY)) {
                    Files.copy(targetFile, newFile);
                } else if (msg.getOType().equals(OType.RENAME) || msg.getOType().equals(OType.MOVE)) {
                    Files.move(targetFile, newFile);
                }
                channel.writeAndFlush(new FileOperationMessage(Message.Type.RESPONSE, msg.getOType(), msg.getFileName(), msg.getNewFileName()));
            } catch (IOException e) {
                channel.writeAndFlush(new FileOperationMessage(Message.Type.EXCEPTION, msg.getOType(), msg.getFileName(), msg.getNewFileName()));
                System.out.println("FileOperationExecutor.execute");
                e.printStackTrace();
            }
        } else {
            if (this.stringCallback != null) stringCallback.callback(
                    "Операция:" + ((FileOperationMessage) message).getOType().toString() +
                            ((message.getType().equals(Message.Type.RESPONSE))? " - Выполнилась" : " - Не выполнилась")
            );
            //todo обработка. ошибки и исполнения действия.
        }
    }

    public void applyFileOperation(OType oType, String fileName, String newFileName, Channel channel) {
        channel.writeAndFlush(new FileOperationMessage(Message.Type.REQUEST, oType, fileName, newFileName));
    }
}
