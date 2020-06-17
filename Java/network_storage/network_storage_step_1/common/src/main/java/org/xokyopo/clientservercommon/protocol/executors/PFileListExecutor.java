package org.xokyopo.clientservercommon.protocol.executors;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.network.impl.Callback;
<<<<<<< HEAD
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
=======
import org.xokyopo.clientservercommon.protocol.MyByteBufUtil;
import org.xokyopo.clientservercommon.protocol.executors.entitys.PFileInfo;
import org.xokyopo.clientservercommon.protocol.executors.template.PExecutorAdapter;
import org.xokyopo.clientservercommon.protocol.executors.template.ExecutorSignalByte;
import org.xokyopo.clientservercommon.seirialization.executors.impl.IChannelRootDir;
import org.xokyopo.clientservercommon.utils.ByteBuffCounter;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PFileListExecutor extends PExecutorAdapter {
    private IChannelRootDir channelRootDir;
    private Callback<List<PFileInfo>> fileListResponse;

    public PFileListExecutor(IChannelRootDir channelRootDir, Callback<List<PFileInfo>> fileListResponse) {
>>>>>>> network_storage_final
        this.setChannelRootDir(channelRootDir);
        this.setFileListResponse(fileListResponse);
    }

<<<<<<< HEAD
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
=======
    @Override
    public byte getSignalByte() {
        return ExecutorSignalByte.FILE_LIST.getSignal();
    }

    @Override
    public void executeRequest(Channel channel, ByteBuf byteBuf) throws Exception {
        Path path = Paths.get(channelRootDir.getRootDir(channel), MyByteBufUtil.getString(byteBuf));
        this.sendResponse(this.getEncodeFileList(path), channel);
    }

    @Override
    public void executeResponse(Channel channel, ByteBuf byteBuf) {
        this.fileListResponse.callback(this.getDecodeFileList(byteBuf));
    }


    private void sendResponse(List<ByteBuf> fileList, Channel channel) {
        ByteBuf outBuff = channel.alloc().buffer(2 + fileList.stream().mapToInt(ByteBuf::readableBytes).sum());
        outBuff.writeByte(this.getSignalByte());
        outBuff.writeByte(MessageType.RESPONSE.signal);
        MyByteBufUtil.writeByteBufList(fileList, outBuff);
        channel.writeAndFlush(outBuff);
    }

    public void sendRequest(String path, Channel channel) {
        ByteBuf outBuff = channel.alloc().buffer(2 + MyByteBufUtil.getTextLength(path));
        outBuff.writeByte(this.getSignalByte());
        outBuff.writeByte(MessageType.REQUEST.signal);
        MyByteBufUtil.addString(path, outBuff);
        channel.writeAndFlush(outBuff);
>>>>>>> network_storage_final
    }

    public void setChannelRootDir(IChannelRootDir channelRootDir) {
        this.channelRootDir = (channelRootDir == null) ? ch->"." : channelRootDir;
    }

<<<<<<< HEAD
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

=======
    public void setFileListResponse(Callback<List<PFileInfo>> fileListResponse) {
        this.fileListResponse = (fileListResponse == null) ? list->{} : fileListResponse;
    }

   private List<ByteBuf> getEncodeFileList(Path path) {
        File[] fileArr = path.toFile().listFiles();
        if (fileArr == null) return new ArrayList<>();
        else return Arrays.stream(fileArr).map(PFileInfo::getByte).collect(Collectors.toList());
    }

    private List<PFileInfo> getDecodeFileList(ByteBuf byteBuf) {
        List<PFileInfo> fileList = new ArrayList<>();
        while (byteBuf.readableBytes() > 0) fileList.add(new PFileInfo(byteBuf));
        return fileList;
    }
>>>>>>> network_storage_final
}
