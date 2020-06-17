package org.xokyopo.clientservercommon.protocol.executors;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.protocol.MyByteBufUtil;
import org.xokyopo.clientservercommon.protocol.executors.impl.IByteBufExecutor;
import org.xokyopo.clientservercommon.protocol.executors.impl.IncomingCallback;
<<<<<<< HEAD
import org.xokyopo.clientservercommon.protocol.executors.template.DefaultSignalByte;
=======
import org.xokyopo.clientservercommon.protocol.executors.template.ExecutorSignalByte;
>>>>>>> network_storage_final

public class PStringExecutor implements IByteBufExecutor {
    private IncomingCallback<String> incomingMessage;

    public PStringExecutor(IncomingCallback<String> incomingMessage) {
        this.setIncomingMessage(incomingMessage);
    }

    @Override
    public final byte getSignalByte() {
<<<<<<< HEAD
        return DefaultSignalByte.ONE_STRING.getSignal();
=======
        return ExecutorSignalByte.ONE_STRING.getSignal();
>>>>>>> network_storage_final
    }

    @Override
    public final void executeMessage(Channel channel, ByteBuf byteBuf) {
        this.incomingMessage.call(MyByteBufUtil.getString(byteBuf), channel);
<<<<<<< HEAD
        byteBuf.release();
=======
>>>>>>> network_storage_final
    }

    public void setIncomingMessage(IncomingCallback<String> incomingMessage) {
        this.incomingMessage = (incomingMessage == null) ? (msg, ch)->{} : incomingMessage;
    }

    public void send(String msg, Channel channel) {
<<<<<<< HEAD
        ByteBuf byteBuf = channel.alloc().buffer(1 + MyByteBufUtil.getTextLength(msg));
        byteBuf.writeByte(this.getSignalByte());
        MyByteBufUtil.addString(msg, byteBuf);
        channel.writeAndFlush(byteBuf);
=======
        ByteBuf outBuff = channel.alloc().buffer(1 + MyByteBufUtil.getTextLength(msg));
        outBuff.writeByte(this.getSignalByte());
        MyByteBufUtil.addString(msg, outBuff);
        channel.writeAndFlush(outBuff);
>>>>>>> network_storage_final
    }
}
