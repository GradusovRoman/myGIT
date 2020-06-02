package org.xokyopo.clientservercommon.executors;

import io.netty.channel.Channel;
import org.xokyopo.clientservercommon.executors.messages.StringMessage;
import org.xokyopo.clientservercommon.network.impl.Callback;
import org.xokyopo.clientservercommon.simples.entitys.AbstractMessage;
import org.xokyopo.clientservercommon.network.impl.MessageExecutor;

public class StringExecutor implements MessageExecutor {
    private Callback<String> inputMessageCallback;

    public StringExecutor() {
        this(null);
    }

    public StringExecutor(Callback<String> inputMessageCallback) {
        this.inputMessageCallback = inputMessageCallback;
    }

    @Override
    public Class<? extends AbstractMessage> getInputMessageClass() {
        return StringMessage.class;
    }

    @Override
    public boolean isLongTimeOperation() {
        return false;
    }

    @Override
    public void execute(AbstractMessage abstractMessage, Channel channel) {
        StringMessage stringMessage = (StringMessage) abstractMessage;
        this.getInputMessageCallback().callback(stringMessage.getText());
    }

    public void send(String msg, Channel channel) {
        channel.writeAndFlush(new StringMessage(AbstractMessage.Type.REQUEST, msg));
    }

    private Callback<String> getInputMessageCallback() {
        return (this.inputMessageCallback == null) ? s->{} : this.inputMessageCallback;
    }
}
