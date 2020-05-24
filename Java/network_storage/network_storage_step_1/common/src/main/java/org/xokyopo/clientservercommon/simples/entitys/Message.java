package org.xokyopo.clientservercommon.simples.entitys;


import java.io.Serializable;

public class Message implements Serializable {
    public enum Type{REQUEST, RESPONSE, EXCEPTION}
    private Type type;

    public Message(Message.Type type) {
        this.type = type;
    }

    public Message.Type getType() {
        return type;
    }
}
