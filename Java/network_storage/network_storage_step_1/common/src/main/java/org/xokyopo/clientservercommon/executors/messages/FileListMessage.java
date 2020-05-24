package org.xokyopo.clientservercommon.executors.messages;

import org.xokyopo.clientservercommon.executors.messages.representor.FileRep;
import org.xokyopo.clientservercommon.simples.entitys.Message;

import java.util.List;

public class FileListMessage extends Message {
    private final List<FileRep> fileList;
    private final String path;

    public FileListMessage(Message.Type type, List<FileRep> fileList, String path) {
        super(type);
        this.fileList = fileList;
        this.path = path;
    }

    public List<FileRep> getFileList() {
        return fileList;
    }

    public String getPath() {
        return path;
    }
}
