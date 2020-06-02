package org.xokyopo.clientservercommon.simples.entitys;


import java.io.Serializable;

public abstract class AbstractMessage implements Serializable {
    public enum Type{REQUEST, RESPONSE, EXCEPTION}
    private Type type;

    public AbstractMessage(AbstractMessage.Type type) {
        this.type = type;
    }

    public AbstractMessage.Type getType() {
        return type;
    }

    protected void  setType(Type type) {
        this.type = type;
    }
}
