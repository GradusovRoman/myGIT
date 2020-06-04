package org.xokyopo.clientservercommon.executors.messages;

import org.xokyopo.clientservercommon.simples.entitys.AbstractMessage;

public class StringMessage extends AbstractMessage {
    private String text;

    public StringMessage(AbstractMessage.Type type, String text) {
        super(type);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
