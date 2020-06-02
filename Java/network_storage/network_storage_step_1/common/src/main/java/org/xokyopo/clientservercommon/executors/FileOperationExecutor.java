package org.xokyopo.clientservercommon.executors;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.impl.IChannelRootDir;
import org.xokyopo.clientservercommon.executors.messages.FileOperationMessage;
import org.xokyopo.clientservercommon.executors.messages.entitys.FilePart;
import org.xokyopo.clientservercommon.network.impl.Callback;
import org.xokyopo.clientservercommon.simples.AbstractMessageExecutor;
import org.xokyopo.clientservercommon.simples.entitys.AbstractMessage;
import org.xokyopo.clientservercommon.executors.messages.FileOperationMessage.OType;
import org.xokyopo.clientservercommon.utils.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileOperationExecutor extends AbstractMessageExecutor<FileOperationMessage> {
    private final IChannelRootDir channelRootDir;
    private final Callback<Boolean> remoteOperationResponse;

    public FileOperationExecutor(IChannelRootDir channelRootDir, Callback<Boolean> remoteOperationResponse) {
        this.channelRootDir = (channelRootDir == null)? (Ch)->"." : channelRootDir;
        this.remoteOperationResponse = remoteOperationResponse;
    }

    @Override
    public final Class<? extends AbstractMessage> getInputMessageClass() {
        return FileOperationMessage.class;
    }

    @Override
    public final boolean isLongTimeOperation() {
        return false;
    }

    @Override
    public final void executeRequest(FileOperationMessage message, Channel channel) {
        String workingDir =  this.channelRootDir.getRootDir(channel);
        Path targetFile =  Paths.get(workingDir, message.getFileName());
        Path newFile = Paths.get(workingDir, message.getNewFileName());
        try {
            if (message.getOType().equals(OType.DELETE)) {
                if(targetFile.toFile().isFile()) Files.deleteIfExists(targetFile);
                else FileUtil.recurseDelete(targetFile);
            } else if (message.getOType().equals(OType.COPY)) {
                Files.copy(targetFile, newFile);
            } else if (message.getOType().equals(OType.MOVE)) {
                Files.move(targetFile, newFile);
            }
            this.sendResponse(message.getOType(), message.getFileName(), message.getNewFileName(), channel);
        } catch (IOException e) {
            this.sendException(message.getOType(), message.getFileName(), message.getNewFileName(), channel);
            System.out.println("FileOperationExecutor.execute");
            e.printStackTrace();
        }
    }

    @Override
    public final void executeResponse(FileOperationMessage message, Channel channel) {
        this.getRemoteOperationResponse().callback(true);
    }

    @Override
    public final void executeException(FileOperationMessage message, Channel channel) {
        this.getRemoteOperationResponse().callback(false);
    }

    public  final void sendResponse(OType oType, String fileName, String newFileName, Channel channel) {
        channel.writeAndFlush(new FileOperationMessage(AbstractMessage.Type.RESPONSE, oType, fileName, newFileName));
    }

    public final void sendException(OType oType, String fileName, String newFileName, Channel channel) {
        channel.writeAndFlush(new FileOperationMessage(AbstractMessage.Type.EXCEPTION, oType, fileName, newFileName));
    }

    private void applyFileOperation(OType oType, String fileName, String newFileName, Channel channel) {
        channel.writeAndFlush(new FileOperationMessage(AbstractMessage.Type.REQUEST, oType, fileName, newFileName));
    }

    private Callback<Boolean> getRemoteOperationResponse() {
        return (this.remoteOperationResponse == null) ? s->{} : this.remoteOperationResponse;
    }

    public void moveFile(String oldFileName, String newFileName, Channel channel) {
        this.applyFileOperation(OType.MOVE, oldFileName, newFileName, channel);
    }

    public void deleteFile(String fileName, Channel channel) {
        this.applyFileOperation(OType.DELETE, fileName, "", channel);
    }

    public void copyFile(String fileName, String toFileName, Channel channel) {
        this.applyFileOperation(OType.COPY, fileName, toFileName, channel);
    }
}
