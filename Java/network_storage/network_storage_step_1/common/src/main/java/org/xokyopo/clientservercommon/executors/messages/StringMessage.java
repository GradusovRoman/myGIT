package org.xokyopo.clientservercommon.executors.messages;

import org.xokyopo.clientservercommon.simples.entitys.Message;

public class StringMessage extends Message{
    private String text;

    public StringMessage(Message.Type type, String text) {
        super(type);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
