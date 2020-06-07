package org.xokyopo.clientservercommon.protocol.executors;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.network.impl.Callback;
import org.xokyopo.clientservercommon.protocol.MyByteBufUtil;
import org.xokyopo.clientservercommon.protocol.executors.impl.IByteBufExecutor;
import org.xokyopo.clientservercommon.protocol.executors.template.DefaultSignalByte;
import org.xokyopo.clientservercommon.seirialization.executors.impl.IChannelRootDir;
import org.xokyopo.clientservercommon.utils.FileUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PFileOperationExecutor implements IByteBufExecutor {
    private IChannelRootDir channelRootDir;
    private Callback<Boolean> remoteOperationResponse;

    private enum Type {
        MOVE((byte) 1),
        DELETE((byte) 2),
        FINISH((byte) 3)
        ;
        public final byte signal;

        Type(byte signal) {
            this.signal = signal;
        }
    }

    public PFileOperationExecutor(IChannelRootDir channelRootDir, Callback<Boolean> remoteOperationResponse) {
        this.setChannelRootDir(channelRootDir);
        this.setRemoteOperationResponse(remoteOperationResponse);
    }

    @Override
    public byte getSignalByte() {
        return DefaultSignalByte.FILE_OPERATION.getSignal();
    }

    @Override
    public void executeMessage(Channel channel, ByteBuf byteBuf) {
        try {
            byte signal = byteBuf.readByte();
            if (signal == Type.MOVE.signal) {
                    Files.move(
                            Paths.get(this.channelRootDir.getRootDir(channel), MyByteBufUtil.getString(byteBuf)),
                            Paths.get(this.channelRootDir.getRootDir(channel), MyByteBufUtil.getString(byteBuf))
                    );
            } else if (signal == Type.DELETE.signal) {
                FileUtil.recurseDelete(
                        Paths.get(this.channelRootDir.getRootDir(channel), MyByteBufUtil.getString(byteBuf))
                );
            } else if (signal == Type.FINISH.signal) {
                this.remoteOperationResponse.callback(byteBuf.readByte() == Type.FINISH.signal);
            }
        } catch (IOException e) {
            System.out.println("PFileOperationExecutor.executeMessage");
            e.printStackTrace();
        }
        byteBuf.release();
    }

    public void moveFile(String oldFileName, String newFileName, Channel channel) {
        ByteBuf outer = channel.alloc().buffer(2 + MyByteBufUtil.getTextLength(oldFileName, newFileName));
        outer.writeByte(this.getSignalByte());
        outer.writeByte(Type.MOVE.signal);
        MyByteBufUtil.addString(oldFileName, outer);
        MyByteBufUtil.addString(newFileName, outer);
        channel.writeAndFlush(outer);
    }

    public void deleteFile(String fileName, Channel channel) {
        ByteBuf outer = channel.alloc().buffer(2 + MyByteBufUtil.getTextLength(fileName));
        outer.writeByte(this.getSignalByte());
        outer.writeByte(Type.DELETE.signal);
        MyByteBufUtil.addString(fileName, outer);
        channel.writeAndFlush(outer);
    }

    public void setChannelRootDir(IChannelRootDir channelRootDir) {
        this.channelRootDir = (channelRootDir == null) ? ch->"." : channelRootDir;
    }

    public void setRemoteOperationResponse(Callback<Boolean> remoteOperationResponse) {
        this.remoteOperationResponse = (remoteOperationResponse == null) ? b->{} : remoteOperationResponse;
    }
}
