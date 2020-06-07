package org.xokyopo.clientservercommon.protocol.executors;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.network.impl.Callback;
import org.xokyopo.clientservercommon.protocol.TypeLengthUtilInByte;
import org.xokyopo.clientservercommon.protocol.executors.impl.IByteBufExecutor;
import org.xokyopo.clientservercommon.protocol.executors.template.DefaultSignalByte;
import org.xokyopo.clientservercommon.seirialization.executors.impl.IChannelRootDir;
import org.xokyopo.clientservercommon.seirialization.executors.messages.FileListMessage;
import org.xokyopo.clientservercommon.seirialization.executors.messages.entitys.FileRep;
import org.xokyopo.clientservercommon.seirialization.template.AbstractMessage;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
//TODO PFileListExecutor не арботает еще
public class PFileListExecutor implements IByteBufExecutor {
    private IChannelRootDir channelRootDir;
    private Callback<List<FileRep>> fileListResponse;

    public PFileListExecutor(IChannelRootDir channelRootDir, Callback<List<FileRep>> fileListResponse) {
        this.setChannelRootDir(channelRootDir);
        this.setFileListResponse(fileListResponse);
    }

    private enum cmd {
        REQUEST((byte)0),
        RESPONSE((byte)0)
        ;
        private final byte signal;

        cmd(byte signal) {
            this.signal = signal;
        }

        public byte getSignal() {
            return signal;
        }
    }

    @Override
    public byte getSignalByte() {
        return DefaultSignalByte.FILE_LIST.getSignal();
    }

    @Override
    public void executeMessage(Channel channel, ByteBuf byteBuf) {
        byte cmdByte = byteBuf.readByte();
        if (cmdByte == cmd.REQUEST.getSignal()) {
            //todo get fileList

        } else if (cmdByte == cmd.RESPONSE.getSignal()) {
            //todo return fileList
        }
    }

    public void getFileList(String path, Channel channel) {
        //TODO
        ByteBuf outBuff = channel.alloc().buffer(2 + TypeLengthUtilInByte.getStringByteLength(path));
        outBuff.writeByte(this.getSignalByte());
        outBuff.writeByte(cmd.REQUEST.getSignal());
        outBuff.writeBytes(path.getBytes(StandardCharsets.UTF_8));
        channel.writeAndFlush(outBuff);
    }

    private Callback<List<FileRep>> getFileListResponse() {
        return (this.fileListResponse == null) ? a->{} : this.fileListResponse;
    }

    public void setChannelRootDir(IChannelRootDir channelRootDir) {
        this.channelRootDir = (channelRootDir == null) ? ch->"." : channelRootDir;
    }

    public void setFileListResponse(Callback<List<FileRep>> fileListResponse) {
        this.fileListResponse = (fileListResponse == null) ? list->{} : fileListResponse;
    }

    private List<String> getFileListOld(String path, Channel channel) {

        File filePath = Paths.get(channelRootDir.getRootDir(channel), path).toFile();

        File[] FileArr = filePath.listFiles();
        //список файлов..

        if (FileArr != null) {
            channel.writeAndFlush(new FileListMessage(
                    AbstractMessage.Type.RESPONSE,
                    Arrays.stream(FileArr).map(FileRep::new).collect(Collectors.toList()),
                    null)
            );
        }

        return null;
    }


    private String encodeFileList(List<File> list) {
        return null;
    }

    private List<String> decodeFileList(String string) {

        return null;
    }

}
