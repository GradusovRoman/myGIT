package org.xokyopo.clientservercommon.network.impl;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.simples.entitys.Message;

public interface MessageExecutor {
    Class<? extends Message> getInputMessageClass();
    boolean isLongTimeOperation();
    void execute(Message message, Channel channel);
}
