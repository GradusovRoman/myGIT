package org.xokyopo.clientservercommon.simples.entitys;


import java.io.Serializable;

public class Message implements Serializable {
    public enum type{REQUEST, RESPONSE, EXCEPTION}
    private type type;

    public Message(Message.type type) {
        this.type = type;
    }

    public Message.type getType() {
        return type;
    }
}
