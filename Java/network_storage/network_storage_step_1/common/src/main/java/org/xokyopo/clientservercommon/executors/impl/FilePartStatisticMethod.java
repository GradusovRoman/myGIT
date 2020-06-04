package org.xokyopo.clientservercommon.executors.impl;

public interface FilePartStatisticMethod {
    void take(String fileName, long fileFullLength, long FileTransmittedLength);
}
