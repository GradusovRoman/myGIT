package org.xokyopo.clientservercommon.protocol.executors;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.protocol.MyByteBufUtil;
import org.xokyopo.clientservercommon.protocol.executors.impl.IByteBufExecutor;
import org.xokyopo.clientservercommon.protocol.executors.impl.IncomingCallback;
import org.xokyopo.clientservercommon.protocol.executors.template.DefaultSignalByte;

public class PStringExecutor implements IByteBufExecutor {
    private IncomingCallback<String> incomingMessage;

    public PStringExecutor(IncomingCallback<String> incomingMessage) {
        this.setIncomingMessage(incomingMessage);
    }

    @Override
    public final byte getSignalByte() {
        return DefaultSignalByte.ONE_STRING.getSignal();
    }

    @Override
    public final void executeMessage(Channel channel, ByteBuf byteBuf) {
        this.incomingMessage.call(MyByteBufUtil.getString(byteBuf), channel);
        byteBuf.release();
    }

    public void setIncomingMessage(IncomingCallback<String> incomingMessage) {
        this.incomingMessage = (incomingMessage == null) ? (msg, ch)->{} : incomingMessage;
    }

    public void send(String msg, Channel channel) {
        ByteBuf byteBuf = channel.alloc().buffer(1 + MyByteBufUtil.getTextLength(msg));
        byteBuf.writeByte(this.getSignalByte());
        MyByteBufUtil.addString(msg, byteBuf);
        channel.writeAndFlush(byteBuf);
    }
}
