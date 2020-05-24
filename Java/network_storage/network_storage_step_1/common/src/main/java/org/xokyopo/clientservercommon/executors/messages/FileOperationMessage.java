package org.xokyopo.clientservercommon.executors.messages;

import org.xokyopo.clientservercommon.simples.entitys.Message;

public class FileOperationMessage extends Message {
    public enum OType{RENAME, DELETE, COPY, MOVE}
    private final String fileName;
    private final OType oType;
    private final String newFileName;

    public FileOperationMessage(Message.type type, OType type1, String fileName,  String newFileName) {
        super(type);
        this.fileName = fileName;
        this.oType = type1;
        this.newFileName = newFileName;
    }

    public FileOperationMessage.OType getOType() {
        return this.oType;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getNewFileName() {
        return this.newFileName;
    }
}
