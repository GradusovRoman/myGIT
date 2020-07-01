package org.xokyopo.clientservercommon.protocol.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import org.xokyopo.clientservercommon.protocol.TypeLengthUtilInByte;
import org.xokyopo.clientservercommon.utils.ByteBuffRefCounter;

//TODO улучшить логику!!!!.
public class FrameEncoderDecoder extends ChannelDuplexHandler {
    private ByteBuf byteBuf;

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf outBuff = (ByteBuf) msg;
        try {
            ByteBuf resultBuff = ctx.alloc().buffer(TypeLengthUtilInByte.INT_LENGTH + outBuff.readableBytes());

//        System.out.println("отправляю пакет в :" + (outBuff.readableBytes())  + " байт");

            resultBuff.writeInt(outBuff.readableBytes());
            resultBuff.writeBytes(outBuff);
            ctx.writeAndFlush(resultBuff);

            ByteBuffRefCounter.add("FrameEncoderDecoder write resultBuff", resultBuff);
        } finally {
            ReferenceCountUtil.release(outBuff);
            ByteBuffRefCounter.add("FrameEncoderDecoder write outBuff", outBuff);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf input = (ByteBuf) msg;
        try {
            if (this.byteBuf == null && input.readableBytes() >= 4) {
                this.byteBuf = ctx.alloc().buffer(input.readInt());
            }

            if (this.byteBuf != null) {
                this.byteBuf.writeBytes(input);

                if (this.byteBuf.readableBytes() == this.byteBuf.capacity()) {
//                System.out.println("получаю пакет в " + this.byteBuf.readableBytes() + " байт");
                    ctx.fireChannelRead(this.byteBuf);
                    this.byteBuf = null;
                }
            }
        } finally {
            ReferenceCountUtil.release(input);
            ByteBuffRefCounter.add("FrameEncoderDecoder channelRead input", input);
        }
        //TODO слепленных два куска разных посылок возможно ли.
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        System.out.println("FrameEncoder.exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}