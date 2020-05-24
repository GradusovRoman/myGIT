package org.xokyopo.clientservercommon.executors.messages;

import org.xokyopo.clientservercommon.simples.entitys.Message;

public class FilePartMessage extends Message {
    private final String fileName;
    private final String filePath;
    private final Integer currentPart;
    private final Integer numberOfPart;
    private final Byte[] filePart;

    public FilePartMessage(Message.type type, String fileName, String filePath, Integer currentPart, Integer numberOfPart, Byte[] filePart) {
        super(type);
        this.fileName = fileName;
        this.filePath = filePath;
        this.currentPart = currentPart;
        this.numberOfPart = numberOfPart;
        this.filePart = filePart;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public Integer getCurrentPart() {
        return currentPart;
    }

    public Integer getNumberOfPart() {
        return numberOfPart;
    }

    public Byte[] getFilePart() {
        return filePart;
    }
}
