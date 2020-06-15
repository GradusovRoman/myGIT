package org.xokyopo.clientservercommon.protocol.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.xokyopo.clientservercommon.protocol.TypeLengthUtilInByte;

@Sharable
public class FrameEncoderDecoder extends ChannelDuplexHandler {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf outBuff = (ByteBuf) msg;
        ByteBuf resultBuff = ctx.alloc().buffer(TypeLengthUtilInByte.INT_LENGTH + outBuff.readableBytes());

        resultBuff.writeInt(outBuff.readableBytes());
        resultBuff.writeBytes(outBuff);
        ctx.writeAndFlush(resultBuff);

        outBuff.release();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf input = (ByteBuf) msg;
        //TODO если я изначально обмениваюсь фреймами, если ли смысл проверять их длинну полностью?
        //TODO распиленные фреймы???
        if (input.readableBytes() > TypeLengthUtilInByte.INT_LENGTH) {
            Object outMsg = this.encode(input, input.readInt());
            if (outMsg != null) ctx.fireChannelRead(outMsg);
            input.release();
        }
    }

    private Object encode(ByteBuf byteBuf, int length) {
        return (byteBuf.readableBytes() >= length) ? byteBuf.readBytes(length) : null;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("FrameEncoder.exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}
