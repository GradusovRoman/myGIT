package org.xokyopo.clientservercommon.protocol;

import io.netty.buffer.ByteBuf;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MyByteBufUtil {

    public static void addString(String text, ByteBuf byteBuf) {
        ByteBuf buffer = byteBuf.alloc().buffer(getTextLength(text));
        buffer.writeBytes(text.getBytes(StandardCharsets.UTF_8));

        byteBuf.writeInt(buffer.readableBytes());
        byteBuf.writeBytes(buffer.readBytes(buffer.readableBytes()));

        buffer.release();
    }

    public static String getString(ByteBuf byteBuf) {
        return byteBuf.readBytes(byteBuf.readInt()).toString(StandardCharsets.UTF_8);
    }
    
    public static int getTextLength(String... msg) {
        return Arrays.stream(msg).mapToInt(text->text.length()* TypeLengthUtilInByte.CHAR_LENGTH).sum() + msg.length * TypeLengthUtilInByte.INT_LENGTH;
    }
}
