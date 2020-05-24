package org.xokyopo.client;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.messages.StringMessage;
import org.xokyopo.clientservercommon.simples.entitys.Message;
import org.xokyopo.clientservercommon.network.impl.MessageExecutor;

public class StringExecutor implements MessageExecutor {
    @Override
    public Class<? extends Message> getInputMessageClass() {
        return StringMessage.class;
    }

    @Override
    public boolean isLongTimeOperation() {
        return false;
    }

    @Override
    public void execute(Message message, Channel channel) {
        StringMessage stringMessage = (StringMessage) message;
        System.out.println("получил: " + stringMessage.getText());
    }

    public void send(String msg, Channel channel) {
        channel.writeAndFlush(new StringMessage(Message.Type.REQUEST, msg));
    }
}
