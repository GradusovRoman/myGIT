package org.xokyopo.clientservercommon.executors;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.impl.IChannelRootDir;
import org.xokyopo.clientservercommon.executors.messages.representor.FileRep;
import org.xokyopo.clientservercommon.executors.messages.FileListMessage;
import org.xokyopo.clientservercommon.network.impl.Callback;
import org.xokyopo.clientservercommon.network.impl.MessageExecutor;
import org.xokyopo.clientservercommon.simples.entitys.Message;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileListMessageExecutor implements MessageExecutor {
    private final IChannelRootDir channelRootDir;
    private final Callback<List<FileRep>> listCallback;

    public FileListMessageExecutor(IChannelRootDir channelRootDir, Callback<List<FileRep>> listCallback) {
        this.channelRootDir = (channelRootDir == null)? (Ch)->"." : channelRootDir;
        this.listCallback = listCallback;
    }

    @Override
    public Class<? extends Message> getInputMessageClass() {
        return FileListMessage.class;
    }

    @Override
    public boolean isLongTimeOperation() {
        return false;
    }

    @Override
    public void execute(Message message, Channel channel) {
        FileListMessage msg = (FileListMessage) message;

        if (msg.getType().equals(Message.Type.REQUEST)) {
            File path = Paths.get(channelRootDir.getRootDir(channel), msg.getPath()).toFile();
            File[] FileArr = path.listFiles();
            if (FileArr != null) {
                channel.writeAndFlush(new FileListMessage(
                        Message.Type.RESPONSE,
                        Arrays.stream(FileArr).map(FileRep::new).collect(Collectors.toList()),
                        null)
                );
            }
        } else {
            if (this.listCallback != null) this.listCallback.callback(((FileListMessage) message).getFileList());
        }
    }

    public void getFileList(String path, Channel channel) {
        channel.writeAndFlush(new FileListMessage(Message.Type.REQUEST, null, path));
    }
}
